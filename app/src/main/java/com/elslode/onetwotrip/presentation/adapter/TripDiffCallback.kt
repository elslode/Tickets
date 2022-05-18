package com.elslode.onetwotrip.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.elslode.onetwotrip.domain.Ticket

object TripDiffCallback: DiffUtil.ItemCallback<Ticket>()  {

    override fun areItemsTheSame(oldItem: Ticket, newItem: Ticket): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Ticket, newItem: Ticket): Boolean {
        return oldItem == newItem
    }
}