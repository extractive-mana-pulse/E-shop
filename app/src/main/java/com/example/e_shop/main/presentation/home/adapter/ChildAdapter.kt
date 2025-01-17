package com.example.e_shop.main.presentation.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.e_shop.R
import com.example.e_shop.databinding.ChildRcViewBinding
import com.example.e_shop.main.domain.model.Product

class ChildAdapter(private val clickEvents: (ChildClickEvents, Product) -> Unit) : RecyclerView.Adapter<ChildAdapter.ViewHolder>() {

    enum class ChildClickEvents{ ADD_TO_FAVORITE, ITEM }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ChildRcViewBinding.bind(itemView)
    }

    private val differCallback = object : DiffUtil.ItemCallback<Product>() {

        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id
        }
    }

    internal val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.child_rc_view, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = differ.currentList[position]
        val context = holder.itemView.context

        holder.binding.apply {

//            fun loadImages(images: ArrayList<String>) {
//                val imageList = ArrayList<SlideModel>()
//
//                for (image in images) {
//                    imageList.add(SlideModel(image))
//                }
//                categoryItemImage.setImageList(imageList)
//            }


            item.apply {
                holder.itemView.setOnClickListener { clickEvents(ChildClickEvents.ITEM, item) }
                addToFavorite.setOnClickListener { clickEvents(ChildClickEvents.ADD_TO_FAVORITE, item) }

                categoryItemName.text = title
                categoryItemPrice.text = "$price$"
                Glide.with(context).load(images[0]).into(categoryItemImage)

            }
        }
    }

    override fun getItemCount(): Int = differ.currentList.size
}