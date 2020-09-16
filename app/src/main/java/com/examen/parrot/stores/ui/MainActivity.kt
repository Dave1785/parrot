package com.examen.parrot.stores.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.examen.parrot.R
import com.examen.parrot.databinding.ActivityMainBinding
import com.examen.parrot.login.framework.UserPreferences
import dagger.hilt.android.AndroidEntryPoint
import java.util.*


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainActivityViewModel:MainActivityViewModel by viewModels()

    private lateinit var binding:ActivityMainBinding
    private lateinit var expandableListAdapter: CategoriesAdapter
    private lateinit var expandableListTitle: List<String>
    private lateinit var expandableListDetail: HashMap<String, List<String>>
    private lateinit var token:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main)

        //Token
        token = UserPreferences.getInstance(this).getValue(UserPreferences.DataType.TOKEN) as String
        mainActivityViewModel.getStores(token)

        binding.data=mainActivityViewModel
        binding.lifecycleOwner=this

    }

    override fun onResume() {
        super.onResume()

        mainActivityViewModel.storesList.observe(this, androidx.lifecycle.Observer {
            mainActivityViewModel.showLoading(false)
            if(it!=null){
                Toast.makeText(this,"Response Stores ${it.keys.size}",Toast.LENGTH_LONG).show()
                expandableListDetail = it
                expandableListTitle = ArrayList(expandableListDetail?.keys)
                expandableListAdapter = CategoriesAdapter(this, expandableListTitle, expandableListDetail)
                binding.categoriesRv.setAdapter(expandableListAdapter)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when(item.itemId){

            R.id.exit_app->{
                UserPreferences.getInstance(this).clearData()
                finish()
                true
            }

            else-> super.onOptionsItemSelected(item)
        }
    }


}