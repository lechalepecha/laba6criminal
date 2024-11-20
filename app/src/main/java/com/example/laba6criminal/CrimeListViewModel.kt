package com.example.laba6criminal

import androidx.lifecycle.ViewModel

class CrimeListViewModel: ViewModel() {
    val crimes = mutableListOf<Crime>()
    init {
        for( i in 0..100)
        {
            val crime = Crime()
            crime.title = "Дело #$i"
            crime.isSolved = i % 2 == 0
            crimes += crime
        }
    }
}