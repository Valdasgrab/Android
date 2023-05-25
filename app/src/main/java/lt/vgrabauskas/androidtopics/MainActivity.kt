package lt.vgrabauskas.androidtopics

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ListView
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import lt.vgrabauskas.androidtopics.databinding.ActivityMainBinding

class MainActivity : ActivityLifecycles() {


    private lateinit var adapter: CustomAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.mainActivity = this

        val items = mutableListOf<Item>()
        generateListOfItems(items)

        setUpListView()
        updateAdapter(items)

        setClickOpenItemDetails()

        // Comments just for merging purpose: commit01
    }


    private fun setUpListView() {
        adapter = CustomAdapter(this)
        binding.itemListView.adapter = adapter
    }
    private fun generateListOfItems(items: MutableList<Item>) {
        for (item in 1..2) {
            items.add(
                Item(
                    item,
                    "Text01_%04x".format(item),
                    "Text02_%06x".format(item)
                )
            )
        }
    }



    private fun updateAdapter(items: MutableList<Item>) {
        adapter.add(items)
        adapter.add(Item(101, "text01", "text02"))
        adapter.add(
            Item(102, "text01", "text02"),
            Item(103, "text01", "text02"),
            Item(104, "text01", "text02"),
            Item(105, "text01", "text02"),
        )
    }

    fun onClickButtonOpenSecondActivity(view: View) {
        val intent = Intent(this, SecondActivity::class.java)
        val id = adapter.getMaxId().inc()
        intent.putExtra(MAIN_ACTIVITY_ITEM_INTENT_ID, id)

        startActivityForResult.launch(intent)
    }

    private fun setClickOpenItemDetails() {
        binding.itemListView.setOnItemClickListener { adapterView, view, position, l ->
            val item: Item = adapterView.getItemAtPosition(position) as Item

            val itemIntent = Intent(this, SecondActivity::class.java)
            itemIntent.putExtra(MAIN_ACTIVITY_ITEM_INTENT_OBJECT, item)

            startActivityForResult.launch(itemIntent)
        }
    }

    private val startActivityForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->

            val item: Item?

            when (result.resultCode) {
                SecondActivity.SECOND_ACTIVITY_ITEM_INTENT_RETURN_NEW -> {
                    item = getExtraFromParcelable(
                        result.data,
                        SecondActivity.SECOND_ACTIVITY_ITEM_INTENT_RETURN_OBJECT
                    )

                    if (item != null) {
                        adapter.add(item)
                    }
                }

                SecondActivity.SECOND_ACTIVITY_ITEM_INTENT_RETURN_UPDATE -> {
                    item = getExtraFromParcelable(
                        result.data,
                        SecondActivity.SECOND_ACTIVITY_ITEM_INTENT_RETURN_OBJECT
                    )
                    adapter.update(item)
                }
            }
        }



    companion object {
        const val MAIN_ACTIVITY_ITEM_INTENT_OBJECT = "package lt.vgrabauskas.androidtopics_item_intent_onject"
        const val MAIN_ACTIVITY_ITEM_INTENT_ID = "package lt.vgrabauskas.androidtopics_item_intent_id"
    }
}
