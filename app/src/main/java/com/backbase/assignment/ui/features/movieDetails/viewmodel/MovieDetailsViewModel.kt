package com.backbase.assignment.ui.features.movieDetails.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.backbase.assignment.ui.data.State
import com.backbase.assignment.ui.model.MoviesDetailsResult
import com.backbase.assignment.ui.model.Results
import com.backbase.assignment.ui.network.Api
import com.backbase.assignment.ui.network.AppServiceRepo
import io.reactivex.disposables.CompositeDisposable

class MovieDetailsViewModel(application: Application) : AndroidViewModel(application) {
    val movieDetails: MutableLiveData<MoviesDetailsResult> = MutableLiveData()
    val error: MutableLiveData<String?> = MutableLiveData()
    private val  appServiceRepo: AppServiceRepo

    init {
        appServiceRepo = AppServiceRepo(application.baseContext)
    }


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