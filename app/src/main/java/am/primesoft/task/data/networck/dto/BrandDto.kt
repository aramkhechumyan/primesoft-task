package am.primesoft.task.data.networck.dto

data class BrandDto(
    val id: Int,
    val name: String,
    val categoryIds: List<Int>
){
    override fun toString(): String {
        return name
    }
}