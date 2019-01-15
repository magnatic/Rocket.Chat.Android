package chat.dk.android.emoji.internal

import chat.dk.android.emoji.Emoji

fun Emoji.isCustom(): Boolean = this.url != null
