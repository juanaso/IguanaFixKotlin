package challenge.juanaso.com.iguanafixkotlin.ui.main

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.annotation.StringRes
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import challenge.juanaso.com.iguanafixkotlin.R
import challenge.juanaso.com.iguanafixkotlin.databinding.MainFragmentBinding
import challenge.juanaso.com.iguanafixkotlin.viewmodel.MainViewModel



class MainFragment : Fragment() {

    private lateinit var binding: MainFragmentBinding
    private lateinit var viewModel: MainViewModel
    private var errorSnackbar: Snackbar? = null

    companion object {
        fun newInstance() = MainFragment()
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false)

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        binding.userRecycler.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        viewModel.errorMessage.observe(this, Observer {
            errorMessage -> if(errorMessage != null) showError(errorMessage) else hideError()
        })
        binding.viewModel = viewModel

        return binding.getRoot();
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    private fun showError(@StringRes errorMessage:Int){
        errorSnackbar = Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_INDEFINITE)
        errorSnackbar?.setAction(R.string.retry, viewModel.errorClickListener)
        errorSnackbar?.show()
    }

    private fun hideError(){
        errorSnackbar?.dismiss()
    }

}
