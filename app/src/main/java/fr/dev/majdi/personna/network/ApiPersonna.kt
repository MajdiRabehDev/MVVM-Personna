package fr.dev.majdi.personna.network

import fr.dev.majdi.personna.model.ResponsePersonna
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Majdi RABEH on 28/06/2020.
 * Email : m.rabeh.majdi@gmail.com
 */
interface ApiPersonna {

    @GET("/api/")
    fun getAllUsers(@Query("results") results: Int): Call<ResponsePersonna>

}