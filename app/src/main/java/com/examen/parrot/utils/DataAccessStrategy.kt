package com.examen.parrot.utils

import android.util.Log
import com.examen.parrot.stores.domain.ResponseStore
import com.examen.parrot.stores.framework.StoreEntity
import com.examen.parrot.utils.Resource.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


suspend fun performGetOperation(
    databaseQuery: () -> List<StoreEntity>,
    networkCall: suspend () -> Resource<ResponseStore>,
    saveCallResult: suspend (store: List<StoreEntity>?) -> Unit): List<StoreEntity>? {

/*    liveData(Dispatchers.IO) {
        emit(Resource.loading())
        val source = databaseQuery.invoke().map { Resource.success(it) }
        emitSource(source)

        val responseStatus = networkCall.invoke()
        if (responseStatus.status == Status.SUCCESS) {
            saveCallResult(responseStatus.data!!)

        } else if (responseStatus.status == Status.ERROR) {
            emit(Resource.error(responseStatus.message!!))
            emitSource(source)
        }
    }*/

    return try{
        var res:List<StoreEntity>?=null

        withContext(Dispatchers.IO){
            var responseStore=networkCall.invoke()
            res = if(responseStore.status==Status.SUCCESS){
                Log.d("Info","Se actualizo info")
                saveCallResult.invoke(responseStore.data?.result?.stores)
                responseStore.data?.result?.stores
            }else{

                Log.d("Info","Se accedio a la base")
                try {
                    Log.d("Info","Se obtuvo info de la base")
                    databaseQuery.invoke()
                }catch (e:Exception){
                    Log.d("Info","Fallo acceso a la base ")
                     null
                }
            }

            res
        }




    }catch (e:Exception){
        null
    }


}
