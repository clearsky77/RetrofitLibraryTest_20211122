package com.clearsky77.retrofitlibrarytest_20211122.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ServerAPI {
    // Retrofit 타입 객체를 하나만 생성하여 모두가 공유한다. 싱글톤.
    companion object{

//        private var BASE_URL = "http://3.34.159.73"
        private var BASE_URL = "https://keepthetime.xyz"
        private var retrofit: Retrofit? = null // 앱이 처음 켜질 때는 없다 => 한 번만 만들고 함수를 통해 공유

        fun getRetrofit() : Retrofit{
            if(retrofit==null){ // 만약 null이면 만들어 둘게요. 없을 때만 만들자.
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL) // 어느 서버에서?
                    .addConverterFactory(GsonConverterFactory.create()) // 자동으로 파싱할 것을 설정
                    .build() // 마무리 문구.
            }

            return retrofit!!
        }
    }

}