package org.thuddle.codetours.model

import kotlinx.serialization.Serializable

@Serializable
data class TourStep(
    val description: String,
    val file: String = "",
    val directory: String = "",
    val uri: String = "",
    val line: Int = 0,
    val pattern: String = "",
    val title: String = ""
)
