package com.clearsky77.retrofitlibrarytest_20211122.api

import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ServerAPIService {

//     로그인 기능
    @FormUrlEncoded// post, put, patch는 추가해줘야한다.
    @POST("/user")
    fun postRequestLogin(
        @Field("email") email: String,
        @Field("password") pw: String,
    )


}