package com.aziz.data.db.converter;

import androidx.annotation.Nullable;
import androidx.room.TypeConverter;

import java.util.Date;

public class DateConverter {

    @TypeConverter
    public static Long fromDate(@Nullable Date date) {
        if (date == null) return null;

        return date.getTime();
    }

    @TypeConverter
    public static Date toDate(@Nullable Long time) {
        if (time == null) return null;
        return new Date(time);
    }
}
