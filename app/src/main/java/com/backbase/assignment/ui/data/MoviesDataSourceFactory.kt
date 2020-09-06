package com.backbase.assignment.ui.data

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.backbase.assignment.ui.model.MoviePlayingResult
import com.backbase.assignment.ui.model.Results
import com.backbase.assignment.ui.network.ServiceInterface
import io.reactivex.disposables.CompositeDisposable

class MoviesDataSourceFactory(
    private val compositeDisposable: CompositeDisposable,
    private val networkService: ServiceInterface?)
    : DataSource.Factory<Int, Results>() {

    val moviesDataSourceLiveData = MutableLiveData<MovieDataSource>()

    override fun create(): DataSource<Int, Results> {
        val moviesDataSource = MovieDataSource(networkService, compositeDisposable)
        moviesDataSourceLiveData.postValue(moviesDataSource)
        return moviesDataSource
    }
}