package com.msg.model.param.auth

data class FindPasswordParam(
    val email: String,
    val newPassword: String, // 8 ~ 24 영어(대문자 소문자 상관 X) + 숫자 + 특수 문자(여러 개도 상관 X)
)
