package com.example.myruns2.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.myruns2.R
import com.example.myruns2.ManualActivity
//
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import com.example.myruns2.MapActivity


class StartFragment : Fragment() {

    private lateinit var viewID: View
    private lateinit var btn3: Button
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewID = inflater.inflate(R.layout.fragment_start, container, false)

        lateinit var adapter2: ArrayAdapter<String>
        var inputData2 = arrayListOf<String>("Standing","Cycling","Hiking","Downhill Skiing","Cross-Country Skiing","Snowboarding","Skating"
            ,"Swimming","Mountain Biking","WheelChair","Elliptical","Other")


        adapter2 = ArrayAdapter(requireContext().applicationContext,android.R.layout.simple_spinner_dropdown_item,inputData2)
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        var spinner2:Spinner = viewID.findViewById(R.id.spinnerActivity) as Spinner
        spinner2.adapter = adapter2


        val inputSpinner = viewID.findViewById<View>(R.id.spinnerInput) as Spinner
        lateinit var adapter: ArrayAdapter<String>
        var inputData = arrayListOf<String>("Manual","GPS","Automatic")

        adapter = ArrayAdapter(requireContext().applicationContext,android.R.layout.simple_spinner_dropdown_item,inputData)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        inputSpinner.adapter = adapter

        inputSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parentView: AdapterView<*>,
                selectedItemView: View?,
                position: Int,
                id: Long
            ) {
                val button: Button = viewID.findViewById(R.id.btnStart)
                button.setOnClickListener {
                    if (parentView.getItemAtPosition(position).toString() == "Manual") {
                        val intent1 = Intent(viewID.context, ManualActivity::class.java)
                        intent1.putExtra("inputType",inputSpinner.selectedItemPosition.toString())
                        intent1.putExtra("ActivityType",spinner2.selectedItemPosition.toString())
                        println(inputSpinner.selectedItem.toString()+ "     "+spinner2.selectedItem.toString())
                        startActivity(intent1)
                    }
                    else {
                        val intent1 = Intent(viewID.context, MapActivity::class.java)
                        startActivity(intent1)
                    }
                }
            }

            override fun onNothingSelected(parentView: AdapterView<*>?) {
                // your code here
            }

        }
        //



        return viewID
    }
}
