package com.rchyn.iventory.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.rchyn.iventory.InventoryApplication
import com.rchyn.iventory.R
import com.rchyn.iventory.adapter.ItemListAdapter
import com.rchyn.iventory.databinding.FragmentItemListBinding
import com.rchyn.iventory.viewmodel.InventoryViewModel
import com.rchyn.iventory.viewmodel.InventoryViewModelFactory


class ItemListFragment : Fragment() {
    private val viewModel: InventoryViewModel by activityViewModels {
        InventoryViewModelFactory(
            (activity?.application as InventoryApplication).database.itemDao()
        )
    }

    private var _binding: FragmentItemListBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentItemListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = ItemListAdapter {
            val action = ItemListFragmentDirections.actionItemListFragmentToItemDetailFragment(
                itemId = it.id
            )
            view.findNavController().navigate(action)
        }

        binding.apply {
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            recyclerView.adapter = adapter
            floatingActionButton.setOnClickListener {
                val action = ItemListFragmentDirections.actionItemListFragmentToAddItemFragment(
                    getString(R.string.add_fragment_title)
                )
                view.findNavController().navigate(action)
            }
        }

        viewModel.allItems.observe(this.viewLifecycleOwner) { items ->
            items.let {
                adapter.submitList(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}