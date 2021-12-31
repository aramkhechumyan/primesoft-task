package am.primesoft.task.presentation.main

import am.primesoft.task.data.networck.dto.BrandDto
import android.R
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

const val ALL = "ALL"
const val BRAND = "BRAND"

class BrandsAdapter(
    context: Context,
    private val brands: List<BrandModel>
) :
    ArrayAdapter<BrandsAdapter.BrandModel>(
        context,
        R.layout.simple_list_item_1,
        brands
    ) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getView(convertView, parent, position)!!
    }

    private fun getView(
        convertView: View?,
        parent: ViewGroup,
        position: Int
    ): View? {
        var itemView = convertView

        if (itemView == null) {
            itemView = LayoutInflater.from(context)
                .inflate(R.layout.simple_list_item_1, parent, false)
        }

        val itemTextView = itemView?.findViewById<TextView>(R.id.text1)

        if (position == 0) {
            itemTextView?.text = ALL
        } else {
            itemTextView?.text = getItem(position).brandDto?.name
        }
        return itemView
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getView(convertView, parent, position)!!
    }

    override fun getItem(position: Int): BrandModel {
        return if (position == 0) {
            BrandModel(ALL, null)
        } else {
            brands[position - 1]
        }
    }

    override fun getCount(): Int {
        return brands.size + 1
    }

    data class BrandModel(
        var type: String = BRAND,
        val brandDto: BrandDto?
    )
}