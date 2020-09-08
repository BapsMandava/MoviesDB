package com.backbase.assignment.ui.features.movieDetails

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.backbase.assignment.R
import com.backbase.assignment.databinding.ActivityMovieDetailsBinding
import com.backbase.assignment.ui.features.movieDetails.viewmodel.MovieDetailsViewModel

class MovieDetailsActivity : AppCompatActivity() {
    private val viewModel by viewModel<MovieDetailsViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMovieDetailsBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_movie_details)
        binding.viewModel = viewModel
        initAppBar()
        initObservers()
        fetchMovieDetails()
    }

    private fun initAppBar() {
        supportActionBar?.title = getString(R.string.movie_details)
    }

    /**
     * observer to check live data changes
     */
    private fun initObservers() {

    }

    private fun fetchMovieDetails(){
        val movieId = intent?.extras?.getString("movieId")
        viewModel.fetchMovieDetails(movieId)
    }
}