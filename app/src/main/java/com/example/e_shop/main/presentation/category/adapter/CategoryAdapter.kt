package com.example.e_shop.main.presentation.category.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.e_shop.R
import com.example.e_shop.databinding.CategoryRcViewBinding
import com.example.e_shop.main.domain.model.Category

class CategoryAdapter  : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = CategoryRcViewBinding.bind(itemView)
    }

    private val differCallback = object : DiffUtil.ItemCallback<Category>(){

        override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem.id == newItem.id
        }
    }
    internal val differ = AsyncListDiffer(this,differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.category_rc_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = differ.currentList[position]
        val context = holder.itemView.context

        holder.binding.apply {
            item.apply {

                categoryName.text = name

                if (name == "Testing Category"){
                    categoryLayout.visibility = View.GONE
                } else {
                    categoryLayout.visibility = View.VISIBLE
                }

                Glide.with(context)
                    .load(image ?: R.drawable.ic_launcher_foreground)
                    .centerCrop()
                    .into(categoryImage)
            }
        }

        holder.itemView.setOnClickListener {

            val bundle = Bundle().apply {
                putString("name", item.name)
                putString("image", item.image)
                putString("id", item.id.toString())
            }

            val navController = Navigation.findNavController(holder.itemView)
            navController.navigate(R.id.categoryItemFragment, bundle)
        }
    }
    override fun getItemCount(): Int = differ.currentList.size
}