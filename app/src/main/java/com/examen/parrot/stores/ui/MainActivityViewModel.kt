package com.examen.parrot.stores.ui


import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.examen.parrot.stores.data.OnUpdateDataListener
import com.examen.parrot.stores.domain.RequestUpdateProduct
import com.examen.parrot.stores.domain.ResponseUpdateProduct
import com.examen.parrot.stores.framework.Product
import com.examen.parrot.stores.framework.StoreEntity
import com.examen.parrot.stores.interactors.GetStores
import com.examen.parrot.utils.Extensions.default
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class MainActivityViewModel @ViewModelInject constructor(private val stores: GetStores) :
    ViewModel() {

    //Token
    var token = MutableLiveData<String>()

    //Loading
    var loader = MutableLiveData<Boolean>().default(false)

    //Data
    private var _storesList = MutableLiveData<HashMap<String, StoreEntity>>()
    val storesList: LiveData<HashMap<String, StoreEntity>>
        get() = _storesList

    private var _productsList = MutableLiveData<List<Product>>()
    val productsList: LiveData<List<Product>>
        get() = _productsList

    fun setListener(OnUpdateDataListener: OnUpdateDataListener) {
        stores.setListener(OnUpdateDataListener)
    }


    fun getStores(token: String) {
        showLoading(true)
        viewModelScope.launch {
            _storesList.value = stores.getStores("Bearer $token")
        }
    }

    fun getProducts(token: String, storeId: String) {
        showLoading(true)
        viewModelScope.launch {
            _productsList.value = stores.getProducts("Bearer $token", storeId)
        }
    }

    var update={
            data:MutableLiveData<ResponseUpdateProduct>,token: String, productId:String, requestUpdateProduct: RequestUpdateProduct->
        viewModelScope.launch {
            withContext(Dispatchers.Main){
               data.value= stores.updateProduct("Bearer $token",productId,requestUpdateProduct)
            }
        }
    }


    fun showLoading(isLoading: Boolean) {
        loader.value = isLoading
    }

}