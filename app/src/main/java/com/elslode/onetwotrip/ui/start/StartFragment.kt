package com.elslode.onetwotrip.ui.start

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.elslode.onetwotrip.R
import com.elslode.onetwotrip.databinding.FragmentStartBinding
import com.elslode.onetwotrip.ui.fragmentTrips.TripsFragment


class StartFragment : Fragment() {

    private var _binding: FragmentStartBinding? = null
    private val binding: FragmentStartBinding
        get() = _binding ?: throw RuntimeException("FragmentStartBinding is null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       _binding = FragmentStartBinding.inflate(inflater, container, false)
        binding.nextButton.setOnClickListener {
            requireActivity().supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, TripsFragment())
                .addToBackStack(null)
                .commit()
        }
        return (binding.root)
    }
}