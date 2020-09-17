package com.examen.parrot.stores.ui

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.examen.parrot.stores.data.onUpdateDataListener
import com.examen.parrot.stores.domain.Store
import com.examen.parrot.stores.framework.StoreEntity
import com.examen.parrot.stores.interactors.GetStores
import com.examen.parrot.utils.Extensions.default
import kotlinx.coroutines.launch
import java.util.ArrayList
import java.util.HashMap

class MainActivityViewModel @ViewModelInject constructor(private val stores: GetStores) : ViewModel(),onUpdateDataListener {

    //Token
    var token=MutableLiveData<String>()

    //Loading
    var loader=MutableLiveData<Boolean>().default(false)

    //Data
    private var _storesList= MutableLiveData<HashMap<String, List<String>>>()
    val storesList : LiveData<HashMap<String, List<String>>>
        get() = _storesList

    fun setListenerData(){
        stores.setListenerData(this)
    }

    fun getStores(token:String){
        showLoading(true)
        viewModelScope.launch {
            _storesList.value=getData(stores.getStores("Bearer $token"))
        }
    }

    private fun getData(storeKeys:List<StoreEntity>?): HashMap<String, List<String>> {

        val expandableListDetail = HashMap<String, List<String>>()

        if (storeKeys != null) {
            for (store in storeKeys){
                val storeList: MutableList<String> = ArrayList()
                storeList.add("Store")
                expandableListDetail[store.uuid] = storeList
            }
        }

        return expandableListDetail
    }

    fun showLoading(isLoading:Boolean){
        loader.value=isLoading
    }

    override fun onDataUpdate(stores: List<StoreEntity>?) {
        _storesList.value=getData(stores)
        Log.d("Info","Se actualizo data desde el worker")
    }


}