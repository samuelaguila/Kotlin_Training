package com.saam.cursoantonio.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.core.os.bundleOf
import androidx.lifecycle.*
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso


fun Button.setOnClickListener2(lambda: (Button) -> Unit){
    lambda(this)
}

fun Activity.toast(message: String){
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun RecyclerView.ViewHolder.toast(message: String){
    Toast.makeText(itemView.context, message, Toast.LENGTH_SHORT).show()
}

fun ViewGroup.inflate(@LayoutRes layout: Int): View {
    return LayoutInflater.from(context).inflate(layout, this, false)
}

fun ImageView.loadUrl(url: String){
    Picasso.get().load(url).into(this)
}

inline fun <reified T : Activity> Context.startActivity(vararg pairs: Pair<String, Any?>){
    val intent= Intent(this, T::class.java).apply {
        putExtras(bundleOf(*pairs))
    }
    startActivity(intent)
}

inline fun <reified T : ViewModel> ViewModelStoreOwner.getViewModel(body: T.() -> Unit): T {
    return ViewModelProvider(this)[T::class.java].apply(body)

}

fun <T> LifecycleOwner.observer(liveData: LiveData<T>, observer: (T) -> Unit){
    liveData.observe(this, Observer(observer))
}
