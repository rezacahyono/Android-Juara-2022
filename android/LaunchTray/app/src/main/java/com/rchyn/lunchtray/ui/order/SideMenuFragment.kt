package com.rchyn.lunchtray.ui.order

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.rchyn.lunchtray.R
import com.rchyn.lunchtray.databinding.FragmentSideMenuBinding
import com.rchyn.lunchtray.ui.order.viewmodel.OrderViewModel


class SideMenuFragment : Fragment() {

    private val viewModel: OrderViewModel by activityViewModels()

    private var _binding: FragmentSideMenuBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSideMenuBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            sideMenuFragment = this@SideMenuFragment
            mainViewModel = viewModel
        }
    }

    fun goToNextScreen(){
        findNavController().navigate(R.id.action_sideMenuFragment_to_accompanimentMenuFragment)
    }

    fun cancelOrder(){
        viewModel.resetOrder()
        findNavController().navigate(R.id.action_sideMenuFragment_to_startFragment)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}