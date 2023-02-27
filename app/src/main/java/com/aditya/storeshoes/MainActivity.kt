package com.aditya.storeshoes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.aditya.storeshoes.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mAdapter: Adapterstore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val viewModel = ViewModelProvider(
            this@MainActivity, ViewModelProvider.NewInstanceFactory()
        )[MainViewModel::class.java]

        mAdapter = Adapterstore { store ->
            Intent(this@MainActivity, ActivityDetail::class.java).also {
                it.putExtra("id", store.id)
                it.putExtra("nama", store.nama)
                it.putExtra("nomor", store.nomor)
                it.putExtra("warna", store.warna)
                it.putExtra("harga", store.harga)
                it.putExtra("image", store.image)
                startActivity(it)
            }
        }

        viewModel.store.observe(this@MainActivity) { listData ->
            Log.d("Response::::::", "onCreate: $listData")
            mAdapter.submitList(listData)
            binding.rvStore.adapter = mAdapter
            binding.rvStore.layoutManager = LinearLayoutManager(this@MainActivity)
            binding.rvStore.setHasFixedSize(true)
        }

        viewModel.isLoading.observe(this@MainActivity) { isLoading ->

            binding.progressBar.isVisible = isLoading
        }
    }



//    private fun deleteDatabase() {
//        binding.imgDelete.setOnClickListener {
//            mAdapter = Adapterstore { store ->
//                Intent(this@MainActivity, ActivityDetail::class.java).also {
//                    it.removeExtra("nama")
//                    it.removeExtra("nomor")
//                    it.removeExtra("warna")
//                    it.removeExtra("harga")
//                    it.removeExtra("image")
//                    startActivity(it)
//                }
//
//            }
//        }
//    }
}