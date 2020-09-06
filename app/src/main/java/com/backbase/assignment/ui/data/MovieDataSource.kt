package com.backbase.assignment.ui.data

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.backbase.assignment.ui.model.Results
import com.backbase.assignment.ui.network.ServiceInterface
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Action
import io.reactivex.schedulers.Schedulers

class MovieDataSource(
    private val networkService: ServiceInterface?,
    private val compositeDisposable: CompositeDisposable
): PageKeyedDataSource<Int, Results>() {


    var state: MutableLiveData<State> =  MutableLiveData()
    private var retryCompletable: Completable? = null

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Results>) {
        updateState(State.LOADING)
        networkService?.fetchPopularPlayingList(pageNo = 1)
            ?.subscribe(
                { response ->
                    updateState(State.DONE)
                    callback.onResult(response.results,
                        null,
                        2
                    )
                },
                {
                    updateState(State.ERROR)
                    setRetry(Action { loadInitial(params, callback) })
                }
            )?.let {
                compositeDisposable.add(it)
            }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Results>) {
        updateState(State.LOADING)
        networkService?.fetchPopularPlayingList(pageNo = params.key)
            ?.subscribe(
                { response ->
                    updateState(State.DONE)
                    callback.onResult(response.results,
                        params.key + 1
                    )
                },
                {
                    updateState(State.ERROR)
                    setRetry(Action { loadAfter(params, callback) })
                }
            )?.let {
                compositeDisposable.add(it)
            }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Results>) {
    }

    private fun updateState(state: State) {
        this.state.postValue(state)
    }

    fun retry() {
        if (retryCompletable != null) {
            compositeDisposable.add(retryCompletable!!
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe())
        }
    }

    private fun setRetry(action: Action?) {
        retryCompletable = if (action == null) null else Completable.fromAction(action)
    }
}