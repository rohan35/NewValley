package com.raydevelopers.newvalley.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.raydevelopers.newvalley.R
import com.raydevelopers.newvalley.databinding.AllChannelsFragmentBinding
import com.raydevelopers.newvalley.dependencyinjector.DependencyProvider
import com.raydevelopers.newvalley.network.NetworkUtils
import com.raydevelopers.newvalley.ui.adapters.AllChannelsRecyclerAdapter
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
        // make network request
        getViewModel().getAllChannels()
        getViewModel().mergedResponseLiveData.observe(viewLifecycleOwner, Observer {
            adapterInfo->
            if(adapterInfo != null)
            {
                mAdapter = AllChannelsRecyclerAdapter(adapterInfo)
                mBinding?.recyclerView?.adapter = mAdapter
                mBinding?.loader?.visibility = View.GONE
            }

        })
        
        mBinding?.pullToRefresh?.setOnRefreshListener {
            getViewModel().getAllChannels()
            pullToRefresh.isRefreshing = false
        }

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
