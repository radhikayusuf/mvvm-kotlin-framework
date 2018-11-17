package radhika.yusuf.id.mvvmkotlin.data.source

import radhika.yusuf.id.mvvmkotlin.data.model.HeroesModel

class Repository(private val remoteDataSource: DataSource, private val localDataSource: DataSource) : DataSource {

    override fun getListHeroes(refresh: Boolean, callback: DataSource.GetHeroesCallback) {
        if (!refresh) {
            localDataSource.getListHeroes(callback = callback)
        }

        remoteDataSource.getListHeroes(refresh, object : DataSource.GetHeroesCallback {
            override fun onSuccess(data: List<HeroesModel>) {
                saveListHeros(data)
                localDataSource.getListHeroes(false, callback)
            }

            override fun onError(errorMessage: String) {
                callback.onError(errorMessage)
            }

            override fun onFinish() {
                callback.onFinish()
            }

        })
    }

    override fun saveListHeros(data: List<HeroesModel>) {
        localDataSource.saveListHeros(data)
    }

}