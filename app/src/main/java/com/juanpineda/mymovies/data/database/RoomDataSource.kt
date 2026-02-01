package com.juanpineda.mymovies.data.database

import com.juanpineda.data.source.LocalDataSource
import com.juanpineda.domain.Movie
import com.juanpineda.mymovies.data.toDomainMovie
import com.juanpineda.mymovies.data.toRoomMovie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class RoomDataSource(db: MovieDatabase) : LocalDataSource {

    private val movieDao = db.movieDao()

    override suspend fun isEmpty(): Boolean =
        withContext(Dispatchers.IO) { movieDao.movieCount() <= 0 }

    override suspend fun saveMovies(movies: List<Movie>) {
        withContext(Dispatchers.IO) { movieDao.insertMovies(movies.map { it.toRoomMovie() }) }
    }

    override suspend fun getPopularMovies(): Flow<List<Movie>> = withContext(Dispatchers.IO) {
        movieDao.getAll().map { list -> list.map { it.toDomainMovie() } }
    }

    override suspend fun findById(id: Int): Movie = withContext(Dispatchers.IO) {
        val roomMovie = movieDao.findById(id)
        if (roomMovie != null) {
            roomMovie.toDomainMovie()
        } else {
            // If movie not in database, create a placeholder that will trigger loading from API
            Movie(
                id = id,
                title = "",
                overview = "",
                releaseDate = "",
                posterPath = "",
                backdropPath = "",
                originalLanguage = "",
                originalTitle = "",
                popularity = 0.0,
                voteAverage = 0.0,
                favorite = false,
                adult = false,
                myVote = 0f
            )
        }
    }

    override suspend fun update(movie: Movie) {
        withContext(Dispatchers.IO) { movieDao.updateMovie(movie.toRoomMovie()) }
    }
}