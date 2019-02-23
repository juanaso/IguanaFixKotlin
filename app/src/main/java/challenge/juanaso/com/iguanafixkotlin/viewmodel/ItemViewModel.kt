package challenge.juanaso.com.iguanafixkotlin.viewmodel

import android.arch.lifecycle.MutableLiveData
import challenge.juanaso.com.iguanafixkotlin.model.User

class ItemViewModel: BaseViewModel() {
    private val name = MutableLiveData<String>()
    private val birthDay = MutableLiveData<String>()

    fun bind(user: User){
        name.value = user.fullName
        birthDay.value = user.getBirthdayFormated()
    }

    fun getName():MutableLiveData<String>{
        return name
    }

    fun getBirthDay():MutableLiveData<String>{
        return birthDay
    }
}