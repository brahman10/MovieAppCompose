package com.lyscraft.data.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.lyscraft.data.exceptions.NoInternetException
import okhttp3.Interceptor
import okhttp3.Interceptor.*
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject


class NetworkInterceptor @Inject constructor(private val context: Context) : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Chain): Response {
        if (!isConnected()) {
            throw NoInternetException()
        }
        return chain.proceed(chain.request())
    }

    private fun isConnected(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        val networkCapabilities = connectivityManager?.activeNetwork ?: return false
        val actNw =
            connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
        return when {
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }
}