package com.keruyun.open.italent.api

data class User(
        val userName: String,
        val nickName: String,
        val pwd: String,
        val device: String
)

data class Token(
        val access_token:String,
        val user_id:String,
        val tenant_id:String
)

data class Sigin(
        val Code:String
)