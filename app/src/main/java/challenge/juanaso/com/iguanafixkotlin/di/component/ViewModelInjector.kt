package challenge.juanaso.com.iguanafixkotlin.di.component

import challenge.juanaso.com.iguanafixkotlin.di.module.NetworkModule
import challenge.juanaso.com.iguanafixkotlin.viewmodel.DetailViewModel
import challenge.juanaso.com.iguanafixkotlin.viewmodel.MainViewModel
import dagger.Component
import javax.inject.Singleton

/**
 * Component providing inject() methods for presenters.
 */
@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {
    /**
     * Injects required dependencies into the specified PostListViewModel.
     * @param postListViewModel PostListViewModel in which to inject the dependencies
     */
    fun inject(mainViewModel: MainViewModel)
    fun inject(detailViewModel: DetailViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun networkModule(networkModule: NetworkModule): Builder
    }
}