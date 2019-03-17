package challenge.juanaso.com.iguanafixkotlin.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.view.View
import android.widget.Toast
import challenge.juanaso.com.iguanafixkotlin.model.User
import challenge.juanaso.com.iguanafixkotlin.network.RetrofitWebService
import challenge.juanaso.com.iguanafixkotlin.persistence.AppDatabase
import challenge.juanaso.com.iguanafixkotlin.persistence.UserDao
import challenge.juanaso.com.iguanafixkotlin.ui.main.UserAdapter
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainViewModel(private val appDatabase: AppDatabase) : BaseViewModel() {

    @Inject
    lateinit var retrofitWebService: RetrofitWebService

    private lateinit var subscription: Disposable

    val userAdapter: UserAdapter = UserAdapter( { user : User -> onItemClick(user) })
    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage:MutableLiveData<Int> = MutableLiveData()
    val userToShowDetail:MutableLiveData<User> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadPosts() }

    init{
        loadPosts()
    }

    private fun loadPosts(){
        var userDao = appDatabase.userDao()
        subscription = Observable.fromCallable { userDao.all }
                .concatMap {
                    dbUserList ->
                    if(dbUserList.isEmpty())
                        retrofitWebService.getUsers().concatMap {
                            apiUsers-> userDao.insertAll(apiUsers)
                            Observable.just(apiUsers)
                        }
                    else
                        Observable.just(dbUserList)
                }
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
        loadingVisibility.value = View.GONE
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

    private fun onItemClick(user: User){
        userToShowDetail.value = user
    }
}

