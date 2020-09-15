package com.examen.parrot.ui.login.categories

import android.os.Bundle
import android.view.View
import android.widget.ExpandableListAdapter
import android.widget.ExpandableListView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.examen.parrot.R
import com.examen.parrot.databinding.ActivityMainBinding
import java.util.*


class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    private lateinit var expandableListAdapter: CateoriesAdapter
    private lateinit var expandableListTitle: List<String>
    private lateinit var expandableListDetail: HashMap<String, List<String>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main)

        expandableListDetail = getData()
        expandableListTitle = ArrayList(expandableListDetail?.keys)
        expandableListAdapter = CateoriesAdapter(this, expandableListTitle, expandableListDetail)
        binding.categoriesRv.setAdapter(expandableListAdapter)


    }

    private fun getData(): HashMap<String, List<String>> {
        val expandableListDetail = HashMap<String, List<String>>()
        val technology: MutableList<String> = ArrayList()
        technology.add("Beats sued for noise-cancelling tech")
        technology.add("Wikipedia blocks US Congress edits")
        technology.add("Google quizzed over deleted links")
        technology.add("Nasa seeks aid with Earth-Mars links")
        technology.add("The Good, the Bad and the Ugly")
        val entertainment: MutableList<String> = ArrayList()
        entertainment.add("Goldfinch novel set for big screen")
        entertainment.add("Anderson stellar in Streetcar")
        entertainment.add("Ronstadt receives White House honour")
        entertainment.add("Toronto to open with The Judge")
        entertainment.add("British dancer return from Russia")
        val science: MutableList<String> = ArrayList()
        science.add("Eggshell may act like sunblock")
        science.add("Brain hub predicts negative events")
        science.add("California hit by raging wildfires")
        science.add("Rosetta's comet seen in close-up")
        science.add("Secret of sandstone shapes revealed")
        expandableListDetail["TECHNOLOGY NEWS"] = technology
        expandableListDetail["ENTERTAINMENT NEWS"] = entertainment
        expandableListDetail["SCIENCE & ENVIRONMENT NEWS"] = science
        return expandableListDetail
    }
}