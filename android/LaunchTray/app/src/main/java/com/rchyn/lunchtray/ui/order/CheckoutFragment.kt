package com.rchyn.lunchtray.ui.order

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.rchyn.lunchtray.R
import com.rchyn.lunchtray.databinding.FragmentCheckoutBinding
import com.rchyn.lunchtray.ui.order.viewmodel.OrderViewModel

class CheckoutFragment : Fragment() {

    private val viewModel: OrderViewModel by activityViewModels()

    private var _binding: FragmentCheckoutBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCheckoutBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            mainViewModel = viewModel
            lifecycleOwner = viewLifecycleOwner
            checkoutFragment = this@CheckoutFragment
        }
    }

    fun sendOrder(){
        Snackbar.make(
            binding.root,
            getString(R.string.send_order),
            Snackbar.LENGTH_SHORT
        ).show()
        findNavController().navigate(R.id.action_checkoutFragment_to_startFragment)
    }

    fun cancelOrder(){
        viewModel.resetOrder()
        findNavController().navigate(R.id.action_checkoutFragment_to_startFragment)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}