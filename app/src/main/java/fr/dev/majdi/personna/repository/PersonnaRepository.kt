package fr.dev.majdi.personna.repository

import fr.dev.majdi.personna.Constants.Companion.NUMBER_OF_USERS
import fr.dev.majdi.personna.model.ResponsePersonna
import fr.dev.majdi.personna.network.ApiPersonna
import retrofit2.Call
import retrofit2.Response

/**
 * Created by Majdi RABEH on 28/06/2020.
 * Email : m.rabeh.majdi@gmail.com
 */
class PersonnaRepository(val apiPersonna: ApiPersonna) {

    fun getUsers(onProductData: OnPersonnaData) {
        apiPersonna.getAllUsers(NUMBER_OF_USERS)
            .enqueue(object : retrofit2.Callback<ResponsePersonna> {
                override fun onResponse(
                    call: Call<ResponsePersonna>,
                    response: Response<ResponsePersonna>
                ) {
                    onProductData.onSuccess((response.body() as ResponsePersonna))
                }

                override fun onFailure(call: Call<ResponsePersonna>, t: Throwable) {
                    onProductData.onFailure()
                }
            })
    }

    interface OnPersonnaData {
        fun onSuccess(responsePersonna: ResponsePersonna)
        fun onFailure()
    }
}