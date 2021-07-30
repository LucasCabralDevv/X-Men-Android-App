package com.lucascabral.x_menapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.lucascabral.x_menapp.data.network.model.Character
import com.lucascabral.x_menapp.data.repository.CharactersRepository
import kotlinx.coroutines.flow.Flow

class CharactersViewModel(
    private val repository: CharactersRepository
): ViewModel() {

    fun getCharacters(): Flow<PagingData<Character>> {
        return  repository.getResultStream()
            .cachedIn(viewModelScope)
    }
}