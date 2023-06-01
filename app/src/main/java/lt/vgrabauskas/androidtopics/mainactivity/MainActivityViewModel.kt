package lt.vgrabauskas.androidtopics.mainactivity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import lt.vgrabauskas.androidtopics.repository.Item
import lt.vgrabauskas.androidtopics.repository.ItemRepository

class MainActivityViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(MainActivityUIState())
    val uiState = _uiState.asStateFlow()
    private val _isDeletedUIState = MutableStateFlow(false)
    val isDeletedUIState = _isDeletedUIState


    fun fetchItems() {
        viewModelScope.launch(Dispatchers.IO) {

            _uiState.update {
                it.copy(isLoading = true, isListVisible = false)
            }

            if (_uiState.value.items.isEmpty()) {
                ItemRepository.instance.addDummyListOfItems()
            }

            delay((1000..4000).random().toLong())

            _uiState.update {
                it.copy(
                    items = ItemRepository.instance.getItems(),
                    isLoading = false,
                    isListVisible = true
                )
            }
        }
    }

    fun deleteItem(item: Item) {
        viewModelScope.launch(Dispatchers.IO) {
            _isDeletedUIState.update {
                ItemRepository.instance.deleteItem(item)
            }
        }
    }
}

