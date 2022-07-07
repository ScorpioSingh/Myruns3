package com.example.myruns2

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.View
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import java.util.*

class ManualActivity : AppCompatActivity() {
    private lateinit var database: ExerciseDatabase
    private lateinit var databasedao: ActivityDatabaseDao
    private lateinit var repository: ExerciseEntryRepository
    private lateinit var viewModel: ExerciseViewModel
    private lateinit var factory: ExerciseViewModelFactory
    private var duration: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.manualentry)

        database = ExerciseDatabase.getInstance(this)
        databasedao = database.activityDao
        repository = ExerciseEntryRepository(databasedao)
        factory = ExerciseViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(ExerciseViewModel::class.java)
        val entry = Table()
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        var month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        var hour = c.get(Calendar.HOUR_OF_DAY)
        var minutes = c.get(Calendar.MINUTE)
        var seconds = c.get(Calendar.SECOND)

        var inputType: String
        var activityType: String
        val cal = Calendar.getInstance()
        val listView = findViewById<ListView>(R.id.listView)
        val items =
            arrayOf("Date", "Time", "Duration", "Distance", "Calories", "Heart Rate", "Comment")

        val arrayadapter: ArrayAdapter<String> = ArrayAdapter(
            this, android.R.layout.simple_list_item_1, items
        )

        listView.adapter = arrayadapter
        listView.setOnItemClickListener { parent, view, position, id ->
            if (position == 0) {

                var newDay = ""


                val popDialog = DatePickerDialog(
                    this, DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
//                    date.setText(""+dayOfMonth +"/" + month + "/ "+ year)

                        cal.set(dayOfMonth, month, year)

                        println("$dayOfMonth, $day, $month")
                    }, year, month, day
                )
                popDialog.show()
            }


            if (position == 1) {
                val timeListener = TimePickerDialog.OnTimeSetListener { timePicker, hour_to, minute_to ->
                   hour = hour_to
                   minutes = minute_to
                   seconds = 0




                }
                TimePickerDialog(
                    this, timeListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE),
                    true
                ).show()
            }
            if (position == 4) {
                val builder2 = AlertDialog.Builder(this)
                val inflater2 = layoutInflater
                val dialoglayout2 = inflater2.inflate(R.layout.caloriesformat, null)

                var editText3: EditText = dialoglayout2.findViewById(R.id.calorieFormat)



                with(builder2) {
                    setTitle("Calories")
                    setPositiveButton("OK") { dialog, which ->
                        var calorieString: String = editText3.text.toString()
                        if(calorieString.isNotEmpty()){
                            entry.calorie = calorieString.toFloat()
                        }

                    }
                    setNegativeButton("Cancel") { dialog, which ->
                        Log.d("Main", "Negative Button Clicked")
                    }
                    setView(dialoglayout2)
                    show()
                }

            }

            if (position == 3) {
                val builder1 = AlertDialog.Builder(this)
                val inflater1 = layoutInflater
                val dialoglayout1 = inflater1.inflate(R.layout.distanceformat, null)

                val editText2: EditText = dialoglayout1.findViewById(R.id.distanceFormat)



                with(builder1) {
                    setTitle("Distance")
                    setPositiveButton("OK") { dialog, which ->
                        var distanceString: String = editText2.text.toString()
                        if(distanceString.isNotEmpty())
                        entry.distance = distanceString.toFloat()}
                    setNegativeButton("Cancel") { dialog, which ->
                        Log.d("Main", "Negative Button Clicked")
                    }
                    setView(dialoglayout1)
                    show()
                }


            }

            if (position == 5) {
                val builder = AlertDialog.Builder(this)
                val inflater = layoutInflater
                val dialoglayout = inflater.inflate(R.layout.heartrateformat, null)

                var editText4: EditText = dialoglayout.findViewById(R.id.heartRateFormat)



                with(builder) {
                    setTitle("Heart Rate")
                    setPositiveButton("OK") { dialog, which ->
                        var heartString: String = editText4.text.toString()
                        if(heartString.isNotEmpty())
                        entry.heartRate = heartString.toInt()
                    }
                    setNegativeButton("Cancel") { dialog, which ->
                        Log.d("Main", "Negative Button Clicked")
                    }
                    setView(dialoglayout)
                    show()
                }
            }
            if (position == 6) {
                val builder = AlertDialog.Builder(this)
                val inflater = layoutInflater
                val dialoglayout = inflater.inflate(R.layout.startcomments, null)
                val editText: EditText = dialoglayout.findViewById(R.id.Duration)


                with(builder) {
                    setTitle("Comments")
                    setPositiveButton("OK") { dialog, which ->
                        var strings: String = editText.text.toString()
                        if(strings.isNotEmpty())
                        entry.comment = strings
                    }
                    setNegativeButton("Cancel") { dialog, which ->
                        Log.d("Main", "Negative Button Clicked")
                    }
                    setView(dialoglayout)
                    show()
                }
            }
            if (position == 2) {
                val builder: AlertDialog.Builder = AlertDialog.Builder(this)
                val inflater = layoutInflater
                val dialoglayout = inflater.inflate(R.layout.durationformat, null)

                val editText1: EditText = dialoglayout.findViewById(R.id.duration90)



                with(builder) {
                    setTitle("Duration")
                    setPositiveButton("OK") { dialog, which ->
                        var durationString: String = editText1.text.toString()
                        if(durationString.isNotEmpty())
                        entry.duration = durationString.toInt()
                    }
                    setNegativeButton("Cancel") { dialog, which ->
                        Log.d("Main", "Negative Button Clicked")
                    }
                    setView(dialoglayout)
                    show()
                }
            }

                var extra = intent.extras
                if (extra != null) {
                    inputType = extra.getString("inputType", "")
                    activityType = extra.getString("ActivityType", "")
                    entry.inputType = inputType.toInt()
                    entry.activityType = activityType.toInt()
                }


                val button: Button = findViewById(R.id.button)
                button.setOnClickListener {
                    entry.dateTime = "$hour:$minutes:$seconds, $month $day $year "
                    viewModel.insert(entry)
                    Toast.makeText(this, "Entry has been recorded", Toast.LENGTH_LONG).show()
                    finish()
                }


            }



