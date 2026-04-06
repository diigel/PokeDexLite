package com.example.pokedexlite.data.local

import android.content.Context
import com.couchbase.lite.CouchbaseLite
import com.couchbase.lite.Database
import com.couchbase.lite.DatabaseConfiguration
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CouchbaseManager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    /**
     * Lazily initialise Couchbase Lite and open (or create) the shared database.
     * The database file is stored in the app's internal files directory.
     */
    val database: Database by lazy {
        CouchbaseLite.init(context)
        val config = DatabaseConfiguration().apply {
            directory = context.filesDir.absolutePath
        }
        Database(DB_NAME, config)
    }

    companion object {
        private const val DB_NAME = "pokedex_lite"
    }
}
