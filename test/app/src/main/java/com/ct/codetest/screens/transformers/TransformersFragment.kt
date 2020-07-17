package com.ct.codetest.screens.transformers

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.ct.codetest.R
import com.ct.codetest.models.transformers.AllTransformers
import com.ct.codetest.models.transformers.Transformer
import com.ct.codetest.platform.BaseFragment
import com.ct.codetest.platform.LiveDataWrapper
import com.ct.codetest.viewmodels.TransformersViewModel
import kotlinx.android.synthetic.main.fragment_transformers.*

class TransformersFragment : BaseFragment() {
    


    lateinit var mRecyclerViewAdapter: TransformersAdapter

    private val mViewModel: TransformersViewModel by lazy {
        ViewModelProviders.of(this, mBaseViewModelFactory).get(TransformersViewModel::class.java)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        this.mViewModel.mTransformersResponse.observe(viewLifecycleOwner, this.mDataObserver)
        this.mViewModel.mLoadingLiveData.observe(viewLifecycleOwner, this.loadingObserver)

        mRecyclerViewAdapter =
            TransformersAdapter(
                requireActivity(),
                arrayListOf()
            )

        transformersListRecyclerView.adapter = mRecyclerViewAdapter
        transformersListRecyclerView.layoutManager = LinearLayoutManager(requireActivity())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        this.mViewModel.requestTransformersData()
        super.onViewCreated(view, savedInstanceState)
    }

    private val mDataObserver = Observer<LiveDataWrapper<AllTransformers>> { result ->
        when (result?.responseStatus) {
            LiveDataWrapper.ResponseStatus.LOADING -> {
                // Loading data
            }
            LiveDataWrapper.ResponseStatus.ERROR -> {
                // Error for http request
                logD("", "LiveDataResult.Status.ERROR = ${result.response}")
                error_holder.visibility = View.VISIBLE
                showToast("Error in getting data")

            }
            LiveDataWrapper.ResponseStatus.SUCCESS -> {
                logD("", "LiveDataResult.Status.SUCCESS = ${result.response}")
                val allTransformers = result.response as AllTransformers
                val listItems = allTransformers.transformers as ArrayList<Transformer>
                if (listItems.isEmpty()) addFirst.visibility = View.VISIBLE
                else addFirst.visibility = View.GONE
                processData(listItems)
            }
        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_transformers

    private fun processData(listItems: ArrayList<Transformer>) {
        logD("", "processData called with data ${listItems.size}")
        logD("", "processData listItems =  $listItems")

        mRecyclerViewAdapter.updateListItems(listItems)

    }

    private val loadingObserver = Observer<Boolean> { visible ->
        if (visible) {
            progress_circular.visibility = View.VISIBLE
        } else {
            progress_circular.visibility = View.INVISIBLE
        }
    }

}