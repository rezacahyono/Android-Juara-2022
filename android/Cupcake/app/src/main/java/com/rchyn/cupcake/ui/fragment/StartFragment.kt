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
import com.rchyn.cupcake.databinding.FragmentStartBinding
import com.rchyn.cupcake.model.OrderViewModel

class StartFragment : Fragment() {

    private val shareViewModel: OrderViewModel by activityViewModels()

    private var _binding: FragmentStartBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d("TAG", "onCreateView: ${shareViewModel.price.value.toString()}")
        _binding = FragmentStartBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            startFragment = this@StartFragment
        }
    }

    fun orderCupcake(quantity: Int) {
        shareViewModel.setQuantity(quantity)
        if (shareViewModel.hasNoFlavorSet()) {
            shareViewModel.setFlavor(getString(R.string.vanilla))
        }
        findNavController().navigate(R.id.action_startFragment_to_flavorFragment)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}