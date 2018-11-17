package radhika.yusuf.id.mvvmkotlin.mvvm.home;

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import radhika.yusuf.id.mvvmkotlin.R
import radhika.yusuf.id.mvvmkotlin.utils.extension.replaceFragmentInActivity


class HomeActivity : AppCompatActivity(), HomeNavigator {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setupFragment()
    }


    private fun setupFragment() {
        supportFragmentManager.findFragmentById(R.id.frame_main_content)
        HomeFragment.newInstance().let {
            replaceFragmentInActivity(it, R.id.frame_main_content)
        }
    }

    companion object {
        fun startActivity(context: Context) {
            context.startActivity(Intent(context, HomeActivity::class.java))
        }
    }
}
