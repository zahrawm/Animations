package com.dannyose.rmapp.data.model


import com.dannyose.rmapp.data.model.Character
import com.google.gson.annotations.SerializedName

data class Characters(
    @SerializedName("results")
    val characters: List<Character?>
)