package com.example.productivitytracker

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.productivitytracker.database.ProductiveSession
import com.example.productivitytracker.database.SessionDatabase
import com.example.productivitytracker.database.SessionDatabaseDao
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class SessionDatabaseTest : junit.framework.TestCase(){

    private lateinit var sessionDao: SessionDatabaseDao
    private lateinit var db: SessionDatabase


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

    @Test
    @Throws(Exception::class)
    fun insertAndGetSession() {
        val sesh = ProductiveSession()
        sessionDao.insert(sesh)
        val currentSesh = sessionDao.getMostRecentSession()
        assertEquals(currentSesh?.description, "")
    }

    @Test
    @Throws(Exception::class)
    fun getAndDeleteSession() {
        // Arrange
        val sesh = ProductiveSession()
        sessionDao.insert(sesh)
        val newSesh = sessionDao.getMostRecentSession()

        // Act
        sessionDao.removeSession(newSesh!!.sessionId)

        // Assert
        val checkSesh = sessionDao.getMostRecentSession()
        assertNotSame(newSesh, checkSesh)
    }
}