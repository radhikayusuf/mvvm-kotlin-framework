package radhika.yusuf.id.mvvmkotlin.data.source

import radhika.yusuf.id.mvvmkotlin.data.model.HeroesModel

interface DataSource {


    fun getListHeroes(
        refresh: Boolean = false,
        callback: GetHeroesCallback
    )

    fun saveListHeros(data: List<HeroesModel>)

    interface GetHeroesCallback{

        fun onSuccess(data: List<HeroesModel>)

        fun onError(errorMessage: String)

        fun onFinish()
    }

}