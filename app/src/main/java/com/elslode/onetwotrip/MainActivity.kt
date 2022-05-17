package com.elslode.onetwotrip

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.elslode.onetwotrip.databinding.ActivityMainBinding
import com.elslode.onetwotrip.ui.start.StartFragment

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding
        get() = _binding ?: throw RuntimeException("ActivityMainBinding == null")

    private val component by lazy {
        (application as OneTripApp).component
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, StartFragment()).commit()
    }
}