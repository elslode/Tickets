package com.elslode.onetwotrip.ui.fragmentTrips

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.elslode.onetwotrip.OneTripApp
import com.elslode.onetwotrip.databinding.FragmentTripsBinding
import com.elslode.onetwotrip.ui.ViewModelFactory
import com.elslode.onetwotrip.ui.adapter.TripAdapter
import com.elslode.onetwotrip.ui.fragmentChooseClassFly.TripChooseDialogFragment
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
        binding.recyclerViewTrip.adapter = adapter
        adapter.onTripItemClickListener = { trip ->
            mViewModel.getTripItem(trip.id)
            TripChooseDialogFragment.newInstance(trip).show(
                parentFragmentManager,
                "DialogTripDetail"
            )
        }

        mViewModel.listTrips.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        return binding.root
    }

}