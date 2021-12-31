package am.primesoft.task.data.networck.retrofit

import am.primesoft.task.data.networck.dto.DataDto
import am.primesoft.task.data.networck.dto.TaskResposeDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.HeaderMap
import retrofit2.http.Headers

interface TaskAPIService {
    @Headers("language: en")
    @GET("customer/getItems?categoryId=0&parentCategoryId=0&brandId=0&text=&type=1")
    fun getItems(): Call<TaskResposeDto>
}