package lt.vgrabauskas.androidtopics

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import timber.log.Timber

class MainActivity : AppCompatActivity() {


    lateinit var openSecondActivityButton: Button
    lateinit var adapter: CustomAdapter
    lateinit var itemListView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        openSecondActivityButton = findViewById(R.id.openSecondActivityButton)
        itemListView = findViewById(R.id.itemListView)

        setClickOpenSecondActivity()

        val items = mutableListOf<Item>()
        generateListOfItems(items)

        adapter = CustomAdapter(this)
        adapter.add(items)
        adapter.add(Item(101, "text01", "text02"))
        adapter.add(
            Item(102, "text03", "text04"),
            Item(103, "text05", "text06"),
            Item(104, "text03", "text04"),
            Item(105, "text05", "text06")
        )

        itemListView.adapter = adapter

    }

    private fun generateListOfItems(items: MutableList<Item>) {
        for (item in 1..10) {
            items.add(
                Item(
                    item,
                    "text01%04x".format(item),
                    "text02%06x".format(item)
                )
            )
        }
    }

    private fun setClickOpenSecondActivity() {
        openSecondActivityButton.setOnClickListener {
            startActivity(Intent(this, SecondActivity::class.java))
        }
    }
}
