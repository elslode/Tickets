package com.elslode.airtickets.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.elslode.onetwotrip.domain.TripResponse

object TripDiffCallback: DiffUtil.ItemCallback<TripResponse>()  {

    override fun areItemsTheSame(oldItem: TripResponse, newItem: TripResponse): Boolean {
        return oldItem.currency == newItem.currency
    }

    override fun areContentsTheSame(oldItem: TripResponse, newItem: TripResponse): Boolean {
        return oldItem == newItem
    }
}