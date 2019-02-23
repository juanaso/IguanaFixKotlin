package challenge.juanaso.com.iguanafixkotlin.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.view.View
import challenge.juanaso.com.iguanafixkotlin.model.User
import challenge.juanaso.com.iguanafixkotlin.network.RetrofitWebService
import challenge.juanaso.com.iguanafixkotlin.ui.main.UserAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class MainViewModel : BaseViewModel() {

    @Inject
    lateinit var retrofitWebService: RetrofitWebService

    private lateinit var subscription: Disposable

    val userAdapter: UserAdapter = UserAdapter()
    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage:MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadPosts() }

    init{
        loadPosts()
    }

    private fun loadPosts(){
        subscription = retrofitWebService.getUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { onRetrievePostListStart() }
                .doOnTerminate { onRetrievePostListFinish() }
                .subscribe(
                        { result -> onRetrievePostListSuccess(result) },
                        { onRetrievePostListError() }
                )
    }

    private fun onRetrievePostListStart(){
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onRetrievePostListFinish(){
        loadingVisibility.value = View.GONE
    }

    private fun onRetrievePostListSuccess(users:List<User>){
        userAdapter.updateUsers(users)
    }

    private fun onRetrievePostListError(){

    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }
}

