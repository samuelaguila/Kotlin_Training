package com.saam.cursoantonio.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saam.cursoantonio.util.Event
import com.saam.cursoantonio.data.Filter
import com.saam.cursoantonio.data.MediaItem
import com.saam.cursoantonio.data.MediaProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(): ViewModel() {

       private val _progressVisible= MutableLiveData<Boolean>()
       val progressVisible: LiveData<Boolean> get() = _progressVisible

       private val _items= MutableLiveData<List<MediaItem>>()
       val items: LiveData<List<MediaItem>> get()= _items

       private val _navigateToDetail= MutableLiveData<Event<Int>>()
       val navigateToDetail:LiveData<Event<Int>> get() = _navigateToDetail

     fun updateItems(filter: Filter = Filter.None) {
         viewModelScope.launch {
             _progressVisible.value= true
             _items.value = withContext(Dispatchers.IO) { getFilteredItems(filter) }
             _progressVisible.value= false
        }
    }

     fun getFilteredItems(filter: Filter): List<MediaItem> {
        return MediaProvider.getListItems().let { media ->
            when (filter) {
                Filter.None -> media
                is Filter.ByType -> media.filter { it.type == filter.type }
            }
        }
    }

    fun onMediaItemClicked (id: MediaItem) {
        _navigateToDetail.value= Event(id.id)
    }
}