package com.saam.cursoantonio.util

data class Event<out T> (private val content: T) {

   private var hasBeenHandled= false

    fun getContentInfoIfNotHandled(): T? {
         return if (hasBeenHandled){
             null
         } else {
            hasBeenHandled= true
            content
         }
    }
}