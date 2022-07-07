package com.example.myruns2.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.lifecycle.ViewModelProvider
import androidx.preference.PreferenceFragment
import androidx.preference.PreferenceFragmentCompat
import com.example.myruns2.*
import kotlinx.coroutines.selects.select

//// TODO: Rename parameter arguments, choose names that match
//// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"
//
///**
// * A simple [Fragment] subclass.
// * Use the [StartFragment.newInstance] factory method to
// * create an instance of this fragment.
// */
class HistoryFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var database: ExerciseDatabase
    private lateinit var databasedao: ActivityDatabaseDao
    private lateinit var repository: ExerciseEntryRepository
    private lateinit var viewModel: ExerciseViewModel
    private lateinit var factory: ExerciseViewModelFactory
    private lateinit var myList: ListView
    private lateinit var mylistAdapter: myListAdapter
    private lateinit var arrayList: ArrayList<Table>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_history, container, false)

        myList = view.findViewById(R.id.ListViewHistory)
        database = ExerciseDatabase.getInstance(requireActivity())
        databasedao = database.activityDao
        repository = ExerciseEntryRepository(databasedao)
        factory = ExerciseViewModelFactory(repository)
        viewModel = ViewModelProvider(requireActivity(), factory).get(ExerciseViewModel::class.java)

        viewModel.exerciseLiveData.observe(requireActivity()) {
            mylistAdapter.update(it)
            mylistAdapter.notifyDataSetChanged()
        }
        arrayList = ArrayList()
        mylistAdapter = myListAdapter(requireActivity(), arrayList)
        myList.adapter = mylistAdapter

        myList.setOnItemClickListener{
            _,_, position,_ ->
         var selectedItem: Table? = viewModel.selected(position)
         val intent = Intent(context, MainActivity2::class.java)
         var a = selectedItem?.id
         var b = selectedItem?.inputType
         var c = selectedItem?.activityType
         var d = selectedItem?.dateTime
         var e = selectedItem?.duration
         var f = selectedItem?.distance
         var g = selectedItem?.calorie
         var h = selectedItem?.heartRate

         intent.putExtra("id",a.toString())
         intent.putExtra("input", b.toString())
         intent.putExtra("activity", c.toString())
         intent.putExtra("datetime", d.toString())
         intent.putExtra("duration", e.toString())
         intent.putExtra("distance", f.toString())
         intent.putExtra("calories", g.toString())
         intent.putExtra("heartRate", h.toString())

         startActivity(intent)
//            val intent1 = Intent(viewID.context, ManualActivity::class.java)
//            intent1.putExtra("inputType",inputSpinner.selectedItemPosition.toString())
//            intent1.putExtra("ActivityType",spinner2.selectedItemPosition.toString())
//            println(inputSpinner.selectedItem.toString()+ "     "+spinner2.selectedItem.toString())
//            startActivity(intent1)


        }
        return view
    }
}