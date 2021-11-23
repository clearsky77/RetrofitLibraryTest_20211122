package com.clearsky77.retrofitlibrarytest_20211122

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.clearsky77.retrofitlibrarytest_20211122.databinding.ActivityLoginBinding
import com.clearsky77.retrofitlibrarytest_20211122.datas.BasicResponse
import com.kakao.sdk.user.UserApi
import com.kakao.sdk.user.UserApiClient
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.security.MessageDigest

class LoginActivity : BaseActivity() {

    lateinit var binding: ActivityLoginBinding // xml에 <layout>달아야 찾아진다.

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

        binding.btnSignUp.setOnClickListener {
            val myIntent = Intent(mContext, SignUpActivity::class.java)
            startActivity(myIntent)
        }

        binding.btnLogin.setOnClickListener {
            val inputEmail = binding.edtEmail.text.toString()
            val inputPw = binding.edtPassword.text.toString()

            apiService.postRequestLogin(inputEmail, inputPw).enqueue(object : Callback<BasicResponse>{ //꼭 Callback<T> (Retrofit2)를 선택
                override fun onResponse(
                    call: Call<BasicResponse>,
                    response: Response<BasicResponse>
                ) {
                    if (response.isSuccessful) {
                        val basicResponse =  response.body()!!

//                        Toast.makeText(mContext, basicResponse.message, Toast.LENGTH_SHORT).show()

                        val userNickname = basicResponse.data.user.nickname
                        Toast.makeText(mContext, "${userNickname}님 환영합니다!", Toast.LENGTH_SHORT).show()

                    }
                    else {

                        val errorJson = JSONObject( response.errorBody()!!.string() )
                        Log.d("에러경우", errorJson.toString())

                        val message = errorJson.getString("message")

                        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()

                    }
                }

                override fun onFailure(call: Call<BasicResponse>, t: Throwable) {
                }

            })
        }

//        카카오 로그인
        binding.btnKakaoLogin.setOnClickListener {
            if(UserApiClient.instance.isKakaoTalkLoginAvailable(mContext)){
                UserApiClient.instance.loginWithKakaoTalk(mContext){ token, error ->
                    if(error!=null){
                        Log.e("카카오 로그인","실패-loginWithKakaoTalk")
                    }else if(token!=null){
                        Log.e("카카오 로그인","")
                        Log.e("카카오 로그인",token.accessToken)
                        getMyInfoFromKakao()
                    }

                }
            }else{
                UserApiClient.instance.loginWithKakaoAccount(mContext){token, error ->
                    if(error!=null){
                        Log.e("카카오 로그인","실패-loginWithKakaoAccount")
                    }else if(token!=null){
                        Log.e("카카오 로그인","성공")
                        Log.e("카카오 로그인",token.accessToken)
                        getMyInfoFromKakao()
                    }
                }
            }
        }
    }

    override fun setValues() {
        UserApiClient.instance.logout {  }
        getKeyHash()
    }

    fun getKeyHash() {
        val info = packageManager.getPackageInfo(
            "com.clearsky77.retrofitlibrarytest_20211122",
            PackageManager.GET_SIGNATURES
        )
        for (signature in info.signatures) {
            val md: MessageDigest = MessageDigest.getInstance("SHA")
            md.update(signature.toByteArray())
            Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT))
        }
    }

    fun getMyInfoFromKakao(  ) {

        UserApiClient.instance.me { user, error ->

            if (error != null) {
                Log.e("카톡로그인", "사용자 정보 요청 실패", error)
            }
            else if (user != null) {
                Log.i("카톡로그인", "사용자 정보 요청 성공" +
                        "\n회원번호: ${user.id}" +
                        "\n이메일: ${user.kakaoAccount?.email}" +
                        "\n닉네임: ${user.kakaoAccount?.profile?.nickname}")
                apiService.postRequestSocialLogin(
                    "kakao",
                    user.id.toString(),
                    user.kakaoAccount?.profile?.nickname!!).enqueue(object : Callback<BasicResponse> {
                    override fun onResponse(
                        call: Call<BasicResponse>,
                        response: Response<BasicResponse>
                    ) {
                        if (response.isSuccessful) {
                            val br = response.body()!!
                            Toast.makeText(mContext, "${br.data.user.nickname}님, 환영합니다!", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<BasicResponse>, t: Throwable) {
                    }
                } )
            }

        }

    }
}