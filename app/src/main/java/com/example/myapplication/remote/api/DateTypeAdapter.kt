package com.example.myapplication.remote.api

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import java.util.Date

class DateTypeAdapter : TypeAdapter<Date?>() {
    override fun write(out: JsonWriter, value: Date?) {
        if (value == null) out.nullValue() else out.value(value.time)
    }

    override fun read(`in`: JsonReader): Date? {
        return if (`in`.peek() != JsonToken.NULL) Date(`in`.nextLong()) else null
    }
}