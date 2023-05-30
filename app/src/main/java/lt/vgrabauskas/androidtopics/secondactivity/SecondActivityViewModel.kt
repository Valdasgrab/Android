package lt.vgrabauskas.androidtopics.secondactivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import lt.vgrabauskas.androidtopics.repository.Item
import lt.vgrabauskas.androidtopics.repository.ItemRepository

class SecondActivityViewModel : ViewModel() {

    private val _itemLiveData = MutableLiveData<Item>()
    val itemLiveData: LiveData<Item>
        get() = _itemLiveData

    fun fetchItem(itemId: Int) {
        if (itemLiveData.value == null) {
            if (itemId > 0) {
                _itemLiveData.value = ItemRepository.instance.getItem(itemId)
            } else {
                _itemLiveData.value = Item(-1, "", "")
            }
        }
    }

    fun saveItem(item: Item) {
        if (item != null) {
            if (item.id > 0) {
                ItemRepository.instance.updateItem(item)
            } else {
                ItemRepository.instance.addItem(item)
            }
        }

//        ItemRepository.instance.
    }
}