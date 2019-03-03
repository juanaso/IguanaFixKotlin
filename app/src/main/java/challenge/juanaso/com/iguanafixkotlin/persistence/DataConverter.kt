package challenge.juanaso.com.iguanafixkotlin.persistence

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

class DataConverter {

    @TypeConverter
    fun fromDate(date: Date?): String? {
        if (date == null) {
            return null
        }
        val gson = Gson()
        val type = object : TypeToken<Date>(){}.type
        return gson.toJson(date, type)
    }

    @TypeConverter
    fun toDate(dateString: String?): Date? {
        if (dateString == null) {
            return null
        }
        val gson = Gson()
        val type = object : TypeToken<Date>() {

        }.type
        return gson.fromJson(dateString, type)
    }
}
