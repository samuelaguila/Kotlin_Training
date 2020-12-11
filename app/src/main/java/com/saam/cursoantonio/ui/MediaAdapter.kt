package com.saam.cursoantonio.ui

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.saam.cursoantonio.R
import com.saam.cursoantonio.data.MediaItem
import com.saam.cursoantonio.data.MediaItem.*
import com.saam.cursoantonio.databinding.ViewMediaItemBinding
import com.squareup.picasso.Picasso
import kotlin.properties.Delegates

class MediaAdapter(items: List<MediaItem> = emptyList(), val listener: (MediaItem) -> Unit): RecyclerView.Adapter<MediaAdapter.ViewHolder>() {

    var items:List<MediaItem> by Delegates.observable(items) { _, _, _ ->
        notifyDataSetChanged() //nnotify the rview to update again
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view= parent.inflate(R.layout.view_media_item)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val mediaItem= items[position]
        holder.bind(mediaItem)
        holder.itemView.setOnClickListener{ listener(mediaItem)}
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(val view: View): RecyclerView.ViewHolder(view){

        val binding= ViewMediaItemBinding.bind(view)

        fun bind(mediaItem: MediaItem){
            with(binding){
                rvTexto.text = mediaItem.name
                Picasso.get().load(mediaItem.url).into(rvImagen)
                rvVideoLogo.visibility= when (mediaItem.type){
                    Type.PHOTO -> View.GONE
                    Type.VIDEO -> View.VISIBLE
                }
            }
        }
    }
}