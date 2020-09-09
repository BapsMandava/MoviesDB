package com.backbase.assignment.ui.features.movieDetails.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.backbase.assignment.ui.ServiceType
import com.backbase.assignment.ui.model.MoviesDetailsResult
import com.backbase.assignment.ui.repository.AppServiceRepo
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.koin.core.parameter.parametersOf

class MovieDetailsViewModel() :  ViewModel(), KoinComponent {
    val movieDetails: MutableLiveData<MoviesDetailsResult> = MutableLiveData()
    val error: MutableLiveData<String?> = MutableLiveData()
    private val appServiceRepo: AppServiceRepo by inject { parametersOf(ServiceType.API) }

    /**
     *  call service to fetch movie list
     */
    fun fetchMovieDetails(id:Int?) {
        appServiceRepo.getMovieDetails(id,
            onSuccess = { response ->
                movieDetails.postValue(response)
            }, onError = {
                error.postValue(it)
            })
    }

    override fun onCleared() {
        appServiceRepo.dispose()
        super.onCleared()
    }
}