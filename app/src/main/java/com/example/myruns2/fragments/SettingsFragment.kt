package com.example.myruns2.fragments

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.preference.PreferenceFragment
import com.example.myruns2.R
import com.example.myruns2.RunActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import org.w3c.dom.Text


class SettingsFragment : Fragment(){
        var indexItems =0
        private var unitArray = arrayOf("Metric(Kilometres)","Imperial(Miles)")
        var selectedUnit = unitArray[indexItems]
    private lateinit var sharedpreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_settings, container, false)





        val X: TextView = view.findViewById(R.id.detailsIntent)
        X.setOnClickListener {
            val intent = Intent(this.context, RunActivity::class.java)
            startActivity(intent)
        }


        val webpage: TextView = view.findViewById(R.id.externalLink)
        webpage.movementMethod = LinkMovementMethod.getInstance()

        val unitDialog: TextView = view.findViewById(R.id.UnitPreference)
        unitDialog.setOnClickListener{

            this.context?.let { it1 ->
                MaterialAlertDialogBuilder(it1)
                    .setTitle("Unit Preference")
                    .setSingleChoiceItems(unitArray,indexItems){dialog,which->
                        indexItems = which
                        selectedUnit = unitArray[which]
                    }
                    .setPositiveButton("OK"){dialog, which ->
                        //showUnits(it,"$selectedUnit Selected")
                    }
                    .setNeutralButton("Cancel") {dialog, which->

                    }.show()
            }
        }


        val commentBox: TextView = view.findViewById(R.id.Comments)
        commentBox.setOnClickListener{
            val builder = AlertDialog.Builder(this.context)
            val inflater = layoutInflater
            val dialoglayout = inflater.inflate(R.layout.setting_comments, null)
            val editComment = dialoglayout.findViewById<EditText>(R.id.CommentsEditText)

            with(builder){
                setTitle("Enter your comment")
                setPositiveButton("OK"){dialog, which ->
//                    val preferences : SharedPreferences = this.context.getSharedPreferences("sharedComment", Context.MODE_PRIVATE)
//                    val editor : SharedPreferences.Editor = preferences.edit()
//                    editor.putString("sharedCom", editComment.toString())
//                    editor.apply()
//                    editor.commit()
//                    val getPref : SharedPreferences = this.context.getSharedPreferences("sharedComment", Context.MODE_PRIVATE)
//                    val sharedComment = getPref.getString("sharedCom", "LOLOLOLOL");
                }
                setNegativeButton("Cancel"){dialog, which ->
                    Log.d("Main","Negative Button Clicked")
                }
                setView(dialoglayout)
                show()
            }
        }

        return view
    }





}


private fun showUnits(view: View, msg: String) {
    Snackbar.make(view, msg, Snackbar.LENGTH_LONG).show()
}
