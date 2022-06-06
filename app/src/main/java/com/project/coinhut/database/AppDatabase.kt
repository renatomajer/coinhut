package com.project.coinhut.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.project.coinhut.api.HoldingsDao


@Database(
    entities = [AssetHoldingEntity::class],
    version = 1,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun holdingsDao(): HoldingsDao

    companion object {

        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "holdings_database"
                    ).fallbackToDestructiveMigration().build()

                    INSTANCE = instance
                }

                return instance
            }
        }
    }
}