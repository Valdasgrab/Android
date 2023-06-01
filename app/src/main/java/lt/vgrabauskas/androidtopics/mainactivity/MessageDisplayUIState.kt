package lt.vgrabauskas.androidtopics.mainactivity

import lt.vgrabauskas.androidtopics.repository.Item

data class MessageDisplayUIState(
    val item: Item = Item(-1, "", ""),
    val isDeleted: Boolean = false
)
