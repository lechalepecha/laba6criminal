package com.example.laba6criminal.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Insert
import androidx.room.Update
import androidx.lifecycle.LiveData
import com.example.laba6criminal.Crime
import java.util.UUID
@Dao
interface CrimeDao {
    @Query("SELECT * FROM crime")
    fun getCrimes(): LiveData<List<Crime>>
    @Query("SELECT * FROM crime WHERE id=(:id)")
    fun getCrime(id: UUID): LiveData<Crime?>
    @Update
    fun updateCrime(crime: Crime)
    @Insert
    fun addCrime(crime: Crime)
}