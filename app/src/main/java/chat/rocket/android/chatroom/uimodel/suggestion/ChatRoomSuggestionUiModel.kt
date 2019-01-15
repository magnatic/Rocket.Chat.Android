package chat.dk.android.chatroom.uimodel.suggestion

import chat.dk.android.suggestions.model.SuggestionModel

class ChatRoomSuggestionUiModel(
    text: String,
    val fullName: String,
    val name: String,
    searchList: List<String>
) : SuggestionModel(text, searchList, false)
