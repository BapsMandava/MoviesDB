package com.backbase.assignment.ui.features.movieList

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.backbase.assignment.R
import com.backbase.assignment.ui.data.State
import com.backbase.assignment.ui.features.movieDetails.MovieDetailsActivity
import com.backbase.assignment.ui.features.movieList.adapters.MoviesListAdapter
import com.backbase.assignment.ui.features.movieList.viewmodel.MovieListViewModel
import com.backbase.assignment.ui.model.Results
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var dataListViewModel: MovieListViewModel
    private lateinit var moviesAdapter: MoviesListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initialiseViewModel()
        setAdapter()
        fetchDataRepos()
        initState()
    }

    private fun initialiseViewModel() {
        dataListViewModel = ViewModelProviders.of(this).get(MovieListViewModel(application)::class.java)
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
        })
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


    private fun  fetchDataRepos() {
        dataListViewModel.fetchMovieList()
        observeList()
    }

    fun doClick(data:Bundle){
        //Log.d("da",data.getInt("id").toString())
        val intent = Intent(this, MovieDetailsActivity::class.java).apply {
            putExtra("data", data)
        }
        startActivity(intent)
    }


}
