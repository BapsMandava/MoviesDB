package com.backbase.assignment.ui.features.movieList

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.backbase.assignment.R
import com.backbase.assignment.ui.BaseActivity
import com.backbase.assignment.ui.data.State
import com.backbase.assignment.ui.features.movieDetails.MovieDetailsActivity
import com.backbase.assignment.ui.features.movieList.adapters.MoviesListAdapter
import com.backbase.assignment.ui.features.movieList.viewmodel.MovieListViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : BaseActivity() {
    private val dataListViewModel by viewModel<MovieListViewModel>()
    private lateinit var moviesAdapter: MoviesListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initAppBar()
        setAdapter()
        fetchDataRepos()
        initState()
    }
    private fun initAppBar() {
        val bgImage = resources.getDrawable(R.drawable.moviebox_toolbar)
        supportActionBar?.setBackgroundDrawable(bgImage)
        supportActionBar?.setDisplayShowTitleEnabled(false);
    }

    private fun setAdapter() {
        moviesAdapter = MoviesListAdapter(this,{ dataListViewModel.retry() },{ item -> doClick(item) })
        recycler_view.adapter = moviesAdapter
        dataListViewModel.getMovieListResults().observe(this,
            Observer {
                moviesAdapter.submitList(it)
            })

    }
    /**
     * observer to check live data changes
     */
    private fun observeList() {
        dataListViewModel?.movieNowPlaylingList.observe(this, Observer {
            it?.let { result ->
                moviesAdapter.clear()
                moviesAdapter.setNowPlayingData(result)
            }
        }) }


    private fun  fetchDataRepos() {
        if(hasNetwork()) {
            initState()
            dataListViewModel.fetchMovieList()
            observeList()
        }else {
            showNetworkMessage(hasNetwork())
        }
    }


    private fun initState() {
        txt_error.setOnClickListener { dataListViewModel.retry() }
        dataListViewModel.getState().observe(this, Observer { state ->
            progress_bar.visibility = if (dataListViewModel.listIsEmpty() && state == State.LOADING) View.VISIBLE else View.GONE
            txt_error.visibility = if (dataListViewModel.listIsEmpty() && state == State.ERROR) View.VISIBLE else View.GONE
            if (!dataListViewModel.listIsEmpty()) {
                moviesAdapter.setState(state ?: State.DONE)
            }
        })
    }


    fun doClick(data:Bundle){
        if(hasNetwork()) {
            val intent = Intent(this, MovieDetailsActivity::class.java).apply {
                putExtra("data", data)
            }
            startActivity(intent)
        } else {
            showNetworkMessage(hasNetwork())
        }
    }


}
