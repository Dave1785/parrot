package com.examen.parrot.stores.data

import com.examen.parrot.stores.framework.StoreEntity

interface onUpdateDataListener  {
    fun onDataUpdate(stores:List<StoreEntity>?)
}