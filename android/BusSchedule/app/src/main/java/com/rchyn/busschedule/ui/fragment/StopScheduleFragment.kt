package com.rchyn.busschedule.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.coroutineScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.rchyn.busschedule.BusScheduleApplication
import com.rchyn.busschedule.databinding.FragmentStopScheduleBinding
import com.rchyn.busschedule.ui.adapter.BusStopAdapter
import com.rchyn.busschedule.viewmodel.BusScheduleViewModel
import com.rchyn.busschedule.viewmodel.BusScheduleViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class StopScheduleFragment : Fragment() {

    companion object {
        private const val STOP_NAME = "stopName"
    }
    private lateinit var stopName: String

    private val viewModel: BusScheduleViewModel by activityViewModels {
        BusScheduleViewModelFactory(
            (activity?.application as BusScheduleApplication).database.scheduleDao()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            stopName = it.getString(STOP_NAME).toString()
        }
    }

    private var _binding: FragmentStopScheduleBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStopScheduleBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerViewBus.layoutManager = LinearLayoutManager(requireContext())
        val busStopAdapter = BusStopAdapter {}
        binding.recyclerViewBus.adapter = busStopAdapter

        lifecycle.coroutineScope.launch(Dispatchers.IO) {
            viewModel.scheduleForStopName(stopName).collect {
                busStopAdapter.submitList(it)
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}