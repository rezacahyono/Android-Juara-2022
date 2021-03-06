package com.rchyn.cupcake.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.rchyn.cupcake.R
import com.rchyn.cupcake.databinding.FragmentFlavorBinding
import com.rchyn.cupcake.model.OrderViewModel

class FlavorFragment : Fragment() {

    private val shareViewModel: OrderViewModel by activityViewModels()

    private var _binding: FragmentFlavorBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFlavorBinding.inflate(layoutInflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            flavorFragment = this@FlavorFragment
            viewModel = shareViewModel
            lifecycleOwner = viewLifecycleOwner
        }
    }

    fun goToNextScreen() {
        findNavController().navigate(R.id.action_flavorFragment_to_pickupFragment)
    }

    fun cancelOrder() {
        findNavController().navigate(R.id.action_flavorFragment_to_startFragment)
        shareViewModel.resetOrder()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}