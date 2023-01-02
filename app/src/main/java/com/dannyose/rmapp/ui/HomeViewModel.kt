package com.dannyose.rmapp.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dannyose.rmapp.data.repository.CharacterRepository

class HomeViewModel(
    application: Application
):ViewModel() {

    private  val  characterRepo = CharacterRepository()

    val characterList = characterRepo.characters

    class HomeViewModelFactory(
        private val application: Application
    ): ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
                HomeViewModel(application) as T
            } else {
                throw IllegalArgumentException("Unknown ViewModel class")
            }
        }
    }

}