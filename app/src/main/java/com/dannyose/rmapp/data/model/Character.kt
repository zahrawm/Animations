package com.dannyose.rmapp.data.model


import com.google.gson.annotations.SerializedName

data class Character(
    @SerializedName("id")
    val id: Int,

    @SerializedName("gender")
    val gender: String,

    @SerializedName("image")
    val image: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("species")
    val species: String,

    @SerializedName("status")
    val status: String,

    @SerializedName("type")
    val type: String

)