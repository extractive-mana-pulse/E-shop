package com.example.e_shop.main.presentation.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.e_shop.main.domain.model.ParentItem
import com.example.e_shop.R
import com.example.e_shop.databinding.ParentRcViewBinding

class ParentAdapter(private val clickEvent: (ParentClickEvent, ParentItem) -> Unit): RecyclerView.Adapter<ParentAdapter.ViewHolder>() {

    enum class ParentClickEvent{
        ITEM,
        SEE_ALL,
        ADD_TO_FAVORITE
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ParentRcViewBinding.bind(itemView)
    }

    private val differCallback = object : DiffUtil.ItemCallback<ParentItem>() {

        override fun areItemsTheSame(oldItem: ParentItem, newItem: ParentItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ParentItem, newItem: ParentItem): Boolean {
            return oldItem.id == newItem.id
        }
    }

    internal val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.parent_rc_view, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = differ.currentList[position]
        val context = holder.itemView.context

        holder.binding.apply {
            item.apply {

                parentAdapterTitleTv.text = title

                // Create ChildAdapter with click events handling
                val childAdapter = ChildAdapter { event, product ->
                    when (event) {
                        ChildAdapter.ChildClickEvents.ITEM -> {
                            // Handle item click
                            clickEvent(ParentClickEvent.ITEM, item)
                        }
                        ChildAdapter.ChildClickEvents.ADD_TO_FAVORITE -> {
                            // Handle add to favorite click
                            // For example, you can do something like:
                            clickEvent(ParentClickEvent.ADD_TO_FAVORITE, item)
                        }
                    }
                }
                childRcView.adapter = childAdapter
                childAdapter.differ.submitList(childItems)
                childRcView.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)

                holder.itemView.setOnClickListener { clickEvent(ParentClickEvent.ITEM, item) }
                seeAllCategories.setOnClickListener { clickEvent(ParentClickEvent.SEE_ALL, item) }
            }
        }
    }
    override fun getItemCount(): Int = differ.currentList.size
}