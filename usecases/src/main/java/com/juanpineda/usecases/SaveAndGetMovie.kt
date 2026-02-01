package com.juanpineda.usecases

import com.juanpineda.data.repository.MoviesRepository
import com.juanpineda.domain.Movie

class SaveAndGetMovie(private val moviesRepository: MoviesRepository) {
    suspend operator fun invoke(movie: Movie): Movie {
        // Save the movie to local database
        moviesRepository.saveMovie(movie)
        // Return the movie
        return moviesRepository.findById(movie.id)
    }
}
