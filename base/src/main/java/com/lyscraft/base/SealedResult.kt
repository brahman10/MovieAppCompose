package com.lyscraft.base

sealed interface SealedResult<out R> {
    data object Loading : SealedResult<Nothing>
    data class Success<out T>(val data: T) : SealedResult<T>
    data class Error<out T>(val message: String, val code: Int) : SealedResult<T>

    data object None : SealedResult<Nothing>
}