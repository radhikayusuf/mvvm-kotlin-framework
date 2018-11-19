package radhika.yusuf.id.mvvmkotlin.data.source.local

import android.content.Context
import radhika.yusuf.id.mvvmkotlin.data.model.HeroesModel
import radhika.yusuf.id.mvvmkotlin.data.source.DataSource
import radhika.yusuf.id.mvvmkotlin.data.source.local.room.AppDatabase
import radhika.yusuf.id.mvvmkotlin.data.source.local.room.HeroesDao

class LocalDataSource(context: Context) : DataSource {

    private val mHeroesTable: HeroesDao by lazy {
        AppDatabase.getInstance(context).heroesDao()
    }

    override fun saveListHeroes(data: List<HeroesModel>) {
        mHeroesTable.deleteAllHeroes()
        for (model in data) {
            mHeroesTable.insertHeroes(model)
        }
    }

    override fun getListHeroes(refresh: Boolean, callback: DataSource.GetHeroesCallback) {
        try {
            val data = (mHeroesTable.retrievedHeroes()?: arrayListOf())
            if (data.isNotEmpty()) {
                callback.onSuccess(data)
            }
        } catch (e: Exception){
            callback.onError(e.message?:e.localizedMessage)
        }

    }


}