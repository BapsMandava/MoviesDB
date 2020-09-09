package com.backbase.assignment.ui.features.movieList.viewmodel

import android.app.Application
import androidx.lifecycle.*
import androidx.paging.PagedList
import com.backbase.assignment.ui.ServiceType
import com.backbase.assignment.ui.data.State
import com.backbase.assignment.ui.model.Results
import com.backbase.assignment.ui.repository.AppServiceRepo
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.koin.core.parameter.parametersOf

class MovieListViewModel() : ViewModel(), KoinComponent {
    val movieNowPlaylingList: MutableLiveData<List<Results>?> = MutableLiveData()
    val error: MutableLiveData<String?> = MutableLiveData()
    private val appServiceRepo: AppServiceRepo by inject { parametersOf(ServiceType.API) }

    /**
     *  call service to fetch movie list
     */
    fun fetchMovieList() {
        appServiceRepo.getNowPlayingList(
            onSuccess = { response ->
                movieNowPlaylingList.postValue(response)
            }, onError = {
                error.postValue(it)
            })
    }


    fun getState(): LiveData<State> {
       return appServiceRepo.getState()
    }

    fun listIsEmpty(): Boolean {
        return appServiceRepo.listIsEmpty()
    }

    fun getMovieListResults(): LiveData<PagedList<Results>> {
        return appServiceRepo.getMovieListResults()
    }

    fun retry() {
        appServiceRepo.retry()
    }

    override fun onCleared() {
        appServiceRepo.dispose()
        super.onCleared()
    }
}