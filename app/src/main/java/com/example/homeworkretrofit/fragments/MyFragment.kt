package com.example.homeworkretrofit.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homeworkretrofit.adapter.RecycleViewAdapter
import com.example.homeworkretrofit.databinding.FragmentMyfragmentBinding

import com.example.homeworkretrofit.model.ProductModel
import com.example.homeworkretrofit.network.RetrfitObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MyFragment:Fragment() {

    private val service=RetrfitObject.createCustomService()

    private lateinit var binding: FragmentMyfragmentBinding
    private val myAdapter=RecycleViewAdapter()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyfragmentBinding.inflate(inflater, container, false)
        myAdapter.setOnItemClickListener {
            Toast.makeText(requireContext(), it.title, Toast.LENGTH_LONG).show()
        }
        binding.list.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = myAdapter
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    LinearLayoutManager.VERTICAL
                )
            )
        }

        binding.showBtn.setOnClickListener {
            loadList()
        }
        return binding.root
    }

    private suspend fun getProducts(): Result<List<ProductModel>> = withContext(Dispatchers.IO) {
        val result = service.getProductList()
        if (result.isSuccessful)
            return@withContext Result.success(result.body() ?: emptyList())
        Result.Failure(result.message(), null)
    }

    private fun loadList() {
        binding.showBtn.visibility = View.GONE
        binding.progressBar.visibility = View.VISIBLE
        lifecycleScope.launch {
            when (val products = getProducts()) {
                is Result.Failure -> {
                    binding.progressBar.visibility = View.GONE
                    binding.showBtn.visibility = View.VISIBLE
                    binding.showBtn.text = "Try Again!"
                }

                is Result.Loading -> {}
                is Result.Success -> {
                    binding.progressBar.visibility = View.GONE
                    binding.list.visibility = View.VISIBLE
                    myAdapter.setList(products.value)
                }
            }
        }
    }









}


