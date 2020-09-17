package com.examen.parrot.stores.data

import java.util.HashMap

interface onUpdateDataListener  {
    fun onDataUpdate(stores: HashMap<String, List<String>>)
}