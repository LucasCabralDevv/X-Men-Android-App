package com.lucascabral.x_menapp.data.repository.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.lucascabral.x_menapp.data.api.XMenService
import com.lucascabral.x_menapp.data.network.model.Character
import java.lang.Exception

class CharacterPagingSource(private val apiService: XMenService) : PagingSource<Int, Character>() {
    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        return try {
            val page: Int = params.key ?: FIRST_PAGE_INDEX
            val response = apiService.getCharacters(page)
            val nextPage: Int = response.info.page + 1
            LoadResult.Page(data = response.results, prevKey = null, nextKey = nextPage)
        } catch (ex: Exception) {
            LoadResult.Error(ex)
        }
    }

    companion object {
        private const val FIRST_PAGE_INDEX = 1
    }
}