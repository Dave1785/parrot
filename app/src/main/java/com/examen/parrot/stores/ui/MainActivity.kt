package com.examen.parrot.stores.ui

import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.work.*
import com.examen.parrot.R
import com.examen.parrot.databinding.ActivityMainBinding
import com.examen.parrot.login.framework.UserPreferences
import com.examen.parrot.shared.framework.RefreshWorker
import com.examen.parrot.stores.data.OnUpdateDataListener
import com.examen.parrot.stores.framework.Product
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit


@AndroidEntryPoint
class MainActivity : AppCompatActivity(), OnUpdateDataListener {

    private val mainActivityViewModel:MainActivityViewModel by viewModels()

    private lateinit var binding:ActivityMainBinding
    private lateinit var expandableListAdapter: CategoriesAdapter
    private lateinit var token:String
    private lateinit var currentStore:String
    private var listStores= mutableListOf<String>()
    private var listProducts= mutableMapOf<String,MutableList<Product>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this, R.layout.activity_main)

        //Token
        token = UserPreferences.getInstance(this).getValue(UserPreferences.DataType.TOKEN) as String
        //mainActivityViewModel.getStores(token)

        expandableListAdapter= CategoriesAdapter(this, listStores, listProducts,this)
        mainActivityViewModel.setListener(this)
        binding.categoriesRv.setAdapter(expandableListAdapter)
        binding.data=mainActivityViewModel
        binding.lifecycleOwner=this

        //Initializing Worker
        enqueueWorker(this)

    }

    override fun onResume() {
        super.onResume()

        mainActivityViewModel.storesList.observe(this, androidx.lifecycle.Observer {
            mainActivityViewModel.showLoading(false)
            if (it != null) {
                Toast.makeText(this, "Response Stores ${it.keys.size}", Toast.LENGTH_LONG).show()

                val keys=it.keys
                listStores=keys.toMutableList()

                for(key in keys){
                    val storeId= it[key]?.uuid ?: ""
                    currentStore=key
                    synchronized(this){mainActivityViewModel.getProducts(token,storeId)}
                }

            }
        })

        mainActivityViewModel.productsList.observe(this, Observer {
            mainActivityViewModel.showLoading(false)

            listProducts[currentStore] = it.toMutableList()
            //mainActivityViewModel.updateProducts(currentStore,it)
            expandableListAdapter.updateChildList(listStores, listProducts )

            Toast.makeText(this,"Se actualizaron productos",Toast.LENGTH_LONG).show()
        })

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when(item.itemId){

            R.id.exit_app -> {
                UserPreferences.getInstance(this).clearData()
                finish()
                true
            }

            else-> super.onOptionsItemSelected(item)
        }
    }

    /**
     * Create worker for refresh data
     */
    private fun enqueueWorker(context: Context) {

        token = UserPreferences.getInstance(this).getValue(UserPreferences.DataType.TOKEN) as String
        val data= Data.Builder()
        data.putString("Token", token)
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresCharging(true)
            .build()
        val work = PeriodicWorkRequestBuilder<RefreshWorker>(1, TimeUnit.MINUTES)
            .setConstraints(constraints)
            .setInputData(data.build())
            .build()
        WorkManager.getInstance(context).enqueue(work)
    }

    /**
     * Callback from worker
     */
    override fun onDataUpdate() {

        runOnUiThread {
            mainActivityViewModel.getStores(token)
            Toast.makeText(this, "Actualizado desde el worker", Toast.LENGTH_LONG).show()
        }

    }



}