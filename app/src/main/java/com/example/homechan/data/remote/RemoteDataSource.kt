package com.example.homechan.data.remote


import com.example.homechan.data.DataSourceException
import com.example.homechan.data.remote.model.RemoteError
import com.example.homechan.data.remote.model.RemoteResult
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Response
import java.io.IOException

abstract class RemoteDataSource {

    suspend fun <T : Any> handleApiResponse(
        execute: suspend () -> Response<RemoteResult<T>>
    ): T {
        try {
            val response = execute()
            val body = response.body()
            if (response.isSuccessful && body != null) {
                return body.result
            }
            response.errorBody()?.let {
                val gson = Gson()
                val error = gson.fromJson<RemoteError>(
                    it.string(),
                    object : TypeToken<RemoteError?>() {}.type
                )
                throw DataSourceException(error.code, "", error.description)
            }
            throw DataSourceException(UNEXPECTED_ERROR_CODE, "Missing error", null)
        } catch (e: DataSourceException) {
            throw e
        } catch (e: IOException) {
            throw DataSourceException(
                CONNECTION_ERROR_CODE,
                "Connection error",
                getDetailsFromException(e)
            )
        } catch (e: Exception) {
            throw DataSourceException(
                UNEXPECTED_ERROR_CODE,
                "Unexpected error",
                getDetailsFromException(e)
            )
        }
    }

    private fun getDetailsFromException(e: Exception): List<String> {
        return if (e.message != null) listOf(e.message!!) else emptyList()
    }

    companion object {
        const val CONNECTION_ERROR_CODE = 98
        const val UNEXPECTED_ERROR_CODE = 99
    }
}