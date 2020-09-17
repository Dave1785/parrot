package com.examen.parrot.utils

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
                saveCallResult.invoke(responseStore.data?.result?.stores)
                responseStore.data?.result?.stores
            }else{
                databaseQuery.invoke()
            }
            res
        }


    }catch (e:Exception){
        null
    }


}
