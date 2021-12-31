package am.primesoft.task.presentation

import am.primesoft.task.data.networck.retrofit.networkModule
import am.primesoft.task.presentation.main.viewModelModule
import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(androidContext = this@App)

            modules(
                viewModelModule,
                networkModule
            )
        }
    }
}