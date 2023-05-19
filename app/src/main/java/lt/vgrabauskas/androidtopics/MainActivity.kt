package lt.vgrabauskas.androidtopics

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import lt.vgrabauskas.androidtopics.databinding.ActivityMainBinding

class MainActivity : ActivityLifecycles() {


    private lateinit var adapter: CustomAdapter
    private var itemIndex = -1
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val items = mutableListOf<Item>()
        generateListOfItems(items)

        setUpListView()
        updateAdapter(items)

        setClickOpenItemDetails()
        setClickOpenSecondActivity()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        timber("$itemIndex")
        outState.putInt(MAIN_ACTIVITY_SAVE_INSTANCE_STATE_ITEM_INDEX, itemIndex)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        timber("$itemIndex")
        itemIndex = savedInstanceState.getInt(MAIN_ACTIVITY_SAVE_INSTANCE_STATE_ITEM_INDEX)
    }

    private fun generateListOfItems(items: MutableList<Item>) {
        for (item in 1..10) {
            items.add(
                Item(
                    item,
                    "Text01_%04x".format(item),
                    "Text02_%06x".format(item)
                )
            )
        }
    }

    private fun setUpListView() {
        adapter = CustomAdapter(this)
        binding.itemListView.adapter = adapter
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

    private fun setClickOpenSecondActivity() {
        binding.openSecondActivityButton.setOnClickListener {
//            startActivityForResult.launch(Intent(this, SecondActivity::class.java))

        }
    }

    private fun setClickOpenItemDetails() {
        binding.itemListView.setOnItemClickListener { adapterView, view, position, l ->
            val item: Item = adapterView.getItemAtPosition(position) as Item
            itemIndex = position

            val itemIntent = Intent(this, SecondActivity::class.java)
            itemIntent.putExtra(MAIN_ACTIVITY_ITEM_ID, item)
            startActivityForResult.launch(itemIntent)
        }
    }

    private val startActivityForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            when (result.resultCode) {
                SecondActivity.SECOND_ACTIVITY_ITEM_INTENT_RETURN_NEW -> {


                    val item: Item = getExtraFromParcelable(result.data)
                    adapter.add(item)
                }
                SecondActivity.SECOND_ACTIVITY_ITEM_INTENT_RETURN_UPDATE -> {
                    val item = getExtraFromParcelable(result.data)
                    if (itemIndex >= 0) {
                        adapter.update(itemIndex, item)
                    }
                }
            }
        }

    private fun getExtraFromParcelable(result: Intent?) =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            result?.getParcelableExtra(MAIN_ACTIVITY_ITEM_ID, Item::class.java)
                ?: Item(0, "", "")
        } else {
            result?.getParcelableExtra(MAIN_ACTIVITY_ITEM_ID)
                ?: Item(0, "", "")
        }

    companion object {
        const val MAIN_ACTIVITY_ITEM_ID = "package lt.vcs.androidtopics_item_id"
        const val MAIN_ACTIVITY_ITEM_TEXT01 = "package lt.vcs.androidtopics_item_text01"
        const val MAIN_ACTIVITY_ITEM_TEXT02 = "package lt.vcs.androidtopics_item_text02"
        const val MAIN_ACTIVITY_ITEM_CREATION_DATE =
            "package lt.vcs.androidtopics_item_creation_date"
        const val MAIN_ACTIVITY_ITEM_UPDATE_DATE = "package lt.vcs.androidtopics_item_update_date"
        const val MAIN_ACTIVITY_SAVE_INSTANCE_STATE_ITEM_INDEX =
            "package lt.vcs.androidtopics_save_instance_state_item_index"
    }
}
