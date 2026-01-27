package com.juanpineda.usecases

import com.juanpineda.data.repository.MoviesRepository
import com.juanpineda.data.result.ErrorResponse
import com.juanpineda.data.result.SuccessResponse
import com.juanpineda.data.result.error.Failure
import com.juanpineda.data.result.onError
import com.juanpineda.data.result.onSuccess
import com.juanpineda.mymovies.testshared.mockedMovie
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetPopularMoviesTest {

    @Mock
    lateinit var moviesRepository: MoviesRepository

    lateinit var getPopularMovies: GetPopularMovies

    @Before
    fun setUp() {
        getPopularMovies = GetPopularMovies(moviesRepository)
    }

    @Test
    fun `invoke calls movies repository`() {
        runBlocking {

            val movies = listOf(mockedMovie.copy(id = 1))
            whenever(moviesRepository.getPopularMovies()).thenReturn(SuccessResponse(flowOf(movies)))

            getPopularMovies.invoke().onSuccess {
                it.collect {
                    Assert.assertEquals(movies, it)
                }
            }
        }
    }


    @Test
    fun `invoke calls movies repository return error UnknownException`() {
        runBlocking {

            whenever(moviesRepository.getPopularMovies()).thenReturn(ErrorResponse(Failure.UnknownException))

            getPopularMovies.invoke().onError {
                Assert.assertEquals(Failure.UnknownException, failure)
            }
        }
    }

    @Test
    fun `invoke calls movies repository return error NetworkConnection`() {
        runBlocking {
            val networkConnection = Failure.NetworkConnection(Exception())
            whenever(moviesRepository.getPopularMovies()).thenReturn(ErrorResponse(networkConnection))

            getPopularMovies.invoke().onError {
                Assert.assertEquals(networkConnection, failure)
            }
        }
    }
}