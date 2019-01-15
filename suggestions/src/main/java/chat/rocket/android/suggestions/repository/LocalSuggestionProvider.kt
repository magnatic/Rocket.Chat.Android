package chat.dk.android.suggestions.repository

interface LocalSuggestionProvider {
    fun find(prefix: String)
}