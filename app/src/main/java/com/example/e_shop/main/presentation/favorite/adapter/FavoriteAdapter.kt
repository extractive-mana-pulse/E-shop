package com.example.e_shop.main.presentation.favorite.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.e_shop.R
import com.example.e_shop.databinding.CategoryItemRcViewBinding
import com.example.e_shop.databinding.FavoriteItemRcViewBinding
import com.example.e_shop.main.domain.model.Product
import com.google.android.material.snackbar.Snackbar

class FavoriteAdapter  : RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = FavoriteItemRcViewBinding.bind(itemView)
    }

    private val differCallback = object : DiffUtil.ItemCallback<Product>(){

        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id
        }
    }

    val differ = AsyncListDiffer(this,differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.favorite_item_rc_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = differ.currentList[position]
        val context = holder.itemView.context

        holder.binding.apply {
            item.apply {

                favoriteItemName.text = title
                favoriteItemPrice.text = "$price$"
                Glide.with(context)
                    .load(images[0])
                    .error(R.drawable.ic_launcher_foreground)
                    .centerCrop()
                    .into(favoriteItemImage)

                holder.itemView.setOnClickListener {
                    Snackbar.make(itemFavoriteLayout, "Developing...", Snackbar.LENGTH_SHORT).show()
                }

                removeFromFavorite.setOnClickListener {
                    Snackbar.make(itemFavoriteLayout, "Developing...", Snackbar.LENGTH_SHORT).show()
                }
            }
        }
    }
    override fun getItemCount(): Int = differ.currentList.size
}