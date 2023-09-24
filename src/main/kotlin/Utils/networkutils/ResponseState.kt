package Utils.networkutils

sealed class ResponseState<T>(val data : T? = null, val error : Exception? =null,val defaultValue: T? = null) {
    class Success<T>(val successData: T?) : ResponseState<T>(data = successData)
    class Error<T>(val errorData: Exception?) : ResponseState<T>(error =errorData)
    class Loading<T> : ResponseState<T>()
    class Default<T>() : ResponseState<T>()

}