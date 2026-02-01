package com.juanpineda.usecases

import com.juanpineda.data.repository.MoviesRepository
import com.juanpineda.domain.Movie

class SearchMovies(private val moviesRepository: MoviesRepository) {
    suspend operator fun invoke(query: String): List<Movie> =
        moviesRepository.searchMovies(query)
}
