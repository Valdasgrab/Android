package lt.vgrabauskas.androidtopics.mainactivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import lt.vgrabauskas.androidtopics.repository.Item
import lt.vgrabauskas.androidtopics.repository.ItemRepository

class MainActivityViewModel : ViewModel() {

    private val _itemsLiveData = MutableLiveData<List<Item>>(mutableListOf())
    val itemsLiveData: LiveData<List<Item>>
        get() = _itemsLiveData

    private val _isLoadingLiveData = MutableLiveData(true)
    val isLoadingLiveData: LiveData<Boolean>
        get() = _isLoadingLiveData

    fun fetchItems() {
        viewModelScope.launch {

            _isLoadingLiveData.value = true

            if (itemsLiveData.value == null || _itemsLiveData.value?.isEmpty() == true) {
                ItemRepository.instance.addDummyListOfItems()
            }

            delay((1000..4000).random().toLong())

            _itemsLiveData.value = ItemRepository.instance.getItems()

            _isLoadingLiveData.value = false
        }


    }

}