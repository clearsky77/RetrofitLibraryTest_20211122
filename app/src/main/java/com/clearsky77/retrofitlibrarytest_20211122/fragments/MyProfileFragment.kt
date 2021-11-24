package com.clearsky77.retrofitlibrarytest_20211122.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.clearsky77.retrofitlibrarytest_20211122.R
import com.clearsky77.retrofitlibrarytest_20211122.databinding.FragmentReviewListBinding
import com.clearsky77.retrofitlibrarytest_20211122.utils.ContextUtil

class MyProfileFragment : Fragment(){

    lateinit var binding : FragmentReviewListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =  DataBindingUtil.inflate(inflater, R.layout.fragment_my_profile, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d("토큰값 확인", ContextUtil.getToken(requireContext()))
    }
}