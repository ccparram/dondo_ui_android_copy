package com.dondo.ui.button

enum class ButtonState(val value: Int) {
    PRIMARY_ENABLED(0),
    PRIMARY_DISABLED(1),
    SECONDARY_ENABLED(2),
    SECONDARY_DISABLED(3);

    companion object {
        fun getByValue(value: Int): ButtonState {
            val map = values().associateBy(ButtonState::value)
            return map[value] ?: throw IllegalArgumentException("There is not state associated to value: $value")
        }
    }
}
