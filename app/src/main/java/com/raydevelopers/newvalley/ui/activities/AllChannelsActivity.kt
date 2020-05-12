package com.raydevelopers.newvalley.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.raydevelopers.newvalley.R
import com.raydevelopers.newvalley.ui.fragments.AllChannelsFragment

class AllChannelsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.all_channels_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, AllChannelsFragment.newInstance())
                    .commitNow()
        }
    }
}