//    fun saveActivity(view: View) {
//        finish();
//
//
//
//    }
        }
    fun dismissButton(view: View) {
        finish();
        Toast.makeText(this, "Entry Discarded", Toast.LENGTH_LONG).show()
    }
    }




//date picker dialog implemented

//        var datePicker = findViewById<TextView>(R.id.date)
//
//        datePicker.setOnClickListener {
//            val c = Calendar.getInstance()
//            val year = c.get(Calendar.YEAR)
//            val month = c.get(Calendar.MONTH)
//            val day = c.get(Calendar.DAY_OF_MONTH)
//            var newDay = ""
//            val cal = Calendar.getInstance()
//
//            val popDialog = DatePickerDialog(
//                this,DatePickerDialog.OnDateSetListener{
//                        view, year, month, dayOfMonth ->
////                    date.setText(""+dayOfMonth +"/" + month + "/ "+ year)
//                    cal.set(dayOfMonth,month,year)
//                }, year,month,day)
//            popDialog.show()
//
//            //time Picker
//
//            var timePicker = findViewById<TextView>(R.id.Time)
//            timePicker.setOnClickListener {
//
//                val timeListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
//                    cal.set(Calendar.HOUR_OF_DAY, hour)
//                    cal.set(Calendar.MINUTE, minute)
//
//                }
//                TimePickerDialog(
//                    this, timeListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE),
//                    true
//                ).show()
//                entry.dateTime = cal.toString()
//            }
//
//
//            //duration box
//            val DurationBox: TextView = findViewById(R.id.Duration)
//
//            DurationBox.setOnClickListener {
//                val builder = AlertDialog.Builder(this)
//                val inflater = layoutInflater
//                val dialoglayout = inflater.inflate(R.layout.durationformat, null)
////                val editComment = dialoglayout.findViewById<EditText>(R.id.CommentsEditText)
////
//                var editText1: EditText = findViewById(R.id.duration90)
//                var durationString: String = editText1.text.toString()
////                entry.duration = durationString.toInt()
//
//
//                with(builder) {
//                    setTitle("Duration")
//                    setPositiveButton("OK") { dialog, which -> }
//                    setNegativeButton("Cancel") { dialog, which ->
//                        Log.d("Main", "Negative Button Clicked")
//                    }
//                    setView(dialoglayout)
//                    show()
//
//
//                }
//
//
//            }
//
//
//            //distanceBox
//
//            val DistanceBox: TextView = findViewById(R.id.Distance)
//            DistanceBox.setOnClickListener {
//                val builder = AlertDialog.Builder(this)
//                val inflater = layoutInflater
//                val dialoglayout = inflater.inflate(R.layout.distanceformat, null)
//
////                val editText2: EditText = findViewById(R.id.distanceFormat)
////                var distanceString: String = editText2.text.toString()
////                entry.distance = distanceString.toFloat()
//
//
//                with(builder) {
//                    setTitle("Distance")
//                    setPositiveButton("OK") { dialog, which -> }
//                    setNegativeButton("Cancel") { dialog, which ->
//                        Log.d("Main", "Negative Button Clicked")
//                    }
//                    setView(dialoglayout)
//                    show()
//                }
//            }
//
//
//            //caloriesBox
//
//            val caloriesBox: TextView = findViewById(R.id.Calories)
//            caloriesBox.setOnClickListener {
//                val builder = AlertDialog.Builder(this)
//                val inflater = layoutInflater
//                val dialoglayout = inflater.inflate(R.layout.caloriesformat, null)
//
////                var editText3: EditText = findViewById(R.id.calorieFormat)
////                var calorieString: String = editText3.text.toString()
////                entry.calorie = calorieString.toFloat()
//
//                with(builder) {
//                    setTitle("Calories")
//                    setPositiveButton("OK") { dialog, which -> }
//                    setNegativeButton("Cancel") { dialog, which ->
//                        Log.d("Main", "Negative Button Clicked")
//                    }
//                    setView(dialoglayout)
//                    show()
//                }
//            }
//
//
//            //heart Rate
//
//            val heartBox: TextView = findViewById(R.id.HeartRate)
//            heartBox.setOnClickListener {
//                val builder = AlertDialog.Builder(this)
//                val inflater = layoutInflater
//                val dialoglayout = inflater.inflate(R.layout.heartrateformat, null)
////                var editText4: EditText = findViewById(R.id.heartRateFormat)
////                var heartString: String = editText4.text.toString()
////                entry.heartRate = heartString.toInt()
//                with(builder) {
//                    setTitle("Heart Rate")
//                    setPositiveButton("OK") { dialog, which -> }
//                    setNegativeButton("Cancel") { dialog, which ->
//                        Log.d("Main", "Negative Button Clicked")
//                    }
//                    setView(dialoglayout)
//                    show()
//                }
//            }
//
//
//            //commentBox
//
//
//            val CommentBox: TextView = findViewById(R.id.commentStart)
//            CommentBox.setOnClickListener {
//                val builder = AlertDialog.Builder(this)
//                val inflater = layoutInflater
//                val dialoglayout = inflater.inflate(R.layout.startcomments, null)
////                val editText: EditText = findViewById(R.id.CommentsEditText)
////                var strings: String = editText.text.toString()
////                entry.comment = strings
//                with(builder) {
//                    setTitle("Comments")
//                    setPositiveButton("OK") { dialog, which -> }
//                    setNegativeButton("Cancel") { dialog, which ->
//                        Log.d("Main", "Negative Button Clicked")
//                    }
//                    setView(dialoglayout)
//                    show()
//                }
//            }
//
//            var extra = intent.extras
//            if (extra != null) {
//                inputType = extra.getString("inputType","")
//                activityType = extra.getString("ActivityType","")
//            }
//
//
//            val button: Button = findViewById(R.id.button)
//            button.setOnClickListener{
//                viewModel.insert(entry)
//            }
//
//
//        }