package com.clearsky77.retrofitlibrarytest_20211122

import android.app.Application
import com.kakao.sdk.common.KakaoSdk

class GlobalApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // 다른 초기화 코드들

        // Kakao SDK 초기화
        KakaoSdk.init(this, "7da41ee361e74f415bd5024c35dd64b8")
    }
}