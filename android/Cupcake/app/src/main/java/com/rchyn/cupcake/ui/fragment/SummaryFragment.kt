package com.rchyn.cupcake.ui.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.rchyn.cupcake.R
import com.rchyn.cupcake.databinding.FragmentSummaryBinding
import com.rchyn.cupcake.model.OrderViewModel

class SummaryFragment : Fragment() {

    private val shareViewModel: OrderViewModel by activityViewModels()

    private var _binding: FragmentSummaryBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSummaryBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            summaryFragment = this@SummaryFragment
            viewModel = shareViewModel
            lifecycleOwner = viewLifecycleOwner
        }
    }

    fun sendOrder() {
        val numberOfCupcakes = shareViewModel.quantity.value ?: 0
        val orderSummary = getString(
            R.string.order_details,
            resources.getQuantityString(R.plurals.cupcakes, numberOfCupcakes, numberOfCupcakes),
            shareViewModel.flavor.value.toString(),
            shareViewModel.date.value.toString(),
            shareViewModel.price.value.toString()
        )
        val intent = Intent(Intent.ACTION_SEND)
        intent.apply {
            type = "text/plan"
            putExtra(Intent.EXTRA_SUBJECT, getString(R.string.new_cupcake_order))
            putExtra(Intent.EXTRA_TEXT, orderSummary)
        }
        if (activity?.packageManager?.resolveActivity(intent, 0) != null) {
            startActivity(intent)
        }
    }

    fun cancelOrder() {
        findNavController().navigate(R.id.action_summaryFragment_to_startFragment)
            shareViewModel.resetOrder()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}