package com.project.coinhut.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AssetHoldingEntity(
    @PrimaryKey(autoGenerate = false)
    var id: String,
    var holding: Double
)