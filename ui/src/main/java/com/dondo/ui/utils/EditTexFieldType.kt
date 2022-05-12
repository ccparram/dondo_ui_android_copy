package com.dondo.ui.utils

enum class EditTexFieldType(val value: Int) {
    TYPE_TEXT(0),
    TYPE_MULTILINE(1);

    companion object {
        fun getByValue(value: Int): EditTexFieldType {
            val map = values().associateBy(EditTexFieldType::value)
            return map[value] ?: throw IllegalArgumentException("There is not state associated to value: $value")
        }
    }
}


