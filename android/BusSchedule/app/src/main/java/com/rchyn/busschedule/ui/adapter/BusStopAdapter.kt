package com.rchyn.busschedule.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rchyn.busschedule.database.entity.Schedule
import com.rchyn.busschedule.databinding.BusStopItemBinding
import java.text.SimpleDateFormat
import java.util.*

class BusStopAdapter(
    private val onItemClicked: (Schedule) -> Unit
) : ListAdapter<Schedule, BusStopAdapter.BusStopViewHolder>(DiffCallback) {
    class BusStopViewHolder(
        private val binding: BusStopItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(schedule: Schedule) {
            binding.apply {
                stopNameTextView.text = schedule.stopName
                arrivalTimeTextView.text = SimpleDateFormat(
                    "h:mm a", Locale.US
                ).format(Date(schedule.arrivalTime.toLong() * 1000))
            }
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Schedule>() {
        override fun areItemsTheSame(oldItem: Schedule, newItem: Schedule): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Schedule, newItem: Schedule): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BusStopViewHolder {
        val bindingLayout =
            BusStopItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val viewHolder = BusStopViewHolder(bindingLayout)
        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.adapterPosition
            onItemClicked(getItem(position))
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: BusStopViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}