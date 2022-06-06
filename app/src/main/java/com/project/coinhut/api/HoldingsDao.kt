package com.project.coinhut.api

import androidx.room.*
import com.project.coinhut.database.AssetHoldingEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface HoldingsDao {

    @Query("SELECT * FROM AssetHoldingEntity")
    fun getAll(): Flow<List<AssetHoldingEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAssetHolding(assetHolding: AssetHoldingEntity)

    @Delete
    fun deleteAssetHolding(assetHolding: AssetHoldingEntity)

    @Query("SELECT * FROM AssetHoldingEntity WHERE id = :assetId")
    fun getAssetHolding(assetId: String): Flow<AssetHoldingEntity>
}