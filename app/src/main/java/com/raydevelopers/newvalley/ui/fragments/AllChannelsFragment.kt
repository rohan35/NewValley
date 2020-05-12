package com.raydevelopers.newvalley.ui.fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.raydevelopers.newvalley.R
import com.raydevelopers.newvalley.viewmodels.AllChannelsViewModel

class AllChannelsFragment : Fragment() {

    companion object {
        fun newInstance() =
            AllChannelsFragment()
    }

    private lateinit var viewModel: AllChannelsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(AllChannelsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
