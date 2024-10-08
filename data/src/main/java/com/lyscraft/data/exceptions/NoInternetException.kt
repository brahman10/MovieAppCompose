package com.lyscraft.data.exceptions

import java.io.IOException


class NoInternetException : IOException() {
    override val message: String
        get() = "No internet connection"
}