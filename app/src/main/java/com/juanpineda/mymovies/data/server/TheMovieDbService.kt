package com.juanpineda.mymovies.data.server

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TheMovieDbService {
    @GET("discover/movie?sort_by=popularity.desc")
    suspend fun listPopularMoviesAsync(
        @Query("api_key") apiKey: String,
        @Query("region") region: String
    ): MovieDbResult

    @GET("movie/{movieId}/images")
    suspend fun listMoviesImagesAsync(
        @Path("movieId") movieId: Int,
        @Query("api_key") apiKey: String
    ): TheMovieDbImagesResult

    @GET("search/movie")
    suspend fun searchMoviesAsync(
        @Query("api_key") apiKey: String,
        @Query("query") query: String,
        @Query("language") language: String = "es"
    ): MovieDbResult

    @GET("movie/{movieId}")
    suspend fun getMovieDetailsAsync(
        @Path("movieId") movieId: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "es"
    ): TheMovieDbMovie
}