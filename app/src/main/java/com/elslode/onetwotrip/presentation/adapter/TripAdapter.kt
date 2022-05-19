package com.elslode.onetwotrip.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.elslode.onetwotrip.databinding.ItemTripBinding
import com.elslode.onetwotrip.domain.Ticket
import com.elslode.onetwotrip.presentation.Extension.airportDestination
import com.elslode.onetwotrip.presentation.Extension.currencyType
import com.elslode.onetwotrip.presentation.Extension.price
import com.elslode.onetwotrip.presentation.Extension.transfer

class TripAdapter: ListAdapter<Ticket, TripViewHolder>(TripDiffCallback) {

    var onTripItemClickListener: ((Ticket) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TripViewHolder {
        val binding = ItemTripBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return TripViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TripViewHolder, position: Int) {
        val trip = getItem(position)
        val binding = holder.binding
        with(holder.binding) {
            with(trip) {
                minPriceForFlightTV.text = this.prices.price()
                currencyValue.text= this.currency.currencyType()
                transferTv.text = this.trips.transfer()
                fromDestination.text = this.trips.first().from.airportDestination()
                toDestination.text = this.trips.last().to.airportDestination()
            }
        }
        binding.root.setOnClickListener {
            onTripItemClickListener?.invoke(trip)
        }
    }
}