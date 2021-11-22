package com.clearsky77.retrofitlibrarytest_20211122

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity(){ // 상속하기 위해서 abstract을 붙인다. 코틀린 모든 클래스는 기본 final이기 때문
    lateinit var mContext: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this
    }

    abstract fun setupEvents()
    abstract fun setValues()
}