package com.saam.cursoantonio.data

import androidx.annotation.WorkerThread

object MediaProvider {
    fun getListItems(): List<MediaItem> {
        Thread.sleep(1000)
        return (1..10).map{
            MediaItem(
                 it,
                "Title $it",
                "https://placekitten.com/200/200?image=$it",
                if (it % 3 == 0) MediaItem.Type.PHOTO else MediaItem.Type.VIDEO
            )
        }
    }
}