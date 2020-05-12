package com.raydevelopers.newvalley.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.raydevelopers.newvalley.R
import com.raydevelopers.newvalley.databinding.AllChannelsFragmentBinding
import com.raydevelopers.newvalley.dependencyinjector.DependencyProvider
import com.raydevelopers.newvalley.viewmodels.AllChannelsViewModel

class AllChannelsFragment : Fragment() {
    private var mBinding:AllChannelsFragmentBinding? = null
    companion object {
        fun newInstance() =
            AllChannelsFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // init data binding
        mBinding =
            DataBindingUtil.inflate(inflater, R.layout.all_channels_fragment, container, false)
        // adding assertion as we have created object above and we are sure it will not be null
        return mBinding!!.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // make network request

    }

    /**
     * getViewModel() provides the object of viewmodel with the help [DependencyProvider]
     */
    private fun getViewModel(): AllChannelsViewModel {
        return ViewModelProvider(this, DependencyProvider.provideLoginViewModelFactory()).get(
            AllChannelsViewModel::class.java
        )
    }
}
