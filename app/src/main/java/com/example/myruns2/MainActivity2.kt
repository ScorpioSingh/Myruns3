package com.example.myruns2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import java.sql.SQLTimeoutException

class MainActivity2 : AppCompatActivity() {
    private lateinit var database: ExerciseDatabase
    private lateinit var databasedao: ActivityDatabaseDao
    private lateinit var repository: ExerciseEntryRepository
    private lateinit var viewModel: ExerciseViewModel
    private lateinit var factory: ExerciseViewModelFactory
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        var id: Long
        var input: String
        var activity:String
        var date: String
        var duration: String
        var distance: String
        var calories: String
        var heart: String
        var extras = intent.extras
        if(extras!= null){
           id = extras.getString("id","0").toLong()
           input = extras.getString("input","")
           activity = extras.getString("activity","")
           date = extras.getString("datetime","")
           duration = extras.getString("duration","")
           distance = extras.getString("distance","")
           calories = extras.getString("calories","")
           heart = extras.getString("heartRate","")

            var A = findViewById<EditText>(R.id.inputHistory)
            A.setText(input)

            var B = findViewById<EditText>(R.id.activityHistory)
            B.setText(activity)

            var C = findViewById<EditText>(R.id.dateHistory)
            C.setText(date)

            var D = findViewById<EditText>(R.id.duration_history)
            D.setText(duration+" secs")

            var E =  findViewById<EditText>(R.id.history_distance)
            E.setText(distance + " Miles")

            var F =  findViewById<EditText>(R.id.history_calories)
            F.setText(calories + "Calories")

            var G = findViewById<EditText>(R.id.heart_history)
            G.setText(heart + "Bpm")

            var deleteButton = findViewById<TextView>(R.id.historyDelete)
            database = ExerciseDatabase.getInstance(this)
            databasedao = database.activityDao
            repository = ExerciseEntryRepository(databasedao)
            factory = ExerciseViewModelFactory(repository)
            viewModel = ViewModelProvider(this, factory).get(ExerciseViewModel::class.java)
            deleteButton.setOnClickListener{
                viewModel.deleteEntry(id)
                finish()
            }
        }



    }
}