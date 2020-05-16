package com.raydevelopers.newvalley.data.respositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.raydevelopers.newvalley.network.NetworkResponse
import com.raydevelopers.newvalley.network.NetworkService
import com.raydevelopers.newvalley.network.RetrofitService
import com.raydevelopers.newvalley.network.ServiceHelper
import com.raydevelopers.newvalley.utility.BASE_URL

class AppRepository(private val serviceHelper: ServiceHelper) {
    /*
       get call to make a netwotrk call and give live data
        */
    suspend fun getCall(
        subUrl: String
    ): Any {
       return serviceHelper.get(subUrl)
    }
}