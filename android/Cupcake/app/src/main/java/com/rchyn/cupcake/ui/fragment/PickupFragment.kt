package com.rchyn.cupcake.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.rchyn.cupcake.R
import com.rchyn.cupcake.databinding.FragmentPickupBinding
import com.rchyn.cupcake.model.OrderViewModel

class PickupFragment : Fragment() {

    private val shareViewModel: OrderViewModel by activityViewModels()

    private var _binding: FragmentPickupBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPickupBinding.inflate(layoutInflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            pickupFragment = this@PickupFragment
            viewModel = shareViewModel
            lifecycleOwner = viewLifecycleOwner
        }
    }

    fun goToNextScreen() {
        findNavController().navigate(R.id.action_pickupFragment_to_summaryFragment)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}