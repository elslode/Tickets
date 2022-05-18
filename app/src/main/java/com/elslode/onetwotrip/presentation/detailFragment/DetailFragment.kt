package com.elslode.onetwotrip.presentation.detailFragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.elslode.onetwotrip.OneTripApp
import com.elslode.onetwotrip.data.database.TicketEntityDbModel
import com.elslode.onetwotrip.databinding.FragmentDetailBinding
import com.elslode.onetwotrip.presentation.ViewModelFactory
import javax.inject.Inject

class DetailFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var mViewModel: DetailViewModel

    private var ticketLevel: String? = null
    private var ticketId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            ticketLevel = it.getString(ARG_CLASS)
            ticketId = it.getInt(ARG_TRIP)
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
        ticketLevel?.let { level ->
            ticketId?.let { id ->
                mViewModel.setDetailData(level, id)
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel.stateDetail.observe(viewLifecycleOwner) {

            when (it) {
                is CityFrom -> {
                    binding.cityFromHeadDetail.text = it.city
                }
                is CityTo -> {
                    binding.cityToHeadDetail.text = it.city
                }
                is NameOfAirportFour -> {
                    binding.airportToDetailsTwo.text = it.airportName
                }
                is NameOfAirportOne -> {
                    binding.airportFromDetails.text = it.airportName
                }
                is NameOfAirportThree -> {
                    binding.airportFromDetailsTwo.text = it.airportName
                }
                is NameOfAirportTwo -> {
                    binding.airportToDetails.text = it.airportName
                }
                is Price -> {
                    binding.priceFlight.text = it.price
                }
                is TicketType -> {
                    binding.classType.text = it.type
                }
                VisibilityCardBusiness -> {
                    binding.flightTwoCardView.isVisible = true
                    binding.arrowBetweenCards.isVisible = true
                }
                VisibilityCardEconomy -> {
                    binding.flightOneCardView.isVisible = true
                }
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(typeTicket: String, ticketId: Int?) =
            DetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_CLASS, typeTicket)
                    ticketId?.let { putInt(ARG_TRIP, it) }
                }
            }

        private const val ARG_CLASS = "ARG_CLASS"
        private const val ARG_TRIP = "TRIP_RESPONSE"
    }
}