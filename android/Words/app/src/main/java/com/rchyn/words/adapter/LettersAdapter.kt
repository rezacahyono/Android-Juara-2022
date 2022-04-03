package com.rchyn.words.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.accessibility.AccessibilityNodeInfo
import android.widget.Button
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.rchyn.words.R
import com.rchyn.words.ui.fragment.LetterListFragmentDirections

class LettersAdapter: RecyclerView.Adapter<LettersAdapter.LettersViewHolder>() {
    private val list = ('A').rangeTo('Z').toList()

    class LettersViewHolder(
        view: View
    ): RecyclerView.ViewHolder(view) {
        val letter: Button = view.findViewById(R.id.button_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LettersViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        layout.accessibilityDelegate = Accesbility
        return LettersViewHolder(layout)
    }

    override fun onBindViewHolder(holder: LettersViewHolder, position: Int) {
        val item = list[position].toString()
        holder.letter.text = item
        holder.letter.setOnClickListener {
            val action = LetterListFragmentDirections.actionLetterListFragmentToWordListFragment(letter = holder.letter.text.toString())
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int = list.size

    companion object Accesbility: View.AccessibilityDelegate() {
        override fun onInitializeAccessibilityNodeInfo(host: View?, info: AccessibilityNodeInfo?) {
            super.onInitializeAccessibilityNodeInfo(host, info)
            val customString = host?.context?.getString(R.string.look_up_words)
            val customClick = AccessibilityNodeInfo.AccessibilityAction(
                AccessibilityNodeInfo.ACTION_CLICK,
                customString
            )
            info?.addAction(customClick)
        }
    }
}