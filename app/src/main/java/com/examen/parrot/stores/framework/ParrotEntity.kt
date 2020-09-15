package com.examen.parrot.stores.framework

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Test")
class ParrotEntity( @PrimaryKey(autoGenerate = true)
                    var id: Int)