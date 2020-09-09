package com.backbase.assignment.network

import com.backbase.assignment.BaseTest
import com.backbase.assignment.ui.ServiceType
import com.backbase.assignment.ui.repository.AppServiceRepo
import org.junit.Assert
import org.junit.Test
import org.koin.core.inject
import org.koin.core.parameter.parametersOf

class AppServiceRepoTest: BaseTest(){
    val appServiceRepo: AppServiceRepo by inject{ parametersOf(ServiceType.MOCK) }

    @Test
    fun `get Movies Details For a Movie with ID`(){
        appServiceRepo.getMovieDetails(movieId = 718444,onSuccess = {
            Assert.assertTrue(it != null)
            Assert.assertEquals(it?.title,"Rogue")
            Assert.assertEquals(it?.runtime,160)
            Assert.assertEquals(it?.original_language,"en")
        },onError = {
            assert(false)
        })
    }

    @Test
    fun `get Now Playing Movies List`(){
        appServiceRepo.getNowPlayingList(onSuccess = {
            Assert.assertTrue(it != null)
            Assert.assertEquals(it?.get(0)?.vote_count,161)
            Assert.assertEquals(it?.get(0)?.vote_average,5.9)
        },onError = {
            assert(false)
        })
    }
}