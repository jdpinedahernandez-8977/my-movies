package com.juanpineda.data.source

import com.juanpineda.data.result.ResultHandler
import com.juanpineda.domain.Movie
import com.juanpineda.domain.MovieImage

interface RemoteDataSource {
    suspend fun getPopularMovies(apiKey: String, region: String): ResultHandler<List<Movie>>
    suspend fun getMovieImages(apiKey: String, movieId: Int): ResultHandler<List<MovieImage>>
    suspend fun searchMovies(apiKey: String, query: String): ResultHandler<List<Movie>>
}