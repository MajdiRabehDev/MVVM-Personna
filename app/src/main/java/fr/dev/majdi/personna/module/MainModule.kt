package fr.dev.majdi.personna.module

import fr.dev.majdi.personna.Constants.Companion.BASE_URL
import fr.dev.majdi.personna.network.ApiPersonna
import fr.dev.majdi.personna.repository.PersonnaRepository
import fr.dev.majdi.personna.viewmodel.PersonnaViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Created by Majdi RABEH on 28/06/2020.
 * Email : m.rabeh.majdi@gmail.com
 */
val mainModule = module {
    single { createWebService() }
    single { PersonnaRepository(get()) }
    viewModel { PersonnaViewModel(get()) }
}

fun createWebService(): ApiPersonna {

    val interceptor = HttpLoggingInterceptor()
    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
    val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()

    val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .baseUrl(BASE_URL)
        .client(client)
        .build()

    return retrofit.create(ApiPersonna::class.java)
}
