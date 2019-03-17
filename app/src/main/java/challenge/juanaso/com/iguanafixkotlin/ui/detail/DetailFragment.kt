package challenge.juanaso.com.iguanafixkotlin.ui.detail

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import challenge.juanaso.com.iguanafixkotlin.R


class DetailFragment : Fragment() {
    private var param1: String? = null
    private val ARG_PARAM1 = "param1"

    companion object {
        fun newInstance() = DetailFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
           // param1 = it.getString(ARG_PARAM1)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

}
