package com.rchyn.dogglers.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rchyn.dogglers.R
import com.rchyn.dogglers.model.Dog
import com.rchyn.dogglers.utils.Layout

class DogCardAdapter(
    private val context: Context,
    private val layout: Int
) : RecyclerView.Adapter<DogCardAdapter.DogCardViewHolder>() {
    private val dataset: ArrayList<Dog> = arrayListOf()

    fun setAdapter(list: List<Dog>){
        if (list.isNullOrEmpty()) return
        dataset.clear()
        dataset.addAll(list)
    }
    class DogCardViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageDog = view.findViewById<ImageView>(R.id.image_dog)
        val titleDOg = view.findViewById<TextView>(R.id.title_dog)
        val ageDog = view.findViewById<TextView>(R.id.age_dog)
        val hobbyDog = view.findViewById<TextView>(R.id.hobby_dog)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogCardViewHolder {
        val adapterLayout: View = when (layout) {
            Layout.GRID -> LayoutInflater.from(parent.context).inflate(R.layout.grid_list_item, parent, false)
            else -> LayoutInflater.from(parent.context)
                .inflate(R.layout.vertical_horizontal_list_item, parent, false)
        }

        return DogCardViewHolder(view = adapterLayout)
    }

    override fun onBindViewHolder(holder: DogCardViewHolder, position: Int) {
        holder.imageDog.setImageResource(dataset[position].imageResourceId)
        holder.titleDOg.text = dataset[position].name
        holder.ageDog.text = context.resources.getString(R.string.dog_age, dataset[position].age)
        holder.hobbyDog.text = context.resources.getString(R.string.dog_hobby, dataset[position].hobbies)
    }

    override fun getItemCount(): Int = dataset.size
}