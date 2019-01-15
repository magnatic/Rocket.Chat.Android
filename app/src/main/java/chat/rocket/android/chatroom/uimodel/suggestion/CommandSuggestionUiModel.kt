package chat.dk.android.chatroom.uimodel.suggestion

import chat.dk.android.suggestions.model.SuggestionModel

class CommandSuggestionUiModel(
    text: String,
    val description: String,
    searchList: List<String>
) : SuggestionModel(text, searchList)