package com.example.productivitytracker

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import com.example.productivitytracker.database.SessionDatabase
import com.example.productivitytracker.database.SessionDatabaseDao
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class SessionDatabaseTest {

    private lateinit var db: SessionDatabase
    private lateinit var sessionDao: SessionDatabaseDao

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext

        db = Room.inMemoryDatabaseBuilder(context, SessionDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        sessionDao = db.sessionDatabaseDao
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }
}