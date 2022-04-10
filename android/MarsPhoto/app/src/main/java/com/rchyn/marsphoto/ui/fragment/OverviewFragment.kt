package com.rchyn.marsphoto.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.rchyn.marsphoto.adapter.PhotoGridAdapter
import com.rchyn.marsphoto.databinding.FragmentOverviewBinding
import com.rchyn.marsphoto.databinding.GridViewItemBinding
import com.rchyn.marsphoto.ui.fragment.viewmodel.OverviewViewModel

class OverviewFragment : Fragment() {

    private val overviewViewModel: OverviewViewModel by viewModels()

    private var _binding: FragmentOverviewBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOverviewBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            recyclerMars.adapter = PhotoGridAdapter()
            viewModel = overviewViewModel
            lifecycleOwner = viewLifecycleOwner
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}