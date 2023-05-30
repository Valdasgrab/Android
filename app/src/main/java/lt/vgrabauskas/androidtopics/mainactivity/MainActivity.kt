package lt.vgrabauskas.androidtopics.mainactivity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
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

        setUpObservables()

        setClickOpenItemDetails()
    }


    override fun onResume() {
        super.onResume()
        activityViewModel.fetchItems()
    }

    fun onClickButtonOpenSecondActivity(view: View) {
        startActivity(Intent(this, SecondActivity::class.java))
    }

    private fun setUpListView() {
        adapter = CustomAdapter(this)
        binding.itemListView.adapter = adapter
    }

    private fun setUpObservables() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                activityViewModel.itemsStateFlow.collect{
                    listOfItems -> adapter.add(listOfItems)
                }
            }
        }

//        activityViewModel.itemsStateFlow.observe(
//            this,
//            Observer { listOfItems ->
//                adapter.add(listOfItems)
//            }
//        )

//        activityViewModel.isLoadingLiveData.observe(this) { isLoading ->
//            binding.loadingProgressBar.isVisible = isLoading
//            binding.itemListView.isVisible = !isLoading
//        }
    }

    private fun setClickOpenItemDetails() {
        binding.itemListView.setOnItemClickListener { adapterView, view, position, l ->
            val item: Item = adapterView.getItemAtPosition(position) as Item

            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra(MAIN_ACTIVITY_ITEM_INTENT_ID, item.id)

            startActivity(intent)
        }
    }

    companion object {
        const val MAIN_ACTIVITY_ITEM_INTENT_ID =
            "package lt.vgrabauskas.androidtopics_item_intent_id"
    }
}
