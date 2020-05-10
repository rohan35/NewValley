package com.raydevelopers.newvalley.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.raydevelopers.newvalley.R
import com.raydevelopers.newvalley.ui.fragments.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow()
        }
    }
}
