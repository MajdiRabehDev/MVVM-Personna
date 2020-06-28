package fr.dev.majdi.personna

import android.app.Application
import fr.dev.majdi.personna.module.mainModule
import org.koin.android.ext.android.startKoin

/**
 * Created by Majdi RABEH on 28/06/2020.
 * Email : m.rabeh.majdi@gmail.com
 */
class PersonnaApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(mainModule), loadPropertiesFromFile = true)
    }
}