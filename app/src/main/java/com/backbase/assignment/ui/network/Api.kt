package com.backbase.assignment.ui.network

import com.backbase.assignment.ui.Constants
import com.backbase.assignment.ui.ServiceType
import retrofit2.mock.BehaviorDelegate
import retrofit2.mock.MockRetrofit
import retrofit2.mock.NetworkBehavior

class Api(serviceType: ServiceType){
    private var serviceInterface: ServiceInterface?=null
    init {
        when(serviceType) {
            ServiceType.API->
                serviceInterface =
                    NetworkAPIController.getApiClient(Constants.MOVIES_API_BASE_URL)?.create(
                        ServiceInterface::class.java)
            else-> serviceInterface =createMockServiceImpl()
        }
    }

    fun getServiceinterface(): ServiceInterface? {
        return serviceInterface
    }

    /**
     * returns a mock service interface object
     */
    private fun createMockServiceImpl(): ServiceInterface {
        val retrofit = NetworkAPIController.getApiClient(Constants.MOVIES_API_BASE_URL)
        val behavior = NetworkBehavior.create()
        val mockRetrofit = MockRetrofit.Builder(retrofit).networkBehavior(behavior).build()
        val delegate: BehaviorDelegate<ServiceInterface> = mockRetrofit.create(ServiceInterface::class.java)
        return MockServiceImpl(delegate)
    }

}