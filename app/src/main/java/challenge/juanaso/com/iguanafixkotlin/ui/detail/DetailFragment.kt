package challenge.juanaso.com.iguanafixkotlin.ui.detail

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import challenge.juanaso.com.iguanafixkotlin.R
import challenge.juanaso.com.iguanafixkotlin.utils.USER_ID
import challenge.juanaso.com.iguanafixkotlin.viewmodel.DetailViewModel


class DetailFragment : Fragment() {
    private var userId: String? = null
    private lateinit var viewModel: DetailViewModel


    companion object {
        fun newInstance() = DetailFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            userId = it.getString(USER_ID)
        }
        viewModel = DetailViewModel(userId!!)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

}
