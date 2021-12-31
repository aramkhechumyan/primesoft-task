package am.primesoft.task.data.networck.retrofit

import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single { provideRetrofit() }
    single { provideTaskAPIService(get()) }
}

fun provideRetrofit(): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://api.superautosports.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

fun provideTaskAPIService(retrofit: Retrofit): TaskAPIService {
    return retrofit.create(TaskAPIService::class.java)
}