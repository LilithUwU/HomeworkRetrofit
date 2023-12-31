package com.example.homeworkretrofit.fragments.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.homeworkretrofit.R
import com.example.homeworkretrofit.databinding.ProductListItemBinding
import com.example.homeworkretrofit.fragments.model.ProductModelEntity

class RecycleViewAdapter: RecyclerView.Adapter<RecycleViewAdapter.ViewHolder>() {

    private var values = mutableListOf<ProductModelEntity>()
    private var onItemClick: (ProductModelEntity) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ProductListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        with(holder) {
            title.text = item.title
            description.text = item.description
            price.text = item.price.toString()

            Glide.with(itemView.context)
                .load(item.thumbnail)
                .placeholder(R.drawable.baseline_image_24)
                .error(R.drawable.baseline_error_24)
                .into(thumbnail)
        }
    }

    override fun getItemCount(): Int =values.size



    inner class ViewHolder(binding: ProductListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                onItemClick(values[absoluteAdapterPosition])
            }
        }
        val thumbnail: ImageView = binding.imageIv //initialized here
        val title: TextView = binding.titleTv
        val description: TextView = binding.descriptionTv
        val price: TextView=binding.priceTv
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(items: List<ProductModelEntity>?) {
        if (items != null) {
            values = items.toMutableList()
        }
        notifyDataSetChanged()
    }

    fun addItem(item: ProductModelEntity) {
        values.add(item)
        notifyItemInserted(values.size - 1)
    }

    fun setOnItemClickListener(callback: (ProductModelEntity) -> Unit) {
        onItemClick = callback
    }

}