package com.rchyn.iventory.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.rchyn.iventory.InventoryApplication
import com.rchyn.iventory.R
import com.rchyn.iventory.data.Item
import com.rchyn.iventory.data.getFormattedPrice
import com.rchyn.iventory.databinding.FragmentItemDetailBinding
import com.rchyn.iventory.databinding.FragmentItemListBinding
import com.rchyn.iventory.viewmodel.InventoryViewModel
import com.rchyn.iventory.viewmodel.InventoryViewModelFactory

class ItemDetailFragment : Fragment() {

    private val viewModel: InventoryViewModel by activityViewModels {
        InventoryViewModelFactory(
            (activity?.application as InventoryApplication).database.itemDao()
        )
    }

    private val navigateArgs: ItemDetailFragmentArgs by navArgs()

    private lateinit var item: Item

    private var _binding: FragmentItemDetailBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentItemDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = navigateArgs.itemId
        viewModel.retrieveItem(id).observe(this.viewLifecycleOwner) { selectedItem ->
            item = selectedItem
            bind(item)
        }
    }

    private fun bind(item: Item) {
        binding.apply {
            itemName.text = item.itemName
            itemPrice.text = item.getFormattedPrice()
            itemCount.text = item.quantityInStock.toString()

            sellItem.isEnabled = viewModel.isStockAvailable(item)
            sellItem.setOnClickListener { viewModel.sellItem(item) }

            deleteItem.setOnClickListener {
                showConfirmationDialog()
            }

            editItem.setOnClickListener {
                editItem()
            }

        }
    }

    private fun showConfirmationDialog() {
        MaterialAlertDialogBuilder(requireContext()).apply {
            setTitle(getString(android.R.string.dialog_alert_title))
            setMessage(getString(R.string.delete_question))
            setCancelable(false)
            setNegativeButton(getString(R.string.no)) { _, _ -> }
            setPositiveButton(getString(R.string.yes)) { _, _ ->
                deleteItem()
            }
        }.show()
    }

    private fun editItem() {
        val action = ItemDetailFragmentDirections.actionItemDetailFragmentToAddItemFragment(
            getString(R.string.edit_fragment_title),
            item.id
        )
        findNavController().navigate(action)
    }

    private fun deleteItem() {
        viewModel.deleteItem(item)
        findNavController().navigateUp()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}