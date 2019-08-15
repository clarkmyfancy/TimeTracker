package com.example.productivitytracker.TimeTracker

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.productivitytracker.database.ProductiveSession
import com.example.productivitytracker.database.SessionDatabaseDao
import kotlinx.coroutines.*

class TimeTrackerViewModel (
    val database: SessionDatabaseDao,
    application: Application) : AndroidViewModel(application) {

    init {
        initCurrentSession()
    }

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private var currentSession = MutableLiveData<ProductiveSession?>()

    private fun initCurrentSession() {
        uiScope.launch {
            currentSession.value = getCurrentSessionFromDatabase()
        }
    }

    private suspend fun getCurrentSessionFromDatabase(): ProductiveSession? {
        return withContext(Dispatchers.IO) {
            var session = database.getMostRecentSession()
            if (session?.endTimeMilli != session?.startTimeMilli) {
                session = null
            }
            session
        }
    }

    fun onClickStart() {
        uiScope.launch {
            val newSession = ProductiveSession()
            insert(newSession)
            currentSession.value = getCurrentSessionFromDatabase()
        }
    }

    private suspend fun insert(newSession: ProductiveSession) {
        return withContext(Dispatchers.IO) {
            database.insert(newSession)
        }
    }

    fun onClickStop() {
        uiScope.launch {
            val oldSession = currentSession.value ?: return@launch
            oldSession.endTimeMilli = System.currentTimeMillis()
            update(oldSession)
        }
    }

    private suspend fun update(oldSession: ProductiveSession) {
        return withContext(Dispatchers.IO) {
            database.update(oldSession)
        }
    }


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
//fun onClickClear() {
//    TODO()
//}