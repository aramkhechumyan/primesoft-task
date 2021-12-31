package am.primesoft.task.data.networck.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ItemDto(
    val id: Int,
    val categoryId: Int,
    val iconUrl: String,
    val price: Double,
    val name: String,
    val description: String,
    val brandId: Int
) : Parcelable