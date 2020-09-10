package ccom.backbase.assignment.ui.di

import com.backbase.assignment.ui.ServiceType
import com.backbase.assignment.ui.features.movieDetails.viewmodel.MovieDetailsViewModel
import com.backbase.assignment.ui.features.movieList.viewmodel.MovieListViewModel
import com.backbase.assignment.ui.network.Api
import com.backbase.assignment.ui.repository.AppServiceRepo
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel

/**
 * App Module to load all the Koin injections
 *
 */
val appModule= module {
    factory { (serviceType : ServiceType) -> AppServiceRepo(serviceType) }
    factory { (serviceType : ServiceType) -> Api(serviceType) }
    viewModel { MovieListViewModel() }
    viewModel { MovieDetailsViewModel() }
}