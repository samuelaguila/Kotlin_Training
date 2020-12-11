package com.saam.cursoantonio.data

class MediaItem (val id: Int, val name:String, val url:String, val type: Type){
    enum class Type {
        PHOTO,
        VIDEO}
}