package com.examen.parrot.framework.database.entitys

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Test")
class ParrotEntity( @PrimaryKey(autoGenerate = true)
                    var id: Int)