package challenge.juanaso.com.iguanafixkotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import challenge.juanaso.com.iguanafixkotlin.ui.main.MainFragment
import challenge.juanaso.com.iguanafixkotlin.R
import challenge.juanaso.com.iguanafixkotlin.ui.detail.DetailFragment
import challenge.juanaso.com.iguanafixkotlin.utils.extension.replaceFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow()
        }
    }

    fun navigateToUserDetail(bundle: Bundle){
        val detailFragment = DetailFragment.newInstance()
        detailFragment.arguments = bundle
        replaceFragment(detailFragment, R.id.container)
    }
}
