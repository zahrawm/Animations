package com.dannyose.rmapp.data.api


import com.dannyose.rmapp.data.model.Characters
import com.dannyose.rmapp.util.Constants.Companion.CHARACTER_ENDPOINT
import retrofit2.Response
import retrofit2.http.GET


interface CharacterAPI {
    @GET(CHARACTER_ENDPOINT)
    suspend fun getCharacters(
    ): Response<Characters>
}