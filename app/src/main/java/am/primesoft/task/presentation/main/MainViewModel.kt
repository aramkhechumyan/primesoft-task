package am.primesoft.task.presentation.main

import am.primesoft.task.data.networck.dto.BrandDto
import am.primesoft.task.data.networck.dto.ItemDto
import am.primesoft.task.data.networck.dto.TaskResposeDto
import am.primesoft.task.data.networck.retrofit.TaskAPIService
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
}

class MainViewModel(taskAPIService: TaskAPIService) : ViewModel() {

    val itemsLiveData: LiveData<List<ItemDto>> = MutableLiveData()
    val brandsLiveData: LiveData<List<BrandDto>> = MutableLiveData()

    private val itemsCall = taskAPIService.getItems()

    fun getData() {
        itemsCall.enqueue(object : Callback<TaskResposeDto> {
            override fun onResponse(
                call: Call<TaskResposeDto>,
                response: Response<TaskResposeDto>
            ) {
                if (response.isSuccessful) {
                    val taskDto = response.body()
                    taskDto?.let {
                        it.result.data.items?.let { items ->
                            (itemsLiveData as MutableLiveData).value = items
                        }

                        it.result.data.brands?.let { brands ->
                            (brandsLiveData as MutableLiveData).value = brands
                        }
                    }
                }
            }

            override fun onFailure(call: Call<TaskResposeDto>, t: Throwable) {
            }
        })
    }

    override fun onCleared() {
        itemsCall.cancel()
    }
}