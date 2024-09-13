package com.example.e_shop.profile.presentation.address.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.e_shop.R
import com.example.e_shop.databinding.AddressLayoutBinding
import com.example.e_shop.profile.domain.model.Address

class AddressAdapter(private val clickEvent: (AddressAdapterClickHandler, Address) -> Unit)  : RecyclerView.Adapter<AddressAdapter.ViewHolder>() {

    enum class AddressAdapterClickHandler {
        ITEM,
        EDIT
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = AddressLayoutBinding.bind(itemView)
    }

    private val differCallback = object : DiffUtil.ItemCallback<Address>(){

        override fun areItemsTheSame(oldItem: Address, newItem: Address): Boolean = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Address, newItem: Address): Boolean = oldItem.id == newItem.id
    }

    internal val differ = AsyncListDiffer(this,differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.address_layout, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = differ.currentList[position]
        val context = holder.itemView.context

        holder.binding.apply {
            addressTv.text = "${item.city}, ${item.streetAddress} ${item.state}"
            addressEditTv.setOnClickListener { clickEvent(AddressAdapterClickHandler.EDIT, item) }
            holder.itemView.setOnClickListener{ clickEvent(AddressAdapterClickHandler.ITEM, item) }
        }
    }
    override fun getItemCount(): Int = differ.currentList.size
}