package challenge.juanaso.com.iguanafixkotlin.ui.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.annotation.StringRes
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.*
import android.view.inputmethod.EditorInfo
import challenge.juanaso.com.iguanafixkotlin.MainActivity
import challenge.juanaso.com.iguanafixkotlin.R
import challenge.juanaso.com.iguanafixkotlin.databinding.MainFragmentBinding
import challenge.juanaso.com.iguanafixkotlin.di.ViewModelFactory
import challenge.juanaso.com.iguanafixkotlin.utils.USER_ID
import challenge.juanaso.com.iguanafixkotlin.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {

    private lateinit var binding: MainFragmentBinding
    private lateinit var viewModel: MainViewModel
    private var errorSnackbar: Snackbar? = null

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setSwipeToRefresh()
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        toolbar.inflateMenu(R.menu.user_menu)
        this.setHasOptionsMenu(true)
        viewModel.loadPosts()

    }

    private fun setSwipeToRefresh() {
        main_swipeRefresh.setOnRefreshListener{viewModel.loadPosts()}

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false)

        this.setHasOptionsMenu(true)
        viewModel =   ViewModelProviders.of(this, ViewModelFactory(this.activity!!)).get(MainViewModel::class.java)
        binding.userRecycler.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        viewModel.errorMessage.observe(this, Observer {
            errorMessage -> if(errorMessage != null) showError(errorMessage) else hideError()
        })
        binding.viewModel = viewModel

        viewModel.userToShowDetail.observe(this, Observer {
            if(it!=null){
                val bundle = Bundle()
                bundle.putString(USER_ID, it.id!!)
                ((activity)as MainActivity).navigateToUserDetail(bundle)
                viewModel.userToShowDetail.value = null;
            }
        })
        return binding.root
    }

    private fun showError(@StringRes errorMessage:Int){
        errorSnackbar = Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_INDEFINITE)
        errorSnackbar?.setAction(R.string.retry, viewModel.errorClickListener)
        errorSnackbar?.show()
    }

    private fun hideError(){
        errorSnackbar?.dismiss()
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater!!.inflate(R.menu.user_menu, menu)
        val searchItem = menu!!.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView
        searchView.imeOptions = EditorInfo.IME_ACTION_DONE
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.userAdapter.filter.filter(newText)
                return false
            }
        })
    }
}
