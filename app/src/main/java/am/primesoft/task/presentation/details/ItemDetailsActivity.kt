package am.primesoft.task.presentation.details

import am.primesoft.task.R
import am.primesoft.task.data.networck.dto.ItemDto
import am.primesoft.task.presentation.main.ITEM
import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import coil.load
import android.text.method.ScrollingMovementMethod


class ItemDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item)

        val imageView = findViewById<ImageView>(R.id.itemImage_imageView)
        val nameText = findViewById<TextView>(R.id.itemName_textView)
        val descriptionText = findViewById<TextView>(R.id.descreption_textView)
        val priceText = findViewById<TextView>(R.id.pice_textView)

        val itemDto = intent?.getParcelableExtra<ItemDto>(ITEM)

        imageView.load(itemDto?.iconUrl)
        nameText.text = itemDto?.name
        descriptionText.movementMethod = ScrollingMovementMethod()
        descriptionText.text = itemDto?.description
        priceText.text = "$ ${itemDto?.price.toString()}"
    }
}