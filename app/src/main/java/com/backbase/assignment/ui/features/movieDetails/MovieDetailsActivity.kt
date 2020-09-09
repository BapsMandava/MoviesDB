package com.backbase.assignment.ui.features.movieDetails

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.backbase.assignment.R
import com.backbase.assignment.databinding.ActivityMovieDetailsBinding
import com.backbase.assignment.ui.BaseActivity
import com.backbase.assignment.ui.features.movieDetails.adapter.GenresListAdapter
import com.backbase.assignment.ui.features.movieDetails.viewmodel.MovieDetailsViewModel
import com.backbase.assignment.ui.features.movieList.viewmodel.MovieListViewModel
import kotlinx.android.synthetic.main.activity_movie_details.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieDetailsActivity : BaseActivity() {
    private val movieDetailsViewModel by viewModel<MovieDetailsViewModel>()
    lateinit var binding: ActivityMovieDetailsBinding;
    private lateinit var genresAdapter: GenresListAdapter
    var id:Int=0;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var data = intent.getBundleExtra("data")
        id = data.getInt("id")
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_details)
        binding.executePendingBindings()
        pbProgress.visibility= View.VISIBLE
        initAppBar()
        fetchMovieDetails()
        setAdapter()

    }

    private fun initAppBar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        supportActionBar?.setDisplayShowTitleEnabled(false);
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
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
                pbProgress.visibility = View.GONE
                rlMovieDeatils.visibility = View.VISIBLE
                binding.viewModel = result
                genresAdapter.setRepos(result.genres)
            }
        })
    }

    private fun fetchMovieDetails(){
        movieDetailsViewModel.fetchMovieDetails(id)
        initObservers()
    }
}