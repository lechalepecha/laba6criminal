package com.bignerdranch.android.laba6criminal.database
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.bignerdranch.android.laba6criminal.Crime

@Database(entities = [ Crime::class ],
    version=1)
@TypeConverters(CrimeTypeConverters::class)
abstract class CrimeDatabase : RoomDatabase() {
}