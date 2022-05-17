package com.elslode.onetwotrip.ui.fragmentChooseClassFly

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.core.view.isVisible
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.elslode.onetwotrip.OneTripApp
import com.elslode.onetwotrip.R
import com.elslode.onetwotrip.databinding.FragmentDialogChooseTripBinding
import com.elslode.onetwotrip.domain.TripResponse
import com.elslode.onetwotrip.ui.ViewModelFactory
import com.elslode.onetwotrip.ui.detailFragment.DetailFragment
import com.elslode.onetwotrip.ui.detailFragment.DetailViewModel
import com.elslode.onetwotrip.utils.Constant.BUSSINESS
import com.elslode.onetwotrip.utils.Constant.ECONOM
import javax.inject.Inject

class TripChooseDialogFragment : DialogFragment() {

    private var _binding: FragmentDialogChooseTripBinding? = null
    private val binding: FragmentDialogChooseTripBinding
        get() = _binding ?: throw RuntimeException("FragmentDialogChooseTripBinding is null")

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var mViewModel: ViewModelDialog

    private val component by lazy {
        (requireActivity().application as OneTripApp).component
    }

    private var trip: TripResponse? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            trip = it.getParcelable(TRIP_KEY)
        }
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDialogChooseTripBinding.inflate(inflater, container, false)

        val window: Window? = dialog?.window
        window?.setBackgroundDrawableResource(android.R.color.transparent)

        mViewModel = ViewModelProvider(this, viewModelFactory)[ViewModelDialog::class.java]
        mViewModel.tripAvailablePrice(trip?.prices)

        mViewModel.checkBoxBussinessVisible.observe(viewLifecycleOwner) {
            binding.checkBoxBusiness.isVisible = it
            binding.priceBusiness.isVisible = it
        }

        mViewModel.checkBoxEconomyVisible.observe(viewLifecycleOwner) {
            binding.checkBoxEconomy.isVisible = it
            binding.priceEconomy.isVisible = it
        }

        mViewModel.buttonEnabledVisible.observe(viewLifecycleOwner) {
            binding.enableChooseTypeTicketButton.isVisible = it
        }

        mViewModel.priceBussiness.observe(viewLifecycleOwner) {
            binding.priceBusiness.text = it
        }

        mViewModel.priceEconomy.observe(viewLifecycleOwner) {
            binding.priceEconomy.text = it
        }
        return binding.root
    }

    private fun fragmentTransaction(fragment: Fragment) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onResume() {
        super.onResume()
        binding.checkBoxEconomy.setOnClickListener {
            if (binding.checkBoxEconomy.isChecked) {
                binding.checkBoxBusiness.isChecked = false
            }
        }
        binding.checkBoxBusiness.setOnClickListener {
            if (binding.checkBoxBusiness.isChecked) {
                binding.checkBoxEconomy.isChecked = false
            }
        }

        binding.enableChooseTypeTicketButton.setOnClickListener {
            binding.apply {
                when (true) {
                    checkBoxBusiness.isChecked -> {
                        fragmentTransaction(DetailFragment.newInstance(BUSSINESS, trip))
                        dialog?.hide()
                    }
                    checkBoxEconomy.isChecked -> {
                        fragmentTransaction(DetailFragment.newInstance(ECONOM, trip))
                        dialog?.dismiss()
                    }
                }
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(trip: TripResponse) =
            TripChooseDialogFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(TRIP_KEY, trip)
                }
            }

        private const val TRIP_KEY = "TRIP"
    }


}