package com.example.productivitytracker.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update


@Dao
interface SessionDatabaseDao {

    @Insert
    fun insert(session: ProductiveSession)

    @Update
    fun update(session: ProductiveSession)

    @Query("SELECT * FROM productive_session_table WHERE sessionId = :key")
    fun getSession(key: Long): ProductiveSession

    @Query("SELECT * FROM productive_session_table")
    fun getAllSessions(): LiveData<List<ProductiveSession>>

    @Query("SELECT * FROM productive_session_table ORDER BY sessionId DESC LIMIT 1")
    fun getMostRecentSession(): ProductiveSession?

    @Query("DELETE FROM productive_session_table WHERE sessionId = :key")
    fun removeSession(key: Long)
}