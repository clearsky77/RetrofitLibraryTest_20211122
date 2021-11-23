package com.clearsky77.retrofitlibrarytest_20211122

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import com.clearsky77.retrofitlibrarytest_20211122.databinding.ActivitySignUpBinding
import com.clearsky77.retrofitlibrarytest_20211122.datas.BasicResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : BaseActivity() {

    lateinit var binding : ActivitySignUpBinding
    var isDuplOk = false
    var isPasswordLenthOk = false
    var isPasswordSame = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

//        1. 비밀번호 타이핑 이벤트 => 8글자 이상인지 아닌
        binding.edtPassword.addTextChangedListener {
            if(it.toString().length>=8) {
                binding.txtPasswordCheckResult1.text = "사용해도 좋은 비밀번호입니다."
            }else{
                binding.txtPasswordCheckResult1.text = "8자 이상으로 해주세요"
            }
            isPasswordSame = compareTwoPasswords()
        }

        binding.edtPasswordRepeat.addTextChangedListener {
            isPasswordSame = compareTwoPasswords()
        }


        binding.edtEmail.addTextChangedListener {
//            Log.d("입력된 내용", it.toString())
            // 재검사 요청
            binding.txtEmailCheckResult.text = "이메일 중복검사를 해주세요."
            isDuplOk = false
        }

//        이메일 중복확인 버튼
        binding.btnEmailCheck.setOnClickListener {

            val email = binding.edtEmail.text.toString()

            apiService.getRequestDuplicatedCheck("EMAIL", email).enqueue( object : Callback<BasicResponse> {
                override fun onResponse(
                    call: Call<BasicResponse>,
                    response: Response<BasicResponse>
                ) {
                    if(response.isSuccessful) {
                        binding.txtEmailCheckResult.text = "사용해도 좋은 이메일입니다."
                        isDuplOk = true
                    }else{
                        binding.txtEmailCheckResult.text = "다른 이메일을 사용해주세요."
                        isDuplOk = false
                    }
                }

                override fun onFailure(call: Call<BasicResponse>, t: Throwable) {
                }

            } )

        }

//        회원가입 버튼
        binding.btnSignUp.setOnClickListener {

            if(!isDuplOk){
                Toast.makeText(mContext, "이메일 중복검사를 해주세요", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if(!isPasswordLenthOk){
                Toast.makeText(mContext, "비밀번호는 8글자 이상이어야합니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if(!isPasswordSame){
                Toast.makeText(mContext, "두 개의 비밀번호는 같아야합니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val email = binding.edtEmail.text.toString()
            val password = binding.edtPassword.text.toString()
            val nickname = binding.edtNickname.text.toString()

            apiService.putRequestSignUp(email, password, nickname).enqueue(object : Callback<BasicResponse>{
                override fun onResponse(
                    call: Call<BasicResponse>,
                    response: Response<BasicResponse>
                ) {
                    if(response.isSuccessful){
                        val br = response.body()!!
                        Log.d("가입한 사람의 토큰",br.data.token)
                        val signUpUserNickname = br.data.user.nickname
                        Toast.makeText(
                            mContext,
                            "${signUpUserNickname}님 가입을 축하합니다!",
                            Toast.LENGTH_SHORT
                        ).show()

                        finish()
                    }
                }

                override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

                }

            })

        }

    }

    override fun setValues() {
    }

// 비밀번호 검사 메소드
    fun compareTwoPasswords() : Boolean{
        val originalPassword = binding.edtPassword.text.toString()
        val repeatPassword = binding.edtPasswordRepeat.text.toString()

        if (originalPassword == repeatPassword) {
            binding.txtPasswordCheckResult2.text = "사용해도 좋습니다."
            return true
        }
        else {
            binding.txtPasswordCheckResult2.text = "위의 비밀번호와 일치해야 합니다."
            return false
        }


    }

}