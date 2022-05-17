package com.elslode.onetwotrip.ui.detailFragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.elslode.onetwotrip.OneTripApp
import com.elslode.onetwotrip.databinding.FragmentDetailBinding
import com.elslode.onetwotrip.domain.TripResponse
import com.elslode.onetwotrip.ui.ViewModelFactory
import com.elslode.onetwotrip.ui.fragmentTrips.ViewModelTrips
import javax.inject.Inject

class DetailFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var mViewModel: DetailViewModel

    private var classFly: String? = null
    private var trip: TripResponse? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            classFly = it.getString(ARG_CLASS)
            trip = it.getParcelable(ARG_TRIP)
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

        trip?.let { trip ->
            classFly?.let { flyType ->
                mViewModel.setClassFly(flyType, trip)
            }
        }

        mViewModel.classType.observe(viewLifecycleOwner) {
            binding.classType.text = it
        }
        mViewModel.airportOne.observe(viewLifecycleOwner){
            binding.airportFromDetails.text = it
        }
        mViewModel.airportTwo.observe(viewLifecycleOwner){
            binding.airportToDetails.text = it
        }
        mViewModel.airportThree.observe(viewLifecycleOwner){
            binding.airportFromDetailsTwo.text = it
        }
        mViewModel.airportFour.observe(viewLifecycleOwner){
            binding.airportToDetailsTwo.text = it
        }
        mViewModel.price.observe(viewLifecycleOwner){
            binding.priceFlight.text = it
        }
        mViewModel.cityFrom.observe(viewLifecycleOwner){
            binding.cityFromHeadDetail.text = it
        }
        mViewModel.cityTo.observe(viewLifecycleOwner){
            binding.cityToHeadDetail.text = it
        }
        mViewModel.cardVisibilityTwo.observe(viewLifecycleOwner){
            binding.flightTwoCardView.isVisible = it
            binding.arrowBetweenCards.isVisible = it
        }
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(classFly: String, trip: TripResponse?) =
            DetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_CLASS, classFly)
                    putParcelable(ARG_TRIP, trip)
                }
            }

        private const val ARG_CLASS = "ARG_CLASS"
        private const val ARG_TRIP = "TRIP_RESPONSE"
    }
}