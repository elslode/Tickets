package com.elslode.onetwotrip.presentation.fragmentTickets

import android.app.Application
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.elslode.onetwotrip.OneTripApp
import com.elslode.onetwotrip.databinding.FragmentTripsBinding
import com.elslode.onetwotrip.presentation.State
import com.elslode.onetwotrip.presentation.ViewModelFactory
import com.elslode.onetwotrip.presentation.adapter.StateResource
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
        TripAdapter(activity?.applicationContext as Application)
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
            mViewModel.openTicketItem(trip.id)
        }

        mViewModel.ticketsList.observe(viewLifecycleOwner) {
            when (it.status) {
                State.LOADING -> {
                    binding.progressTickets.isVisible = true
                    binding.recyclerViewTrip.isVisible = false
                }
                State.ERROR -> {
                    binding.progressTickets.isVisible = false
                    binding.recyclerViewTrip.isVisible = false
                    Toast.makeText(requireContext(), "${it.message}", Toast.LENGTH_SHORT)
                        .show()
                }
                State.SUCCESS -> {
                    binding.recyclerViewTrip.isVisible = false
                    binding.recyclerViewTrip.isVisible = true
                    adapter.submitList(it.data)
                    binding.progressTickets.isVisible = false
                }
            }
        }

        mViewModel.tripItem.observe(viewLifecycleOwner) {
            TicketChooseDialogFragment.newInstance(it.id).show(parentFragmentManager, null)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        activity?.viewModelStore?.clear()
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            TripsFragment().apply {
                arguments = Bundle().apply {}
            }
    }
}