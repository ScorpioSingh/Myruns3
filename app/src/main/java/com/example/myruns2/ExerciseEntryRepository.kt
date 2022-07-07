package com.example.myruns2

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class ExerciseEntryRepository(private val inputActivityDao: ActivityDatabaseDao) {
    var allData:Flow<List<Table>> = inputActivityDao.getAllData()

    fun insert(table: Table){
        CoroutineScope(IO).launch {
            inputActivityDao.insertActivity(table)
        }

    }

    fun delete(id:Long){
        CoroutineScope(IO).launch {
            inputActivityDao.deleteEntry(id)
        }
    }

    fun deleteAll(){
        CoroutineScope(IO).launch {
            inputActivityDao.deleteAll()
        }
    }
}