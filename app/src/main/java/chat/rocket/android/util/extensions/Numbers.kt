package chat.dk.android.util.extensions

import org.threeten.bp.LocalDateTime

fun Long?.localDateTime(): LocalDateTime? {
    return this?.let {
        DateTimeHelper.getLocalDateTime(it)
    }
}