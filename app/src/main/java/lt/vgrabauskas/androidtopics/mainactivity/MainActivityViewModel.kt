package lt.vgrabauskas.androidtopics.mainactivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import lt.vgrabauskas.androidtopics.repository.Item
import lt.vgrabauskas.androidtopics.repository.ItemRepository

class MainActivityViewModel : ViewModel() {

    private val _itemsLiveData = MutableLiveData<List<Item>>(mutableListOf())
    val itemsLiveData: LiveData<List<Item>>
        get() = _itemsLiveData

    fun fetchItems() {
        if (itemsLiveData.value == null || _itemsLiveData.value?.isEmpty() == true) {
            ItemRepository.instance.addDummyListOfItems()
        }
        _itemsLiveData.value = ItemRepository.instance.items
    }
}