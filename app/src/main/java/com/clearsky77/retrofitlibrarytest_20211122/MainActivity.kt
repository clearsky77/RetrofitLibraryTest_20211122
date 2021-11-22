package com.clearsky77.retrofitlibrarytest_20211122

import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.clearsky77.retrofitlibrarytest_20211122.databinding.ActivityMainBinding
import com.clearsky77.retrofitlibrarytest_20211122.datas.BasicResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : BaseActivity() {

    lateinit var binding: ActivityMainBinding // xml에 <layout>달아야 찾아진다.

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {
        binding.btnLogin.setOnClickListener {
            val inputEmail = binding.edtEmail.text.toString()
            val inputPw = binding.edtPassword.text.toString()

            apiService.postRequestLogin(inputEmail, inputPw).enqueue(object : Callback<BasicResponse>{ //꼭 Callback<T> (Retrofit2)를 선택
                override fun onResponse(
                    call: Call<BasicResponse>,
                    response: Response<BasicResponse>
                ) {
                    val basicResponse = response.body()!!
                    Toast.makeText(mContext, basicResponse.message, Toast.LENGTH_SHORT).show()
                }

                override fun onFailure(call: Call<BasicResponse>, t: Throwable) {
                }

            })
        }
    }

    override fun setValues() {
    }
}