package com.herco.bmicalculatorkotlin.dto

import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
data class BmiResultModel(var status: Int, var message: Int, var result: Float) {
    fun toStringJson(): String {
        return Json.encodeToString(this)
    }

    companion object {
        fun fromJson(json: String): BmiResultModel {
            return Json.decodeFromString(json)
        }
    }
}