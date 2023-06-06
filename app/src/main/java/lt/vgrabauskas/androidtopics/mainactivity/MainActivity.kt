package lt.vgrabauskas.androidtopics.mainactivity

import android.app.AlertDialog
import android.content.ClipData
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AbsListView
import android.widget.AbsListView.OnScrollListener
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import lt.vgrabauskas.androidtopics.ActivityLifecycles
import lt.vgrabauskas.androidtopics.R
import lt.vgrabauskas.androidtopics.secondactivity.SecondActivity
import lt.vgrabauskas.androidtopics.databinding.ActivityMainBinding
import lt.vgrabauskas.androidtopics.repository.Item

class MainActivity : ActivityLifecycles() {


    private lateinit var adapter: CustomAdapter
    private lateinit var binding: ActivityMainBinding
    private val activityViewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.mainActivity = this
        binding.viewModel = activityViewModel
        binding.lifecycleOwner = this

        setUpListView()

        onScrollListView()

        setUpObservables()

        onItemLongClick()
        setClickOpenItemDetails()

    }


    override fun onResume() {
        super.onResume()
        activityViewModel.fetchItems()

        if (adapter.getMaxId() != -1) {
            binding.itemListView.smoothScrollToPosition(
                activityViewModel.positionListViewStateFlow.value
            )
        }

    }

    fun onClickButtonOpenSecondActivity(view: View) {
        startActivity(Intent(this, SecondActivity::class.java))
    }

    private fun setUpListView() {
        adapter = CustomAdapter(this)
        binding.itemListView.adapter = adapter
    }

    private fun onScrollListView() {
//        binding.itemListView.setOnScrollListener(
//            object : OnScrollListener{
//                override fun onScrollStateChanged(p0: AbsListView?, p1: Int) {
//
//                }
//
//                override fun onScroll(p0: AbsListView?, position: Int, p2: Int, p3: Int) {
//                    if (activityViewModel.positionListViewStateFlow.value != position) {
//                        activityViewModel.savePositionListView(position)
//                        displaySnackBar("First visible item index $position")
//                    }
//                }
//            }
//        )
        binding.itemListView.setOnScrollChangeListener { _, _, _, _, _ ->
            val position = binding.itemListView.firstVisiblePosition

            if (activityViewModel.positionListViewStateFlow.value != position) {
                activityViewModel.savePositionListView(position)
            }
        }
    }

    private fun setUpObservables() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                activityViewModel.uiState.collect { uiState ->
                    adapter.add(uiState.items)
                }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                activityViewModel.isDeletedUIState.collect { displayUIState ->
                    if (displayUIState.isDeleted) {
                        displaySnackBar("Item was removed from repository")
                        adapter.remove(displayUIState.item)
                    } else {
                        displaySnackBar("Item was not removed from repository")
                    }
                }
            }
        }
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                activityViewModel.positionListViewStateFlow.collect { firstVisiblePosition ->
                    displaySnackBar("First visible position $firstVisiblePosition")
                }
            }
        }
    }

    private fun onItemLongClick() {
        binding.itemListView.setOnItemLongClickListener { adapterView, view, position, l ->
            val item = adapterView.getItemAtPosition(position) as Item
            displayDeleteItemAlertDialog(item)
            true
        }
    }

    private fun setClickOpenItemDetails() {
        binding.itemListView.setOnItemClickListener { adapterView, view, position, l ->
            val item: Item = adapterView.getItemAtPosition(position) as Item

            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra(MAIN_ACTIVITY_ITEM_INTENT_ID, item.id)

            startActivity(intent)
        }
    }

    private fun displayDeleteItemAlertDialog(item: Item) {
        AlertDialog
            .Builder(this)
            .setTitle("Delete")
            .setMessage("Would you like to delete the item?")
            .setIcon(R.drawable.ic_clear_24)
            .setPositiveButton("Yes") { _, _ ->
                activityViewModel.deleteItem(item)

//                displaySnackBar("Item was removed from repository")
            }
            .setNegativeButton("No", null)
            .show()
    }

    private fun displaySnackBar(message: String) {
        Snackbar
            .make(
                binding.openSecondActivityButton,
                message,
                Snackbar.LENGTH_LONG
            )
            .setAnchorView(binding.openSecondActivityButton)
            .show()
    }

    companion object {
        const val MAIN_ACTIVITY_ITEM_INTENT_ID =
            "package lt.vgrabauskas.androidtopics_item_intent_id"
    }
}
