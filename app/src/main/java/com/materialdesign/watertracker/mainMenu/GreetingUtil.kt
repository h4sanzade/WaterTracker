package com.materialdesign.watertracker.mainMenu

import com.materialdesign.watertracker.databinding.FragmentEntranceSliderTwoBinding
import java.util.Calendar

object GreetingUtil {
    fun getGreetingMessage(userName: String): String {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)

        val greeting = when (hour) {
            in 5..11 -> "Good morning"
            in 12..16 -> "Good afternoon"
            in 17..20 -> "Good evening"
            else -> "Good night"
        }

        return "$greeting, $userName!"
    }
}
