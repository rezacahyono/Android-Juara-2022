package com.rchyn.words.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.accessibility.AccessibilityNodeInfo
import androidx.recyclerview.widget.RecyclerView
import com.rchyn.words.R
import com.rchyn.words.databinding.ItemViewBinding
import com.rchyn.words.ui.fragment.WordListFragment

class WordsAdapter(
    private val letterId: String,
    context: Context
) : RecyclerView.Adapter<WordsAdapter.WordsViewHolder>() {

    private val filteredWords: List<String>

    init {
        val words = context.resources.getStringArray(R.array.words)

        filteredWords = words
            .filter { it.startsWith(letterId, ignoreCase = true) }
            .shuffled()
            .take(5)
            .sorted()
    }

    class WordsViewHolder(private val binding: ItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(word: String){
            binding.buttonItem.text = word
            binding.buttonItem.setOnClickListener {
                val queryUrl: Uri = Uri.parse("${WordListFragment.SEARCH_PREFIX}$word")
                val intent = Intent(Intent.ACTION_VIEW, queryUrl)
                itemView.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordsViewHolder {
        val bindingLayout = ItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        bindingLayout.buttonItem.accessibilityDelegate = Accessibility
        return WordsViewHolder(bindingLayout)
    }

    override fun onBindViewHolder(holder: WordsViewHolder, position: Int) {
        holder.bind(filteredWords[position])
    }

    override fun getItemCount(): Int = filteredWords.size

    companion object Accessibility : View.AccessibilityDelegate() {
        override fun onInitializeAccessibilityNodeInfo(
            host: View?,
            info: AccessibilityNodeInfo?
        ) {
            super.onInitializeAccessibilityNodeInfo(host, info)
            val customString = host?.context?.getString(R.string.look_up_word)
            val customClick =
                AccessibilityNodeInfo.AccessibilityAction(
                    AccessibilityNodeInfo.ACTION_CLICK,
                    customString
                )
            info?.addAction(customClick)
        }
    }
}