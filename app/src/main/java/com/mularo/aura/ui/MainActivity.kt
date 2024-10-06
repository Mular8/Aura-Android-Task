package com.mularo.aura.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mularo.aura.databinding.ActivityMainBinding
import com.mularo.aura.db.BootRepository
import com.mularo.aura.notification.NotificationHandler
import com.mularo.aura.utils.formatDateShort
import java.util.Date

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dates = BootRepository.getDates()
        NotificationHandler.showNotification(
            this,
            dates
        )
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.tvText.text = generateText(dates)
    }

    private fun generateText(dates: List<Date>): String {
        return when (dates.size) {
            0 -> "No boots detected"
            else -> generateLog(dates)
        }
    }

    private fun generateLog(dates: List<Date>): String {
        val bootEventInfo = StringBuilder()
        val datesGrouped = dates.groupBy { it }
        for ((date, value) in datesGrouped.entries) {
            bootEventInfo.append(formatDateShort(date)).append(" - ").append(value.size)
                .append("\n")
        }

        return bootEventInfo.toString()
    }
}