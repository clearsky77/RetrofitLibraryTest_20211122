package com.clearsky77.retrofitlibrarytest_20211122.api

import com.clearsky77.retrofitlibrarytest_20211122.datas.BasicResponse
import retrofit2.Call
import retrofit2.http.*

interface ServerAPIService { // 왜 인터페이스? Retrofit에 create메소드가 그렇게 받기 때문에

    //     로그인 기능
    @FormUrlEncoded// post, put, patch는 추가해줘야한다.
    @POST("/user")
    fun postRequestLogin(
        @Field("email") email: String,
        @Field("password") pw: String,
    ): Call<BasicResponse> // 호출 답으로 뭘 받을지 명시

    //    회원가입 기능
    @FormUrlEncoded// post, put, patch는 추가해줘야한다.
    @PUT("/user")
    fun putRequestSignUp(
        @Field("email") email: String, // form data
        @Field("password") pw : String,
        @Field("nick_name") nick: String,
    ) : Call<BasicResponse>

    //    중복 확인 기능 - GET
    @GET("/user/check")
    fun getRequestDuplicatedCheck(
        @Query("type") type: String,
        @Query("value") value: String,
    ) : Call<BasicResponse>



}