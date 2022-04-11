package com.rchyn.amphibians.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.rchyn.amphibians.R
import com.rchyn.amphibians.adapter.AmphibianListAdapter
import com.rchyn.amphibians.adapter.AmphibianListener
import com.rchyn.amphibians.databinding.FragmentAmphibiansListBinding
import com.rchyn.amphibians.ui.fragment.viewmodel.AmphibianViewModel


class AmphibiansListFragment : Fragment() {

    private val viewModel: AmphibianViewModel by activityViewModels()

    private var _binding: FragmentAmphibiansListBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAmphibiansListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            amphibianViewModel = viewModel
            recyclerAmphibian.adapter = AmphibianListAdapter(AmphibianListener { amphibian ->
                viewModel.onAmphibianClicked(amphibian)
                findNavController().navigate(R.id.action_amphibiansListFragment_to_amphibiansDetailFragment)
            })
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}