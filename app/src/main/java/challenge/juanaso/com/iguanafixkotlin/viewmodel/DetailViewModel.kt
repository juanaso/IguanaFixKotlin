package challenge.juanaso.com.iguanafixkotlin.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.view.View
import challenge.juanaso.com.iguanafixkotlin.model.User
import challenge.juanaso.com.iguanafixkotlin.network.RetrofitWebService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DetailViewModel(private val id :String): BaseViewModel(){

    @Inject
    lateinit var retrofitWebService: RetrofitWebService

    private lateinit var subscription: Disposable

    val currentUser: MutableLiveData<User> = MutableLiveData()

    val photoUrl: MutableLiveData<String> = MutableLiveData()

    init {
        loadUser()
    }

    private fun loadUser() {
        subscription = retrofitWebService.getUser(id)
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
    }

    private fun onRetrievePostListFinish(){
    }

    private fun onRetrievePostListSuccess(user: User){
        photoUrl.value = user.photoUrl
    }

    private fun onRetrievePostListError(){
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }
}