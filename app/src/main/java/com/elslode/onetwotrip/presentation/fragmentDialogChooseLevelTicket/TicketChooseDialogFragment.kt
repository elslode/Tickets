package com.elslode.onetwotrip.presentation.fragmentDialogChooseLevelTicket

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
import com.elslode.onetwotrip.domain.TypeOfTicket
import com.elslode.onetwotrip.presentation.ViewModelFactory
import com.elslode.onetwotrip.presentation.detailFragment.DetailFragment
import javax.inject.Inject

class TicketChooseDialogFragment : DialogFragment() {

    private var _binding: FragmentDialogChooseTripBinding? = null
    private val binding: FragmentDialogChooseTripBinding
        get() = _binding ?: throw RuntimeException("FragmentDialogChooseTripBinding is null")

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var mViewModel: ViewModelDialog

    private val component by lazy {
        (requireActivity().application as OneTripApp).component
    }

    private var ticketId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            ticketId = it.getInt(TICKET_KEY)
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
        mViewModel.getItem(ticketId)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel.stateDialog.observe(viewLifecycleOwner) {

            when (it) {
                is VisibilityBoxBusiness -> {
                    binding.checkBoxBusiness.isVisible = true
                    binding.priceBusiness.isVisible = true
                }
                is VisibilityBoxEconomy -> {
                    binding.checkBoxEconomy.isVisible = true
                    binding.priceEconomy.isVisible = true
                }
                is VisibilityButtonEnabled -> {
                    binding.enableChooseTypeTicketButton.isVisible = true
                }
                is PriceBusiness -> {
                    binding.priceBusiness.text = it.price
                }
                is PriceEconomy -> {
                    binding.priceEconomy.text = it.price
                }
            }
        }
    }

    private fun fragmentTransaction(fragment: Fragment) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(this.toString())
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
            if (binding.checkBoxBusiness.isChecked) {
                fragmentTransaction(DetailFragment.newInstance(TypeOfTicket.BUSSINESS.ticketType, ticketId))
            }
            if (binding.checkBoxEconomy.isChecked) {
                fragmentTransaction(DetailFragment.newInstance(TypeOfTicket.ECONOM.ticketType, ticketId))
            }
            dialog?.cancel()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(idTicket: Int) =
            TicketChooseDialogFragment().apply {
                arguments = Bundle().apply {
                    putInt(TICKET_KEY, idTicket)
                }
            }

        private const val TICKET_KEY = "ticketId"
    }


}