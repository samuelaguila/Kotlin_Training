package com.saam.cursoantonio.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saam.cursoantonio.data.MediaItem
import com.saam.cursoantonio.data.MediaProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailedViewModel: ViewModel() {

    private val _item= MutableLiveData<MediaItem>()
    val item: LiveData<MediaItem> get() = _item

    fun updateItems(itemId: Int) {
        viewModelScope.launch(Dispatchers.Main) {
            val items = withContext(Dispatchers.IO) { MediaProvider.getListItems() }
            _item.value = items.firstOrNull { it.id == itemId }
        }
    }
}