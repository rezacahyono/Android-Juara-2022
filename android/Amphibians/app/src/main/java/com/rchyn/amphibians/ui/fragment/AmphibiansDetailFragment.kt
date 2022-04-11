package com.rchyn.amphibians.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.rchyn.amphibians.databinding.FragmentAmphibiansDetailBinding
import com.rchyn.amphibians.ui.fragment.viewmodel.AmphibianViewModel

class AmphibiansDetailFragment : Fragment() {

    private val viewModel: AmphibianViewModel by activityViewModels()

    private var _binding: FragmentAmphibiansDetailBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAmphibiansDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            amphibianViewModel = viewModel
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}