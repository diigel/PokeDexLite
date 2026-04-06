package com.example.pokedexlite.data.local.dao

import com.couchbase.lite.Collection
import com.couchbase.lite.Result
import com.couchbase.lite.CouchbaseLiteException
import com.couchbase.lite.DataSource
import com.couchbase.lite.Expression
import com.couchbase.lite.MutableArray
import com.couchbase.lite.MutableDocument
import com.couchbase.lite.QueryBuilder
import com.couchbase.lite.SelectResult
import com.couchbase.lite.UnitOfWork
import com.example.pokedexlite.data.local.CouchbaseManager
import com.example.pokedexlite.domain.model.Pokemon
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PokemonDao @Inject constructor(
    private val couchbaseManager: CouchbaseManager
) {
    private val db get() = couchbaseManager.database

    private val collection: Collection
        get() = db.defaultCollection

    // ── Write ────────────────────────────────────────────────────────────────

    fun insertPokemons(pokemons: List<Pokemon>) {
        try {
            db.inBatch(UnitOfWork {
                pokemons.forEach { pokemon ->
                    val abilitiesArray = MutableArray().apply {
                        pokemon.abilities.forEach { addString(it) }
                    }

                    // Use pokemon_id as Document ID to ensure atomicity and prevent duplicates
                    val docId = "pokemon_${pokemon.id}"

                    val doc = MutableDocument(docId).apply {
                        setString("type", DOC_TYPE)
                        setInt("pokemon_id", pokemon.id)
                        setString("name", pokemon.name)
                        setInt("page", pokemon.page)
                        setArray("abilities", abilitiesArray)
                    }

                    // Save operation on the collection instance
                    collection.save(doc)
                }
            })
        } catch (e: CouchbaseLiteException) {
            // Handle Couchbase-specific errors
            e.printStackTrace()
        }
    }

    // ── Read ─────────────────────────────────────────────────────────────────

    fun getPokemonsByPage(page: Int): List<Pokemon> {
        val query = QueryBuilder
            .select(
                SelectResult.property("pokemon_id"),
                SelectResult.property("name"),
                SelectResult.property("page"),
                SelectResult.property("abilities")
            )
            .from(DataSource.collection(collection))
            .where(
                Expression.property("type").equalTo(Expression.string(DOC_TYPE))
                    .and(Expression.property("page").equalTo(Expression.intValue(page)))
            )

        return try {
            query.execute().use { rs ->
                rs.allResults().map { row -> row.toPokemon() }
            }
        } catch (e: CouchbaseLiteException) {
            e.printStackTrace()
            emptyList()
        }
    }

    fun getPokemonByName(name: String): Pokemon? {
        val query = QueryBuilder
            .select(
                SelectResult.property("pokemon_id"),
                SelectResult.property("name"),
                SelectResult.property("page"),
                SelectResult.property("abilities")
            )
            .from(DataSource.collection(collection))
            .where(
                Expression.property("type").equalTo(Expression.string(DOC_TYPE))
                    .and(Expression.property("name").equalTo(Expression.string(name)))
            )

        return try {
            query.execute().use { rs ->
                rs.allResults().firstOrNull()?.toPokemon()
            }
        } catch (e: CouchbaseLiteException) {
            e.printStackTrace()
            null
        }
    }

    fun searchByName(nameQuery: String): List<Pokemon> {
        val query = QueryBuilder
            .select(
                SelectResult.property("pokemon_id"),
                SelectResult.property("name"),
                SelectResult.property("page"),
                SelectResult.property("abilities")
            )
            .from(DataSource.collection(collection))
            .where(
                Expression.property("type").equalTo(Expression.string(DOC_TYPE))
                    .and(Expression.property("name").like(Expression.string("%$nameQuery%")))
            )

        return try {
            query.execute().use { rs ->
                rs.allResults().map { row -> row.toPokemon() }
            }
        } catch (e: CouchbaseLiteException) {
            e.printStackTrace()
            emptyList()
        }
    }

    // ── Helpers ───────────────────────────────────────────────────────────────

    private fun Result.toPokemon(): Pokemon {
        val abilitiesArray = getArray("abilities")
        val abilities = (0 until (abilitiesArray?.count() ?: 0))
            .mapNotNull { abilitiesArray?.getString(it) }

        return Pokemon(
            id = getInt("pokemon_id"),
            name = getString("name").orEmpty(),
            abilities = abilities,
            page = getInt("page")
        )
    }

    companion object {
        private const val DOC_TYPE = "pokemon"
    }
}