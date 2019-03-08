package radhika.yusuf.id.mvvmkotlin.mvvm.home


import android.databinding.ObservableList
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import radhika.yusuf.id.mvvmkotlin.databinding.ItemHomeBinding


class HomeAdapter(private val mViewModel: HomeViewModel) :
    RecyclerView.Adapter<HomeAdapter.HomeItem>() {

    private var mData = mViewModel.homeList

    override fun onBindViewHolder(holder: HomeItem, position: Int) {
        holder.bind(mData[position], mViewModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeItem {
        val binding = ItemHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeItem(binding)
    }

    override fun getItemCount(): Int {
        return mData.size
    }


    fun replaceData(data: List<HomeModel>) {
        mData = data as ObservableList<HomeModel>
        notifyDataSetChanged()
    }


    class HomeItem(private val mBinding: ItemHomeBinding) : RecyclerView.ViewHolder(mBinding.root) {

        fun bind(data: HomeModel, viewModel: HomeViewModel) {
            mBinding.mData = data
            mBinding.mListener = object : HomeUserActionListener {
                override fun onRefreshPage() {

                }

                override fun onClickItem(data: HomeModel) {
                    viewModel.eventClickItem.value = data
                }

            }
        }

    }

}