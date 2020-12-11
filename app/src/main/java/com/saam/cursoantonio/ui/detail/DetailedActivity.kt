package com.saam.cursoantonio.ui.detail

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.saam.cursoantonio.data.MediaItem.Type
import com.saam.cursoantonio.databinding.ActivityDetailedBinding
import com.saam.cursoantonio.ui.*

class DetailedActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_ID = "DetailedActivity:id"
    }

    private lateinit var detailedViewModel: DetailedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDetailedBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val itemId = intent.getIntExtra(EXTRA_ID, -1)

        detailedViewModel = getViewModel {
            observer(item) {
                it.let {
                    supportActionBar?.title = it.name
                    binding.imagenDetalles.loadUrl(it.url)
                    binding.videoIndicadorDetalles.visibility = when (it.type) {
                        Type.PHOTO -> View.GONE
                        Type.VIDEO -> View.VISIBLE
                    }
                }
            }
        }
        detailedViewModel.updateItems(itemId)

    }
}