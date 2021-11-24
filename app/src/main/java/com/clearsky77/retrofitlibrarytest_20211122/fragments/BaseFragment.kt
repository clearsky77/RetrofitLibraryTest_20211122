package com.clearsky77.retrofitlibrarytest_20211122.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.clearsky77.retrofitlibrarytest_20211122.api.ServerAPI
import com.clearsky77.retrofitlibrarytest_20211122.api.ServerAPIService

abstract class BaseFragment : Fragment() {

    lateinit var mContext: Context
    lateinit var apiService: ServerAPIService

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mContext = requireContext()

        val retrofit = ServerAPI.getRetrofit() // 레트로핏 줘
        apiService = retrofit.create(ServerAPIService::class.java)
    }

    abstract fun setupEvents()
    abstract fun setValues()
}