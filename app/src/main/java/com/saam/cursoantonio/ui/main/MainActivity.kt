package com.saam.cursoantonio.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.saam.cursoantonio.ui.MediaAdapter
import com.saam.cursoantonio.R
import com.saam.cursoantonio.data.Filter
import com.saam.cursoantonio.data.MediaItem.Type
import com.saam.cursoantonio.databinding.ActivityMainBinding
import com.saam.cursoantonio.ui.*
import com.saam.cursoantonio.ui.detail.DetailedActivity

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel
    private val adapter by lazy {
        MediaAdapter(listener= mainViewModel::onMediaItemClicked) //function that work as lambda
    }
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel = getViewModel(){
            observer(progressVisible) {
                binding.progressBar.visibility = if (it) View.VISIBLE else View.GONE
            }
            observer(items){
                adapter.items= it
            }
            observer(navigateToDetail) {
                it.getContentInfoIfNotHandled()?.let { itemId ->
                    startActivity<DetailedActivity>(DetailedActivity.EXTRA_ID to itemId)
                }
            }
        }
        
        binding.recyclerView.layoutManager = GridLayoutManager(this, 2)
        binding.recyclerView.adapter = adapter

        mainViewModel.updateItems()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val filter=  when (item.itemId) {
            R.id.all -> Filter.None
            R.id.fotos -> Filter.ByType(Type.PHOTO)
            R.id.videos -> Filter.ByType(Type.VIDEO)
            else -> Filter.None
        }

        mainViewModel.updateItems(filter)
        return super.onOptionsItemSelected(item)
    }

}