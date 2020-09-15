package com.examen.parrot.stores.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.examen.parrot.stores.framework.Store
import com.examen.parrot.stores.interactors.GetStores
import com.examen.parrot.utils.Extensions.default
import kotlinx.coroutines.launch
import java.util.ArrayList
import java.util.HashMap

class MainActivityViewModel @ViewModelInject constructor(private val stores: GetStores) : ViewModel() {

    //Token
    var token=MutableLiveData<String>()

    //Loading
    var loader=MutableLiveData<Boolean>().default(false)

    //Data
    private var _storesList= MutableLiveData<HashMap<String, List<String>>>()
    val storesList : LiveData<HashMap<String, List<String>>>
        get() = _storesList

    fun getStores(token:String){
        showLoading(true)
        viewModelScope.launch {
         val storeList= stores.getStores("Bearer $token")
          _storesList.value= getData(storeList)
        }
    }

    private fun getData(storeKeys:List<Store>?): HashMap<String, List<String>> {

        val expandableListDetail = HashMap<String, List<String>>()

        if (storeKeys != null) {
            for (store in storeKeys){
                val storeList: MutableList<String> = ArrayList()
                storeList.add("Store")
                expandableListDetail[store.name] = storeList
            }
        }

        return expandableListDetail
    }

    fun showLoading(isLoading:Boolean){
        loader.value=isLoading
    }


}