package am.primesoft.task.presentation.main

import am.primesoft.task.R
import am.primesoft.task.data.networck.dto.ItemDto
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class ItemsAdapter(private val listener: Listener) :
    RecyclerView.Adapter<ItemsAdapter.MyViewHolder>() {

    private var items = listOf<ItemDto>()

    inner class MyViewHolder(view: View, listener: Listener) : RecyclerView.ViewHolder(view) {

        private val itemName: TextView = view.findViewById(R.id.item_textView)
        private lateinit var itemDto: ItemDto

        init {
            view.setOnClickListener {
                listener.onItemClick(itemDto)
            }
        }

        fun bindData(itemDto: ItemDto) {
            this.itemDto = itemDto
            this.itemName.text = itemDto.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_view, parent, false)
        return MyViewHolder(itemView, listener)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindData(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setItems(items: List<ItemDto>) {
        this.items = items
        notifyDataSetChanged()
    }

    interface Listener {
        fun onItemClick(itemDto: ItemDto)
    }
}