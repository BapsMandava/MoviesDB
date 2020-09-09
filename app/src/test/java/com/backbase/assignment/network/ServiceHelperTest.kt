package com.backbase.assignment.network

import com.backbase.assignment.BaseTest
import com.backbase.assignment.ui.ServiceType
import com.backbase.assignment.ui.network.Api
import com.backbase.assignment.ui.network.MockServiceImpl
import com.backbase.assignment.ui.network.ServiceInterface
import org.junit.Assert
import org.junit.Test

class ServiceHelperTest : BaseTest(){

    @Test
    fun `check if instance of ServiceInterface is SimpleInterface`(){
        var serviceAPIHelper= Api(ServiceType.API)
        Assert.assertTrue(serviceAPIHelper.getServiceinterface() is ServiceInterface)
    }

    @Test
    fun `check if instance of ServiceInterface is mockServiceImpl`(){
        var serviceAPIHelper=Api(ServiceType.MOCK)
        Assert.assertTrue(serviceAPIHelper.getServiceinterface() is MockServiceImpl)
    }
}