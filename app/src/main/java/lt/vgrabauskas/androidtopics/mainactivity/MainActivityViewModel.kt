package lt.vgrabauskas.androidtopics.mainactivity

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import lt.vgrabauskas.androidtopics.repository.Item
import lt.vgrabauskas.androidtopics.repository.ItemRepository

class MainActivityViewModel(private val state: SavedStateHandle) : ViewModel() {

    private val _uiState = MutableStateFlow(MainActivityUIState())
    val uiState = _uiState.asStateFlow()

    private val _isDeletedUIState = MutableSharedFlow<MessageDisplayUIState>(0)
    val isDeletedUIState = _isDeletedUIState

    val positionListViewStateFlow: StateFlow<Int> = state.getStateFlow(ITEM_POSITION_LIST_VIEW, -1)

//    val positionListView = state.get<Int>(ITEM_POSITION_LIST_VIEW)


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
            _isDeletedUIState.emit(
                MessageDisplayUIState(
                    item = item,
                    isDeleted = ItemRepository.instance.deleteItem(item)
                )
            )
//            ItemRepository.instance.deleteItem(Item(-100, "", "")))
//            _isDeletedUIState.update {
//                ItemRepository.instance.deleteItem(item)
//            }
        }
    }

    fun savePositionListView(position: Int) {
        state[ITEM_POSITION_LIST_VIEW] = position
    }

    companion object {
        const val ITEM_POSITION_LIST_VIEW = "lt.vgrabauskas.androidtopics.mainactivity.position.listview"
    }
}

