package am.primesoft.task.presentation.main


import am.primesoft.task.R
import am.primesoft.task.data.networck.dto.BrandDto
import am.primesoft.task.data.networck.dto.ItemDto
import am.primesoft.task.presentation.details.ItemDetailsActivity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.koin.androidx.viewmodel.ext.android.viewModel

const val ITEM = "ITEM"

class MainActivity : AppCompatActivity(), ItemsAdapter.Listener {

    private val itemsAdapter = ItemsAdapter(this)
    private var brands: List<BrandDto>? = null
    private var items: List<ItemDto>? = null
    private val mainViewModel: MainViewModel by viewModel()
    private lateinit var searchQueryEditText: EditText
    private val onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(
            adapter: AdapterView<*>?,
            p1: View?,
            position: Int,
            p3: Long
        ) {
            if (position == 0) {
                filterItems(null)
                return
            }
            brands?.let {
                val selectedBrand = it[position - 1]
                filterItems(selectedBrand)
            }
        }

        override fun onNothingSelected(p0: AdapterView<*>?) {}
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val itemsRecyclerView: RecyclerView = findViewById(R.id.items)
        val dropdownSpinner = findViewById<Spinner>(R.id.brand_spinner)
        searchQueryEditText = findViewById(R.id.search_query_edit_text)


        val linearLayoutManager = LinearLayoutManager(applicationContext)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        itemsRecyclerView.layoutManager = linearLayoutManager
        itemsRecyclerView.adapter = itemsAdapter

        dropdownSpinner.onItemSelectedListener = onItemSelectedListener

        findViewById<View>(R.id.search_button).setOnClickListener {
            search(searchQueryEditText.text.toString())
        }

        mainViewModel.itemsLiveData.observe(this, { items ->
            this.items = items
            this.itemsAdapter.setItems(items)
        })

        mainViewModel.brandsLiveData.observe(this, { brands ->
            this.brands = brands
            val brandModels = mutableListOf<BrandsAdapter.BrandModel>()
            brands.forEach { brandDto ->
                brandModels.add(BrandsAdapter.BrandModel(BRAND, brandDto))
            }
            val adapter = BrandsAdapter(
                this@MainActivity,
                brandModels
            )
            dropdownSpinner.adapter = adapter
        })

        mainViewModel.getData()
    }

    private fun search(searchQuery: String) {
        val filteredItems: List<ItemDto>? = items?.filter {
            it.name.lowercase().contains(searchQuery.trim().lowercase())
        }

        filteredItems?.let {
            itemsAdapter.setItems(it)
        }
    }

    private fun filterItems(selectedBrand: BrandDto?) {
        if (selectedBrand == null) {
            items?.let { itemsAdapter.setItems(it) }
            return
        }
        val filteredItems: List<ItemDto>? = items?.filter {
            it.brandId == selectedBrand.id
        }

        filteredItems?.let {
            itemsAdapter.setItems(it)
        }
    }

    override fun onItemClick(itemDto: ItemDto) {
        val intent = Intent(this, ItemDetailsActivity::class.java)
        intent.putExtra(ITEM, itemDto)
        startActivity(intent)
    }
}


