package com.dannyose.rmapp.data.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.MutableLiveData
import com.dannyose.rmapp.data.api.CharacterAPI
import com.dannyose.rmapp.data.model.Characters
import com.dannyose.rmapp.util.Constants.Companion.BASE_URL
import com.google.gson.GsonBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CharacterRepository {
    val characters = MutableLiveData<Characters>()

    init {
        CoroutineScope(Dispatchers.IO).launch {
            getCharacters()
        }
    }

    @WorkerThread
    suspend fun getCharacters() {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        val gson = GsonBuilder()
            .create()

        val retrofit = Retrofit.Builder()
            .client(client)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        val service = retrofit.create(CharacterAPI::class.java)
        val charactersList = service.getCharacters().body()?: emptyList<Characters>()

        characters.postValue(charactersList as Characters)
    }
}