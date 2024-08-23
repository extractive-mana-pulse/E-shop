package com.example.e_shop.main.presentation.favorite.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.denzcoskun.imageslider.models.SlideModel
import com.example.e_shop.R
import com.example.e_shop.databinding.FavoriteItemRcViewBinding
import com.example.e_shop.main.domain.model.Product
import com.google.android.material.snackbar.Snackbar

class FavoriteAdapter(private val clickEvent: (FavoriteAdapterClickHandler, Product) -> Unit)  : RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {

    enum class FavoriteAdapterClickHandler {
        ITEM,
        REMOVE_FROM_WISHLIST
    }

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

    internal val differ = AsyncListDiffer(this,differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.favorite_item_rc_view, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = differ.currentList[position]
        val context = holder.itemView.context

        holder.binding.apply {

            fun loadImages(images: ArrayList<String>) {
                val imageList = ArrayList<SlideModel>()
                for (image in images) { imageList.add(SlideModel(image)) }
                favoriteItemImage.setImageList(imageList)
            }

            item.apply {

                favoriteItemName.text = title
                favoriteItemPrice.text = "$price$"
                loadImages(images)

                holder.itemView.setOnClickListener { clickEvent(FavoriteAdapterClickHandler.ITEM, item) }
                removeFromFavorite.setOnClickListener { clickEvent(FavoriteAdapterClickHandler.REMOVE_FROM_WISHLIST, item) }
            }
        }
    }
    override fun getItemCount(): Int = differ.currentList.size
}