package com.rezac.affirmation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rezac.affirmation.databinding.ListItemBinding
import com.rezac.affirmation.model.Affirmation

class ItemAdapter(
    private val context: Context,
    private val dataset: List<Affirmation>
): RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {
    class ItemViewHolder(
        binding: ListItemBinding
    ): RecyclerView.ViewHolder(binding.root) {
        val itemTitle = binding.itemTitle
        val itemImage = binding.itemImage
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val bindingLayout = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(bindingLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position]
        holder.itemTitle.text = context.resources.getString(item.stringResourceId)
        holder.itemImage.setImageResource(item.imageResourceId)
    }

    override fun getItemCount(): Int = dataset.size
}