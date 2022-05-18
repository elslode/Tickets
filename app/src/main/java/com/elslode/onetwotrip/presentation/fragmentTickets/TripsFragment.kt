package com.elslode.onetwotrip.presentation.fragmentTickets

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.elslode.onetwotrip.OneTripApp
import com.elslode.onetwotrip.R
import com.elslode.onetwotrip.databinding.FragmentTripsBinding
import com.elslode.onetwotrip.presentation.ViewModelFactory
import com.elslode.onetwotrip.presentation.adapter.TripAdapter
import com.elslode.onetwotrip.presentation.fragmentDialogChooseLevelTicket.TicketChooseDialogFragment
import javax.inject.Inject


class TripsFragment : Fragment() {

    private var _binding: FragmentTripsBinding? = null
    private val binding: FragmentTripsBinding
        get() = _binding ?: throw RuntimeException("FragmentTripsBinding is null")

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var mViewModel: ViewModelTrips

    private val component by lazy {
        (requireActivity().application as OneTripApp).component
    }

    private val adapter by lazy {
        TripAdapter()
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTripsBinding.inflate(inflater, container, false)
        mViewModel = ViewModelProvider(this, viewModelFactory)[ViewModelTrips::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerViewTrip.adapter = adapter
        adapter.onTripItemClickListener = { trip ->
            mViewModel.getTicketItem(trip.id)
            TicketChooseDialogFragment.newInstance(trip.id).show(parentFragmentManager, null)
        }

        mViewModel.listTrips.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        activity?.viewModelStore?.clear()
    }
}