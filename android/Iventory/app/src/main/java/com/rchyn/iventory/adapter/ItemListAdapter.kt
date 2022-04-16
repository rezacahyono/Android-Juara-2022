package com.rchyn.iventory.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rchyn.iventory.data.Item
import com.rchyn.iventory.data.getFormattedPrice
import com.rchyn.iventory.databinding.ItemListItemBinding

class ItemListAdapter(
    private val onItemClicked: (Item) -> Unit
) : ListAdapter<Item, ItemListAdapter.ItemListViewHolder>(DiffCallback) {

    class ItemListViewHolder(
        private val binding: ItemListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Item) {
            binding.apply {
                itemName.text = item.itemName
                itemPrice.text = item.getFormattedPrice()
                itemQuantity.text = item.quantityInStock.toString()
            }
        }

    }


    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Item>() {
            override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem.itemName == newItem.itemName
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemListViewHolder {
        val bindingLayout =
            ItemListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemListViewHolder(bindingLayout)
    }

    override fun onBindViewHolder(holder: ItemListViewHolder, position: Int) {
        val current = getItem(position)
        holder.itemView.setOnClickListener {
            onItemClicked(current)
        }
        holder.bind(getItem(position))
    }
}