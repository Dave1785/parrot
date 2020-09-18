package com.examen.parrot.utils

import android.util.Log
import com.examen.parrot.stores.framework.Product
import com.examen.parrot.stores.domain.ResponseProducts
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun performGetOperationProduct(

    databaseQuery: () -> List<Product>,
    networkCall: suspend () -> Resource<ResponseProducts>,
    saveCallResult: suspend (store: List<Product>?) -> Unit
): List<Product>? {

    return try {

        var res: List<Product>?

        withContext(Dispatchers.IO) {
            var responseProducts = networkCall.invoke()
            res = if (responseProducts.status == Resource.Status.SUCCESS) {
                Log.d("Info", "Se actualizo info")
                try {
                    saveCallResult.invoke(responseProducts.data?.results)
                } catch (e: Exception) {
                    Log.d("info", "Exception")
                }
                responseProducts.data?.results
            } else {

                Log.d("Info", "Se accedio a la base")
                try {
                    Log.d("Info", "Se obtuvo info de la base")
                    databaseQuery.invoke()
                } catch (e: Exception) {
                    Log.d("Info", "Fallo acceso a la base ")
                    null
                }
            }

            res
        }


    } catch (e: Exception) {
        null
    }

}