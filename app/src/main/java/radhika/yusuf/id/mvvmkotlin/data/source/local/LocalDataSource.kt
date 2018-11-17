package radhika.yusuf.id.mvvmkotlin.data.source.local

import android.content.Context
import radhika.yusuf.id.mvvmkotlin.data.source.DataSource
import radhika.yusuf.id.mvvmkotlin.data.model.HeroesModel
import radhika.yusuf.id.mvvmkotlin.data.source.local.room.AppDatabase
import radhika.yusuf.id.mvvmkotlin.data.source.local.room.HeroesDao

class LocalDataSource(context: Context) : DataSource {

    private val mHeroesTable: HeroesDao by lazy {
        AppDatabase.getInstance(context).heroesDao()
    }

    override fun saveListHeros(data: List<HeroesModel>) {
        mHeroesTable.deleteAllHeroes()
        for (model in data) {
            mHeroesTable.insertHeroes(model)
        }
    }

    override fun getListHeroes(refresh: Boolean, callback: DataSource.GetHeroesCallback) {
        try {
            if ((mHeroesTable.retrievedHeroes()?: arrayListOf()).isNotEmpty()) {
                callback.onSuccess(mHeroesTable.retrievedHeroes()!!)
            }
        } catch (e: Exception){
            callback.onError(e.message?:e.localizedMessage)
        }

    }


}