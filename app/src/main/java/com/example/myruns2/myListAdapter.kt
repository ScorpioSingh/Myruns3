package com.example.myruns2

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import org.w3c.dom.Text
import java.text.FieldPosition

class myListAdapter(val context: Context,private var exerciseList: List<Table>): BaseAdapter() {
    override fun getItem(position: Int):Any {
        return exerciseList[position]
    }

    override fun getItemId(position: Int):Long{
        return position.toLong()
    }


    override fun getCount():Int{
        return exerciseList.size
    }

    override fun getView(position: Int, view: View?, parent: ViewGroup?): View{
        val view = View.inflate(context,R.layout.layout_adapter,null)

        val types_of_input = arrayOf("Manual Entry", "Gps", "Automatic")

        val exerciseTypes = arrayOf(
            "Running",
            "Walking",
            "Standing",
            "Cycling",
            "Hiking",
            "Downhill Skiing",
            "Snowboarding",
            "Skating",
            "Swimming",
            "Mountain Biking",
            "Wheelchair",
            "Elliptical",
            "Other")

        val Input = view.findViewById<TextView>(R.id.inputType)
        Input.text = types_of_input[exerciseList[position].inputType] + ","

        val activity = view.findViewById<TextView>(R.id.activityType)
        activity.text = exerciseTypes[exerciseList[position].activityType]+","

        val datetime = view.findViewById<TextView>(R.id.dateTime)
        datetime.text = exerciseList[position].dateTime.toString()+","

        val miles = view.findViewById<TextView>(R.id.miles)
        miles.text = exerciseList[position].distance.toString()+"miles, "

        val exerciseTime = view.findViewById<TextView>(R.id.exerciseTime)
        exerciseTime.text = exerciseList[position].duration.toString()+"minutes,"

        return view
    }


    fun update(newList: List<Table>){
        exerciseList = newList
    }

}