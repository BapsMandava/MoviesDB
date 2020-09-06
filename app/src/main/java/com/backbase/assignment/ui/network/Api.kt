package com.backbase.assignment.ui.network

import com.backbase.assignment.ui.Constants
import retrofit2.mock.BehaviorDelegate
import retrofit2.mock.MockRetrofit
import retrofit2.mock.NetworkBehavior

class Api(){
    private var serviceInterface: ServiceInterface?=null
    init {
        serviceInterface =
                    NetworkAPIController.getApiClient(Constants.MOVIES_API_BASE_URL)?.create(
                        ServiceInterface::class.java)
    }

    fun getServiceinterface(): ServiceInterface? {
        return serviceInterface
    }

}