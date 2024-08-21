package com.example.e_shop.main.presentation.category.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.denzcoskun.imageslider.models.SlideModel
import com.example.e_shop.R
import com.example.e_shop.databinding.CategoryItemRcViewBinding
import com.example.e_shop.main.domain.model.Product
import com.google.android.material.snackbar.Snackbar

class CategoryItemAdapter(private val clickEvent: (CategoryItemClickHandler, Product) -> Unit)  : RecyclerView.Adapter<CategoryItemAdapter.ViewHolder>() {

    enum class CategoryItemClickHandler {
        ITEM,
        ADD_TO_FAVORITE
    }

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

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = differ.currentList[position]
        val context = holder.itemView.context

        holder.binding.apply {

            fun loadImages(images: ArrayList<String>) {
                val imageList = ArrayList<SlideModel>()
                for (image in images) { imageList.add(SlideModel(image)) }
                categoryItemImage.setImageList(imageList)
            }

            item.apply {

                categoryItemName.text = title
                categoryItemPrice.text = "$price$"
                loadImages(images)

                holder.itemView.setOnClickListener { clickEvent(CategoryItemClickHandler.ITEM, item) }
                addToFavorite.setOnClickListener { clickEvent(CategoryItemClickHandler.ADD_TO_FAVORITE, item) }
            }
        }
    }
    override fun getItemCount(): Int = differ.currentList.size
}