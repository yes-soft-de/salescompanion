package de.sixbits.salescompanion.data_model

import java.util.*

data class SalesContactDataModel(
    val firstName: String,
    val lastName: String,
    val phone: String?,
    val createdAt: Date?,
    val updatedAt: Date?,
    val synced: Boolean = false
)