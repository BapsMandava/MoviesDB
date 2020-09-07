package com.backbase.assignment.ui.features.movieList

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.backbase.assignment.R
import com.backbase.assignment.ui.data.State
import com.backbase.assignment.ui.features.movieList.adapters.MoviesListAdapter
import com.backbase.assignment.ui.features.movieList.viewmodel.MovieListViewModel
import com.backbase.assignment.ui.model.Results
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    companion object {
        val TAG = MainActivity::class.java.simpleName
    }

    private lateinit var dataListViewModel: MovieListViewModel
    private lateinit var moviesAdapter: MoviesListAdapter
    private lateinit var dataRepoList: ArrayList<Results>
    lateinit var mLayoutManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initialiseViewModel()
        setAdapter()
        fetchDataRepos()
        initState()
       // observeList()
        //fetchDataRepos()
    }

    private fun initialiseViewModel() {
        dataListViewModel = ViewModelProviders.of(this).get(MovieListViewModel(application)::class.java)
    }

    private fun setAdapter() {
        moviesAdapter = MoviesListAdapter(this) { dataListViewModel.retry() }
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
       // if(hasNetwork()) {
          //  showProgressBar(true)
           dataListViewModel.fetchMovieList()
            observeList()
       // dataListViewModel.fetchPopularMovieList()
       /* }else{
            showNetworkMessage(hasNetwork())
        }*/
    }

    fun doClick(data:Bundle){
       /* val intent = Intent(this, TitleDetailActivity::class.java).apply {
            putExtra("data", data)
        }
        startActivity(intent)*/
    }

}
