package com.rchyn.lunchtray.ui.order

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.rchyn.lunchtray.R
import com.rchyn.lunchtray.databinding.FragmentStartBinding

class StartFragment : Fragment() {

    private var _binding: FragmentStartBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStartBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonStartOrder.setOnClickListener { goTextNextScreen() }
    }

    private fun goTextNextScreen() {
        findNavController().navigate(R.id.action_startFragment_to_entreeMenuFragment)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}