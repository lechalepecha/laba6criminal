package com.bignerdranch.android.laba6criminal.database
import androidx.room.Dao
import androidx.room.Query
import com.bignerdranch.android.laba6criminal.Crime
import java.util.UUID
@Dao
interface CrimeDao {
    @Query("SELECT * FROM crime")
    fun getCrimes(): List<Crime>
    @Query("SELECT * FROM crime WHERE id=(:id)")
    fun getCrime(id: UUID): Crime?
}