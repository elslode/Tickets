package com.elslode.onetwotrip.di

import android.app.Application
import com.elslode.onetwotrip.MainActivity
import com.elslode.onetwotrip.ui.detailFragment.DetailFragment
import com.elslode.onetwotrip.ui.fragmentChooseClassFly.TripChooseDialogFragment
import com.elslode.onetwotrip.ui.fragmentTrips.TripsFragment
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

    fun inject(mainActivity: MainActivity)
    fun inject(tripDialogFragment: TripChooseDialogFragment)
    fun inject(tripsFragment: TripsFragment)
    fun inject(detailFragment: DetailFragment)

    @Component.Factory
    interface Factory{
        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }
}