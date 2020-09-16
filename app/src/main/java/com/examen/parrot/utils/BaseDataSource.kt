package com.examen.parrot.utils


import kotlinx.coroutines.Deferred

abstract class BaseDataSource {

    protected suspend fun <T> getResult(call: suspend () -> Deferred<T>): Resource<T> {
        try {
            val response = call()
            if (response.isCompleted) {
                val body = response.await()
                if (body != null) return Resource.success(body)
            }
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
        return Resource.error("fail")
    }

    private fun <T> error(message: String): Resource<T> {
        return Resource.error("Network call has failed for a following reason: $message")
    }

}