package com.example.e_shop.main.presentation.category.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.e_shop.R
import com.example.e_shop.databinding.CategoryItemRcViewBinding
import com.example.e_shop.main.domain.model.Product
import com.google.android.material.snackbar.Snackbar

class CategoryItemAdapter  : RecyclerView.Adapter<CategoryItemAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = CategoryItemRcViewBinding.bind(itemView)
    }

    private val differCallback = object : DiffUtil.ItemCallback<Product>(){

        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id
        }
    }
    internal val differ = AsyncListDiffer(this,differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.category_item_rc_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = differ.currentList[position]
        val context = holder.itemView.context

        holder.binding.apply {
            item.apply {

                categoryItemName.text = title
                categoryItemPrice.text = "$price$"
                Glide.with(context)
                    .load(images[0])
                    .error(R.drawable.ic_launcher_foreground)
                    .centerCrop()
                    .into(categoryItemImage)

                holder.itemView.setOnClickListener {
                    Snackbar.make(itemCategoryLayout, "Developing...", Snackbar.LENGTH_SHORT).show()
                }

                addToFavorite.setOnClickListener {
                    Snackbar.make(itemCategoryLayout, "Developing...", Snackbar.LENGTH_SHORT).show()
                }
            }
        }
    }
    override fun getItemCount(): Int = differ.currentList.size
}