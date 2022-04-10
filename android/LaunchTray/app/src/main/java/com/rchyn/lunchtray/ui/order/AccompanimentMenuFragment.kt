package com.rchyn.lunchtray.ui.order

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.rchyn.lunchtray.R
import com.rchyn.lunchtray.databinding.FragmentAccompanimentMenuBinding
import com.rchyn.lunchtray.ui.order.viewmodel.OrderViewModel

class AccompanimentMenuFragment : Fragment() {

    private val viewModel: OrderViewModel by activityViewModels()

    private var _binding: FragmentAccompanimentMenuBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAccompanimentMenuBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            mainViewModel = viewModel
            accompanimentMenuFragment = this@AccompanimentMenuFragment
        }
    }

    fun goToNextScreen(){
        findNavController().navigate(R.id.action_accompanimentMenuFragment_to_checkoutFragment)
    }

    fun cancelOrder(){
        viewModel.resetOrder()
        findNavController().navigate(R.id.action_accompanimentMenuFragment_to_startFragment)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}