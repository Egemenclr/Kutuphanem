package com.egemeninceler.kutuphanem.data.local.db.typeconverter

import android.net.Uri
import androidx.room.TypeConverter


class UriTypeConverter {
    @TypeConverter
    fun uriToString(uri: Uri?): String {
        return uri?.toString() ?: "uriTypeError"
    }


    @TypeConverter
    fun stringToUri(string: String?): Uri {
        return Uri.parse(string)

    }

}