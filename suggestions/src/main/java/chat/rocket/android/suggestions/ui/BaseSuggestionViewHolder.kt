package chat.dk.android.suggestions.ui

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import chat.dk.android.suggestions.model.SuggestionModel

abstract class BaseSuggestionViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun bind(item: SuggestionModel, itemClickListener: SuggestionsAdapter.ItemClickListener?)
}
