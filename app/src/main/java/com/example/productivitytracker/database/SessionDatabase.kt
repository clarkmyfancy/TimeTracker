package com.example.productivitytracker.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ProductiveSession::class], version = 1, exportSchema = false)
abstract class SessionDatabase : RoomDatabase() {

    abstract val sessionDatabaseDao: SessionDatabaseDao

    // allows clients to access properties and methods of class without instantiating it
    companion object {

        @Volatile
        private var INSTANCE: SessionDatabase? = null

        fun getInstance(context: Context): SessionDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        SessionDatabase::class.java,
                        "productive_session_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}