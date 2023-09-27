package com.example.homeworkretrofit.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homeworkretrofit.databinding.FragmentMyfragmentBinding
import com.example.homeworkretrofit.fragments.adapter.RecycleViewAdapter
import com.example.homeworkretrofit.fragments.model.Result

class MyFragment : Fragment() {

    private val viewModel by lazy {
        ViewModelProvider(requireActivity())[MyFragmentViewModel::class.java]
    }
    private lateinit var binding: FragmentMyfragmentBinding
    private val myAdapter = RecycleViewAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyfragmentBinding.inflate(inflater, container, false)

        initView()
        observers()

        return binding.root
    }

    private fun observers() {
        viewModel.productData.observe(viewLifecycleOwner) { result ->
            when (result) {
                is Result.Failure -> {
                    binding.progressBar.visibility = View.GONE
                    binding.showBtn.visibility = View.VISIBLE
                    binding.showBtn.text = "Try Again!"
                }

                is Result.Loading -> {
                    binding.showBtn.visibility = View.GONE
                    binding.progressBar.visibility = View.VISIBLE
                }

                is Result.Success -> {
                    binding.progressBar.visibility = View.GONE
                    binding.list.visibility = View.VISIBLE
                    myAdapter.setList(result.value)
                }
            }
        }
    }

    private fun initView() {

        myAdapter.setOnItemClickListener { product ->
            Toast.makeText(requireContext(), product.title, Toast.LENGTH_LONG).show()
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
            viewModel.getProducts()
        }
    }
}
