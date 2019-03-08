package radhika.yusuf.id.mvvmkotlin.mvvm.home

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import radhika.yusuf.id.mvvmkotlin.R
import radhika.yusuf.id.mvvmkotlin.databinding.FragmentHomeBinding
import radhika.yusuf.id.mvvmkotlin.utils.base.BaseFragment
import radhika.yusuf.id.mvvmkotlin.utils.chocohelper.ChocoBinding
import radhika.yusuf.id.mvvmkotlin.utils.chocohelper.ChocoChips
import radhika.yusuf.id.mvvmkotlin.utils.chocohelper.ChocoViewModel


class HomeFragment : BaseFragment<HomeViewModel>(), HomeUserActionListener {

    @ChocoBinding(R.layout.fragment_home)
    lateinit var mViewDataBinding: FragmentHomeBinding

    @ChocoViewModel
    lateinit var mViewModel: HomeViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        ChocoChips.inject<FragmentHomeBinding, HomeViewModel, HomeUserActionListener>(this)
        return mViewDataBinding.root
    }

    override fun setContentData() {
        mViewDataBinding.recyclerHome.adapter = HomeAdapter(mViewModel)
        mViewDataBinding.recyclerHome.layoutManager = LinearLayoutManager(context)
    }

    override fun onCreateObserver(viewModel: HomeViewModel) {
        viewModel.apply {
            eventClickItem.observe(this@HomeFragment, Observer {
                if (it != null) {
                    onClickItem(it)
                }
            })
        }
    }

    override fun onRefreshPage() {
        mViewModel.refreshData()
    }

    override fun setMessageType(): String {
        return MESSAGE_TYPE_SNACK_CUSTOM
    }

    override fun onDestroyObserver(viewModel: HomeViewModel) {
        viewModel.eventClickItem.removeObservers(this)
    }

    override fun onClickItem(data: HomeModel) {
        Toast.makeText(activity, data.title, Toast.LENGTH_SHORT).show()
    }

    companion object {
        fun newInstance() = HomeFragment().apply {

        }

    }

}
