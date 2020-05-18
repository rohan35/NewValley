package com.raydevelopers.newvalley.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.raydevelopers.newvalley.MindValleyApplication
import com.raydevelopers.newvalley.R
import com.raydevelopers.newvalley.databinding.AllChannelsFragmentBinding
import com.raydevelopers.newvalley.dependencyinjector.DependencyProvider
import com.raydevelopers.newvalley.network.NetworkUtils
import com.raydevelopers.newvalley.ui.adapters.AllChannelsRecyclerAdapter
import com.raydevelopers.newvalley.utility.ERROR_OFFLINE
import com.raydevelopers.newvalley.viewmodels.AllChannelsViewModel
import kotlinx.android.synthetic.main.all_channels_fragment.*

class AllChannelsFragment : Fragment() {
    private var mBinding:AllChannelsFragmentBinding? = null
    private var mAdapter:AllChannelsRecyclerAdapter? = null
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
        mBinding?.loader?.visibility = View.VISIBLE
        // check internet
        // check internet
        if(!NetworkUtils.verifyAvailableNetwork(MindValleyApplication.applicationContext()))
        {
            getViewModel().errorLiveData.value = ERROR_OFFLINE
        }
        // make network request
        getViewModel().getAllChannels()
        observeError()
        getViewModel().mergedResponseLiveData.observe(viewLifecycleOwner, Observer {
            adapterInfo->
            if(adapterInfo != null)
            {
                mAdapter = AllChannelsRecyclerAdapter(adapterInfo)
                mBinding?.recyclerView?.adapter = mAdapter
                mBinding?.recyclerView?.setHasFixedSize(true)
                mBinding?.loader?.visibility = View.GONE
            }

        })
        
        mBinding?.pullToRefresh?.setOnRefreshListener {
            if(!NetworkUtils.verifyAvailableNetwork(MindValleyApplication.applicationContext()))
            {
                getViewModel().errorLiveData.value = ERROR_OFFLINE
            }
            getViewModel().getAllChannels()
            pullToRefresh.isRefreshing = false
        }

    }

    private fun observeError() {
        getViewModel().errorLiveData.observe(viewLifecycleOwner, Observer { message ->
            if (!message.isNullOrBlank()) {
                Snackbar.make(
                    mBinding!!.layoutRoot,
                    message,
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        })
    }

    /**
     * getViewModel() provides the object of viewModel with the help [DependencyProvider]
     */
    private fun getViewModel(): AllChannelsViewModel {
        return ViewModelProvider(this, DependencyProvider.provideLoginViewModelFactory()).get(
            AllChannelsViewModel::class.java
        )
    }
}
