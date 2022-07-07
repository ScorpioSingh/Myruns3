package com.example.myruns2

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "ExerciseEntry") //making all the objects nullable
data class Table(
        @PrimaryKey(autoGenerate = true)
        var id: Long = 0L,
        @ColumnInfo(name = "input")
        var inputType:Int = 0,
        @ColumnInfo(name = "activityType")
        var activityType:Int =0,
        @ColumnInfo(name = "date_time")
        var dateTime: String = "",
        @ColumnInfo(name = "duration")
        var duration: Int =0 ,
        @ColumnInfo(name = "distance")
        var distance: Float = 0F,
        @ColumnInfo(name = "averagePace")
        var avgPace: Float = 0F,
        @ColumnInfo(name = "averageSpeed")
        var avgSpeed:Float = 0F,
        @ColumnInfo(name = "calorie")
        var calorie:Float = 0F,
        @ColumnInfo(name = "climb")
        var climb:Float = 0F,
        @ColumnInfo(name = "heartRate")
        var heartRate:Int =0 ,
        @ColumnInfo(name = "comment")
        var comment:String = ""
//        @ColumnInfo(name = "list")
//        var locationList:ArrayList<LatLng>
)