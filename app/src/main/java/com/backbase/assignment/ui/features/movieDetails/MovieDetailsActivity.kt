package com.backbase.assignment.ui.features.movieDetails

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.backbase.assignment.R
import com.backbase.assignment.databinding.ActivityMovieDetailsBinding
import com.backbase.assignment.ui.features.movieDetails.adapter.GenresListAdapter
import com.backbase.assignment.ui.features.movieDetails.viewmodel.MovieDetailsViewModel
import com.backbase.assignment.ui.features.movieList.adapters.MoviesListAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_movie_details.*

class MovieDetailsActivity : AppCompatActivity() {
    private lateinit var movieDetailsViewModel: MovieDetailsViewModel
    lateinit var binding: ActivityMovieDetailsBinding;
    private lateinit var genresAdapter: GenresListAdapter
    var id:Int=0;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var data = intent.getBundleExtra("data")
        id = data.getInt("id")
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_details)
        binding.executePendingBindings()
        initialiseViewModel()
        fetchMovieDetails()
        setAdapter()
        initObservers()
    }

    private fun initialiseViewModel() {
        movieDetailsViewModel =
            ViewModelProviders.of(this).get(MovieDetailsViewModel(application)::class.java)
    }

    private fun initAppBar() {
       // supportActionBar?.title = getString(R.string.ac)
    }

    private fun setAdapter() {
        genresAdapter = GenresListAdapter(this)
        rvGenres.adapter = genresAdapter
        rvGenres.layoutManager = GridLayoutManager(this,3)
        rvGenres.setNestedScrollingEnabled(false);

    }

    /**
     * observer to check live data changes
     */
    private fun initObservers() {
        movieDetailsViewModel?.movieDetails.observe(this, Observer {
            it?.let { result ->
                binding.viewModel = result
                genresAdapter.setRepos(result.genres)
            }
        })
    }

    private fun fetchMovieDetails(){
        movieDetailsViewModel.fetchMovieDetails(id)
    }
}