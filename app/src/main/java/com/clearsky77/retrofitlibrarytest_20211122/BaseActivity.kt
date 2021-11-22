package com.clearsky77.retrofitlibrarytest_20211122

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.clearsky77.retrofitlibrarytest_20211122.api.ServerAPI
import com.clearsky77.retrofitlibrarytest_20211122.api.ServerAPIService
import retrofit2.create

abstract class BaseActivity : AppCompatActivity(){ // 상속하기 위해서 abstract을 붙인다. 코틀린 모든 클래스는 기본 final이기 때문

    lateinit var mContext: Context
    lateinit var apiService: ServerAPIService // BaseActivity를 상속 받는 모든 Activity에서 서버접속 바로 사용 가능.

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this

        val retrofit = ServerAPI.getRetrofit()
        apiService = retrofit.create(ServerAPIService::class.java)
    }

    abstract fun setupEvents()
    abstract fun setValues()
}