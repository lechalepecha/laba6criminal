package com.bignerdranch.android.laba6criminal
import android.app.Application
class CriminalIntentApplication : Application()
{
    override fun onCreate() {
        super.onCreate()
        CrimeRepository.initialize(this)
    }
}