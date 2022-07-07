package com.example.myruns2

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Table::class], version = 1) //version number 1
abstract class ExerciseDatabase:RoomDatabase() {
    abstract  val activityDao:ActivityDatabaseDao

    companion object{
        @Volatile
        private var INSTANCE: ExerciseDatabase?=null

        fun getInstance(context: Context): ExerciseDatabase{
            synchronized(this){
                var instance = INSTANCE
                if(instance == null){
                    instance = Room.databaseBuilder(context.applicationContext,
                        ExerciseDatabase::class.java,"exerice_entry").build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }





}