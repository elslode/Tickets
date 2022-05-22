package com.elslode.onetwotrip.di

import android.app.Application
import com.elslode.onetwotrip.presentation.detailFragment.DetailFragment
import com.elslode.onetwotrip.presentation.fragmentDialogChooseLevelTicket.TicketChooseDialogFragment
import com.elslode.onetwotrip.presentation.fragmentTickets.TripsFragment
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(
    modules = [
        DataModule::class,
        ViewModelModule::class
    ]
)

interface ApplicationComponent {

    fun inject(ticketDialogFragment: TicketChooseDialogFragment)
    fun inject(tripsFragment: TripsFragment)
    fun inject(detailFragment: DetailFragment)

    @Component.Factory
    interface Factory{
        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }
}
