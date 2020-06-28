package fr.dev.majdi.personna.module

import fr.dev.majdi.personna.Constants.Companion.BASE_URL
import fr.dev.majdi.personna.network.ApiPersonna
import fr.dev.majdi.personna.repository.PersonnaRepository
import fr.dev.majdi.personna.viewmodel.PersonnaViewModel
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
    val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    return retrofit.create(ApiPersonna::class.java)
}