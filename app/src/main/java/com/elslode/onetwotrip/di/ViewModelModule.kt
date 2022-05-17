package com.elslode.onetwotrip.di

import androidx.lifecycle.ViewModel
import com.elslode.onetwotrip.ui.detailFragment.DetailViewModel
import com.elslode.onetwotrip.ui.fragmentChooseClassFly.ViewModelDialog
import com.elslode.onetwotrip.ui.fragmentTrips.ViewModelTrips
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ViewModelDialog::class)
    fun bindDialogViewModule(dialog: ViewModelDialog): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ViewModelTrips::class)
    fun bindTicketsViewModule(vmickets: ViewModelTrips): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    fun bindDetailViewModule(vmDetail: DetailViewModel): ViewModel
}