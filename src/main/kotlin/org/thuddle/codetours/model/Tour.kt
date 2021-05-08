package org.thuddle.codetours.model

import kotlinx.serialization.Serializable

@Serializable
data class Tour(
    val title: String,
    val description: String = "",
    val isPrimary: Boolean = false,
    val nextTour: String = "",
    val steps: ArrayList<TourStep>
)

