package com.saam.cursoantonio.data

sealed class Filter {
    object None : Filter()
    class ByType(val type: MediaItem.Type): Filter()
}