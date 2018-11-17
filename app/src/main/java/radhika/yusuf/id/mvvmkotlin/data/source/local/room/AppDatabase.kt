package radhika.yusuf.id.mvvmkotlin.data.source.local.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import radhika.yusuf.id.mvvmkotlin.data.model.HeroesModel


@Database(entities = [(HeroesModel::class)], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun heroesDao(): HeroesDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also {
                    INSTANCE = it
                }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java, "RadhikaApps.db")
                .allowMainThreadQueries()
                .build()
    }
}