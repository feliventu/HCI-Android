package com.example.homechan.data

class DataSourceException(
    var code: Int,
    message: String,
    var details: List<String>?
) : Exception(message)