package com.elslode.onetwotrip

import android.app.Application
import com.elslode.onetwotrip.di.DaggerApplicationComponent

class OneTripApp: Application() {

    val component by lazy {
       DaggerApplicationComponent.factory().create(this)
    }
}