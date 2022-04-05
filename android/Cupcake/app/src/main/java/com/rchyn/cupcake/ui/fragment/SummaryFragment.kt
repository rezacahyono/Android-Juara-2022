package com.rchyn.cupcake.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.google.android.material.snackbar.Snackbar
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
    ): View? {
        // Inflate the layout for this fragment
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
        Snackbar.make(binding.root, getString(R.string.send_order), Snackbar.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}