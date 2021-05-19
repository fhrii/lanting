package academy.bangkit.lanting.utils

import java.lang.Exception

sealed class ResultState<out T> {
    data class Success<out R>(val data: R) : ResultState<R>()
    data class Error(val exception: Exception): ResultState<Nothing>()
    object Loading : ResultState<Nothing>()
}
