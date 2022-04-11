package com.rchyn.amphibians.adapter

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rchyn.amphibians.R
import com.rchyn.amphibians.model.Amphibian
import com.rchyn.amphibians.ui.fragment.viewmodel.AmphibianApiStatus

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Amphibian>?) {
    val adapter = recyclerView.adapter as AmphibianListAdapter
    adapter.submitList(data)
}

@BindingAdapter("amphibianStatus")
fun bindStatus(imageView: ImageView, status: AmphibianApiStatus?) {
    when (status) {
        AmphibianApiStatus.LOADING -> {
            imageView.visibility = View.VISIBLE
            imageView.setImageResource(R.drawable.ic_loading_animation)
        }
        AmphibianApiStatus.DONE -> {
            imageView.visibility = View.GONE
        }
        AmphibianApiStatus.ERROR -> {
            imageView.visibility = View.VISIBLE
            imageView.setImageResource(R.drawable.ic_connection_error)
        }
    }
}