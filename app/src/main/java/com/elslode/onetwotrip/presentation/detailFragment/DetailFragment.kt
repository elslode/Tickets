package com.elslode.onetwotrip.presentation.detailFragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.elslode.onetwotrip.OneTripApp
import com.elslode.onetwotrip.R
import com.elslode.onetwotrip.databinding.FragmentDetailBinding
import com.elslode.onetwotrip.domain.Ticket
import com.elslode.onetwotrip.domain.TypeOfTicket
import com.elslode.onetwotrip.presentation.*
import com.elslode.onetwotrip.presentation.Extension.airportDestination
import com.elslode.onetwotrip.presentation.Extension.codeDestination
import com.elslode.onetwotrip.presentation.State.*
import com.elslode.onetwotrip.presentation.adapter.StateResource
import java.lang.Error
import java.lang.NullPointerException
import javax.inject.Inject
import kotlin.properties.Delegates

class DetailFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var mViewModel: DetailViewModel

    private lateinit var ticketLevel: String
    private var ticketId by Delegates.notNull<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            ticketLevel = it.getString(ARG_TYPE_OF_TICKET).toString()
            ticketId = it.getInt(ARG_TICKET_ID)
        }
    }

    private val component by lazy {
        (requireActivity().application as OneTripApp).component
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    private var _binding: FragmentDetailBinding? = null
    private val binding: FragmentDetailBinding
        get() = _binding ?: throw RuntimeException("FragmentDetailBinding is null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        mViewModel = ViewModelProvider(this, viewModelFactory)[DetailViewModel::class.java]

        if (ticketLevel.isBlank()) {
            throw NullPointerException(R.string.nullpointexception.getString(requireContext()))
        } else {
            ticketLevel.let { level ->
                ticketId.let { id ->
                    mViewModel.setDetailData(level, id)
                }
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel.stateDetail.observe(viewLifecycleOwner) {
            binding.progressBarDetail.isVisible = false
            when (it.status) {
                LOADING -> {
                    binding.flightOneCardView.isVisible = false
                    binding.flightTwoCardView.isVisible = false
                    binding.progressBarDetail.isVisible = true
                }
                ERROR -> {
                    binding.progressBarDetail.isVisible = false
                    Toast.makeText(
                        this.requireContext(),
                        R.string.error.getString(this.requireContext()),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                SUCCESS -> {
                    binding.progressBarDetail.isVisible = false
                    it.data?.apply {
                        this.prices.map { price ->
                            if (price.type == ticketLevel) {
                                binding.priceFlight.text = price.amount.toString()
                            }
                        }

                        this.trips.map { trip ->
                            binding.cityToHeadDetail.text =
                                trip.to.codeDestination(requireActivity().application)
                            binding.cityFromHeadDetail.text =
                                trip.from.codeDestination(requireActivity().application)
                        }

                        binding.airportFromDetails.text =
                            this.trips.first().from.airportDestination(requireActivity().application)
                        binding.airportToDetails.text =
                            this.trips.first().to.airportDestination(requireActivity().application)
                        binding.airportFromDetailsTwo.text =
                            this.trips.last().from.airportDestination(requireActivity().application)
                        binding.airportToDetailsTwo.text =
                            this.trips.last().to.airportDestination(requireActivity().application)

                        binding.flightOneCardView.isVisible = true
                        binding.flightTwoCardView.isVisible = this.trips.size >= 2
                    }

                    binding.classType.text = ticketLevel
                    binding.priceFlight.text
                }
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(typeTicket: String, ticketId: Int?) =
            DetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_TYPE_OF_TICKET, typeTicket)
                    ticketId?.let { putInt(ARG_TICKET_ID, it) }
                }
            }

        private const val ARG_TYPE_OF_TICKET = "ARG_CLASS"
        private const val ARG_TICKET_ID = "TRIP_RESPONSE"
    }
}