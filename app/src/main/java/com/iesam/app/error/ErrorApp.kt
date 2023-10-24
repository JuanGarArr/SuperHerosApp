package com.iesam.app.error

sealed class ErrorApp {
    object UnknowError: ErrorApp()
    object DataError: ErrorApp()
    object InternetError: ErrorApp()
}