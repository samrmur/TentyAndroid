package com.tenty.tentyandroid.util.types

enum class ContactType {
    CALL, TEXT
}

fun getContactTypeAsString(type: ContactType): String {
    return when(type) {
        ContactType.CALL -> "CALL"
        ContactType.TEXT -> "TEXT"
    }
}

fun getStringAsContactType(type: String): ContactType? {
    return when(type) {
        "CALL" -> ContactType.CALL
        "TEXT" -> ContactType.TEXT
        else -> null
    }
}