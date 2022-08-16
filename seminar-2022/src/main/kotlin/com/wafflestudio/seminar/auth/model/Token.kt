package com.wafflestudio.seminar.auth.model

data class Token(
    val accessToken: String,
    val refreshToken: String,
)