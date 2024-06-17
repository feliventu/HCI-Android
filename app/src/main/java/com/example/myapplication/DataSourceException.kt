package com.example.myapplication

class DataSourceException(
    var code: Int,
    message: String,
    var details: List<String>?
) : Exception(message)