package com.backbase.assignment.ui.network

import com.backbase.assignment.ui.Constants
import com.backbase.assignment.ui.model.MoviePlayingResult
import com.backbase.assignment.ui.model.MoviesDetailsResult
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Service Interface to fetch data
 *
 */
interface ServiceInterface {
    /**
     * get now playing movies list from the service
     */
    @GET("now_playing?")
    fun fetchNowPlayingList(
        @Query("language") language: String = Constants.language,
        @Query("page") pageNo: String = "undefined",
        @Query("api_key") apiKey: String = Constants.api_key
    ): Observable<MoviePlayingResult>?

    /**
     * get popular movies list from the service
     */
    @GET("popular?")
    fun fetchPopularPlayingList(
        @Query("language") language: String = Constants.language,
        @Query("page") pageNo: Int,
        @Query("api_key") apiKey: String = Constants.api_key
    ): Observable<MoviePlayingResult>

    /**
     * get movies details
     */
    @GET("{movie_id}?")
    fun fetchMovieDetails(
        @Path("movie_id") movie_id: Int?,
        @Query("api_key") apiKey: String = Constants.api_key
    ): Observable<MoviesDetailsResult>

}