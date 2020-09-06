package com.backbase.assignment.ui.network

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.backbase.assignment.ui.data.MovieDataSource
import com.backbase.assignment.ui.data.MoviesDataSourceFactory
import com.backbase.assignment.ui.data.State
import com.backbase.assignment.ui.model.Results
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class AppServiceRepo(private val context: Context){
    private val serviceAPI: Api = Api()

    // initialise disposable object to dump api calls
    private val disposable: CompositeDisposable = CompositeDisposable()
    private val pageSize = 10
    private val moviesDataSourceFactory: MoviesDataSourceFactory
    var movieList: LiveData<PagedList<Results>>

    init {
        moviesDataSourceFactory = MoviesDataSourceFactory(disposable, serviceAPI.getServiceinterface())
        val config = PagedList.Config.Builder()
            .setPageSize(pageSize)
            .setInitialLoadSizeHint(pageSize * 2)
            .setEnablePlaceholders(false)
            .build()
        movieList = LivePagedListBuilder(moviesDataSourceFactory, config).build()
    }
    /**
     * get movies list from the service
     * @param onSuccess success callback
     * @param onSuccess error callback
     */
    fun getNowPlayingList(
        onSuccess: (List<Results>?) -> Unit,
        onError: (String) -> Unit){
        serviceAPI.getServiceinterface()?.fetchNowPlayingList()
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(
                { playingList -> onSuccess.invoke(playingList.results)
                },
                { error -> onError.invoke(error.toString())
                }
            )?.let { disposable.add(it) }
    }

    fun getPopularPlayingList(pageNo:String,
        onSuccess: (List<Results>?) -> Unit,
        onError: (String) -> Unit){
        disposable.add(serviceAPI.getServiceinterface()!!.fetchPopularPlayingList(pageNo = 1)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                        playingList -> onSuccess.invoke(playingList.results)
                },
                {
                        error -> onError.invoke(error.toString())
                }
            ))
    }

    fun getState(): LiveData<State> = Transformations.switchMap(
        moviesDataSourceFactory.moviesDataSourceLiveData,
        MovieDataSource::state
    )

    fun retry() {
        moviesDataSourceFactory.moviesDataSourceLiveData.value?.retry()
    }

    fun listIsEmpty(): Boolean {
        return movieList.value?.isEmpty() ?: true
    }


    fun getMovieListResults(): LiveData<PagedList<Results>> {
        return movieList
    }

    /**
     * method to dump calls and release memory
     */
    fun dispose() {
        disposable.dispose()
    }
}