package com.example.pokedexlite.data.local.dao

import com.couchbase.lite.CouchbaseLiteException
import com.couchbase.lite.DataSource
import com.couchbase.lite.Expression
import com.couchbase.lite.MutableDocument
import com.couchbase.lite.QueryBuilder
import com.couchbase.lite.SelectResult
import com.example.pokedexlite.data.local.CouchbaseManager
import com.example.pokedexlite.domain.model.User
import javax.inject.Inject
import javax.inject.Singleton
import com.couchbase.lite.Collection as CblCollection

@Singleton
class UserDao @Inject constructor(
    private val couchbaseManager: CouchbaseManager
) {
    private val db get() = couchbaseManager.database

    private val collection: CblCollection?
        get() = try {
            db.defaultCollection
        } catch (e: CouchbaseLiteException) {
            e.printStackTrace()
            null
        }

    // ── Write ────────────────────────────────────────────────────────────────

    fun insertUser(user: User) {
        val target = collection ?: return

        try {
            val docId = "user_${user.id}"

            val doc = MutableDocument(docId).apply {
                setString("type", DOC_TYPE)
                setString("id", user.id)
                setString("username", user.username)
                setString("email", user.email)
                setString("password", user.password)
            }

            // Save is now performed on the Collection
            target.save(doc)
        } catch (e: CouchbaseLiteException) {
            e.printStackTrace()
        }
    }

    // ── Read ─────────────────────────────────────────────────────────────────

    fun getUserByUsername(username: String): User? {
        val target = collection ?: return null
        val query = QueryBuilder
            .select(
                SelectResult.property("id"),
                SelectResult.property("username"),
                SelectResult.property("email"),
                SelectResult.property("password")
            )
            .from(DataSource.collection(target))
            .where(
                Expression.property("type").equalTo(Expression.string(DOC_TYPE))
                    .and(
                        Expression.property("username")
                            .equalTo(Expression.string(username))
                    )
            )

        return try {
            query.execute().use { rs ->
                rs.allResults().firstOrNull()?.let { row ->
                    User(
                        id = row.getString("id") ?: "",
                        username = row.getString("username") ?: "",
                        email = row.getString("email") ?: "",
                        password = row.getString("password") ?: ""
                    )
                }
            }
        } catch (e: CouchbaseLiteException) {
            e.printStackTrace()
            null
        }
    }

    fun userExists(username: String): Boolean = getUserByUsername(username) != null

    companion object {
        private const val DOC_TYPE = "user"
    }
}