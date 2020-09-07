package com.backbase.assignment.ui.features.movieList.viewmodel

import android.app.Application
import androidx.lifecycle.*
import androidx.paging.PagedList
import com.backbase.assignment.ui.data.MovieDataSource
import com.backbase.assignment.ui.data.MoviesDataSourceFactory
import com.backbase.assignment.ui.data.State
import com.backbase.assignment.ui.model.Results
import com.backbase.assignment.ui.network.Api
import com.backbase.assignment.ui.network.AppServiceRepo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MovieListViewModel(application: Application) : AndroidViewModel(application) {
    private val serviceAPI: Api = Api()

    // initialise disposable object to dump api calls
    private val disposable: CompositeDisposable = CompositeDisposable()
    val movieNowPlaylingList: MutableLiveData<List<Results>?> = MutableLiveData()
    val error: MutableLiveData<String?> = MutableLiveData()
    private val  appServiceRepo: AppServiceRepo

    init {
        appServiceRepo = AppServiceRepo(application.baseContext)
    }


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