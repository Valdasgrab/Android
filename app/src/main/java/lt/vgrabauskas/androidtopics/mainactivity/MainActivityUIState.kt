package lt.vgrabauskas.androidtopics.mainactivity

import lt.vgrabauskas.androidtopics.repository.Item

data class MainActivityUIState(
    val items: List<Item> = mutableListOf(),
    val isLoading: Boolean = false,
    val isListVisible: Boolean = true
)
