package radhika.yusuf.id.mvvmkotlin.mvvm.home;

import android.app.Application
import android.databinding.ObservableArrayList
import android.databinding.ObservableList
import radhika.yusuf.id.mvvmkotlin.R
import radhika.yusuf.id.mvvmkotlin.data.source.DataSource
import radhika.yusuf.id.mvvmkotlin.data.model.HeroesModel
import radhika.yusuf.id.mvvmkotlin.utils.base.BaseViewModel
import radhika.yusuf.id.mvvmkotlin.utils.helper.SingleLiveEvent


class HomeViewModel(context: Application) : BaseViewModel(context) {

    val homeList: ObservableList<HomeModel> = ObservableArrayList()
    val eventClickItem = SingleLiveEvent<HomeModel>()

    override fun start() {
        super.start()
        showMessageRes.value = R.string.text_hello_from_radhika
        loadData()
    }


    private fun loadData(refresh: Boolean = false) {
        isRequesting.set(true)
        mRepository.getListHeroes(refresh, object : DataSource.GetHeroesCallback {
            override fun onSuccess(data: List<HeroesModel>) {
                homeList.clear()
                homeList.addAll(data.map { HomeModel(it.name ?: "-", it.bio ?: "-", it.imageurl ?: "-") })
            }

            override fun onError(errorMessage: String) {
                showMessage.value = errorMessage
                isRequesting.set(false)

            }

            override fun onFinish() {
                isRequesting.set(false)
            }
        })

    }

    fun refreshData() {
        showMessageRes.value = R.string.text_refreshing
        homeList.clear()
        loadData(refresh = true)
    }

}


