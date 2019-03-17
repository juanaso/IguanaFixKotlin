package challenge.juanaso.com.iguanafixkotlin.viewmodel

import android.arch.lifecycle.ViewModel
import challenge.juanaso.com.iguanafixkotlin.di.component.DaggerViewModelInjector
import challenge.juanaso.com.iguanafixkotlin.di.component.ViewModelInjector
import challenge.juanaso.com.iguanafixkotlin.di.module.NetworkModule

abstract class BaseViewModel: ViewModel(){


    private val injector: ViewModelInjector = DaggerViewModelInjector
            .builder()
            .networkModule(NetworkModule)
            .build()
    init {
        inject()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is MainViewModel -> injector.inject(this)
            is DetailViewModel -> injector.inject(this)
        }
    }
}