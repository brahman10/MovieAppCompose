package com.lyscraft.base

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

fun <R, E> mapFromApiResponse(
    result: Flow<SealedResult<R>>,
    mapper: BaseMapper<R, E>
): Flow<SealedResult<E>> {
    return result.map {
        when (it) {
            is SealedResult.Success -> SealedResult.Success(mapper.mapData(it.data))
            is SealedResult.Error -> SealedResult.Error(it.message, it.code)
            is SealedResult.Loading -> SealedResult.Loading
            is SealedResult.None -> SealedResult.None
        }
    }
}