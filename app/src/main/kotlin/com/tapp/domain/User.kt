package com.tapp.domain

import com.tapp.data.response.UserResponse

data class User(
    val firstName: String,
    val image: String,
    val token: String
)

fun UserResponse.toDomain(): User{
    return User(
        firstName = this.firstName,
        image = this.image,
        token = this.token
    )
}
