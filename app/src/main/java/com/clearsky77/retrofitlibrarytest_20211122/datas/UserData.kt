package com.clearsky77.retrofitlibrarytest_20211122.datas

import com.google.gson.annotations.SerializedName

class UserData(
    var id: Int,
    var email: String,
    @SerializedName("nick_name") // <- 서버에서 주는 이름표는 이 이름표이나...
    var nickname: String, // 내가 쓸 때는 이렇게 쓰고 싶을 때
    @SerializedName("profile_img")
    var profileImageURL: String

) {
}