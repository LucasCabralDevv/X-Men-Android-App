package com.lucascabral.x_menapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.lucascabral.x_menapp.data.api.XMenService
import com.lucascabral.x_menapp.data.network.model.Character
import com.lucascabral.x_menapp.data.repository.paging.CharacterPagingSource
import kotlinx.coroutines.flow.Flow

class CharactersRepository(private val apiService: XMenService) {

    fun getResultStream(): Flow<PagingData<Character>> {
        return Pager(config = PagingConfig(pageSize = pageSize, maxSize = pageMax),
            pagingSourceFactory = { CharacterPagingSource(apiService) }
        ).flow
    }

    companion object {
        const val pageSize: Int = 20
        const val pageMax: Int = 100
    }
}