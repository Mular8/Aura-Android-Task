package com.mularo.aura.db

import java.util.Date

class BootRepository {
    companion object {
        fun getDates(): List<Date> {
            //todo: this should be read from db not hardcoded dates
            return listOf(Date(1728254022), Date(1728251022), Date(1728254022))
        }
    }
}