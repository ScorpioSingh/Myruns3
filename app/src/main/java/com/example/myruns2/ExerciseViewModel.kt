package com.example.myruns2

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewmodel.CreationExtras
import java.text.FieldPosition

class ExerciseViewModel(private val repository: ExerciseEntryRepository):ViewModel()  {

    val exerciseLiveData: LiveData<List<Table>> = repository.allData.asLiveData()

    fun insert(table: Table){
        repository.insert(table)
    }

    fun deleteEntry(id:Long){
        val dataList = exerciseLiveData.value
        if(dataList!= null && dataList.size >0){
            repository.delete(id)
        }
    }

    fun selected(position:Int): Table? {
        val dataList = exerciseLiveData.value
        if(dataList!= null && dataList.size >0){
            return dataList[position]
        }
       return null
    }

    fun deleteAll(){
        val dataList = exerciseLiveData.value
        if(dataList!= null && dataList.size >0){
            repository.deleteAll()
        }
    }
}

class ExerciseViewModelFactory(private val repository: ExerciseEntryRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        if(modelClass.isAssignableFrom(ExerciseViewModel::class.java))
            return ExerciseViewModel(repository) as T
        throw IllegalArgumentException("UNKNOWN VIEWMODEL CLASS ")

    }

}