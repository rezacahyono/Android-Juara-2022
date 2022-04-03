package com.rchyn.words.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.rchyn.words.adapter.WordsAdapter
import com.rchyn.words.databinding.FragmentWordListBinding

class WordListFragment : Fragment() {

    private var _binding: FragmentWordListBinding? = null
    private val binding get() = _binding!!

    companion object {
        const val LETTER_EXTRAS = "letter"
        const val SEARCH_PREFIX = "https://www.google.com/search?q="
    }
    private lateinit var letterId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            letterId = it.getString(LETTER_EXTRAS).toString()
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentWordListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = binding.recyclerWords
        recyclerView.layoutManager = LinearLayoutManager(this.requireContext())
        recyclerView.adapter = WordsAdapter(letterId, requireContext())

        recyclerView.addItemDecoration(
            DividerItemDecoration(context,DividerItemDecoration.VERTICAL)
        )
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}