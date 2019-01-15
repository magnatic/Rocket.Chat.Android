package chat.dk.android.chatroom.adapter

import android.annotation.SuppressLint
import android.text.SpannableStringBuilder
import android.text.style.ImageSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import chat.dk.android.R
import chat.dk.android.chatroom.adapter.EmojiSuggestionsAdapter.EmojiSuggestionViewHolder
import chat.dk.android.chatroom.uimodel.suggestion.EmojiSuggestionUiModel
import chat.dk.android.emoji.EmojiParser
import chat.dk.android.emoji.internal.isCustom
import chat.dk.android.suggestions.model.SuggestionModel
import chat.dk.android.suggestions.strategy.trie.TrieCompletionStrategy
import chat.dk.android.suggestions.ui.BaseSuggestionViewHolder
import chat.dk.android.suggestions.ui.SuggestionsAdapter
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.suggestion_emoji_item.view.*

class EmojiSuggestionsAdapter : SuggestionsAdapter<EmojiSuggestionViewHolder>(
    token = ":",
    completionStrategy = TrieCompletionStrategy()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmojiSuggestionViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.suggestion_emoji_item, parent,false)
        return EmojiSuggestionViewHolder(view)
    }

    class EmojiSuggestionViewHolder(view: View) : BaseSuggestionViewHolder(view) {

        @SuppressLint("SetTextI18n")
        override fun bind(item: SuggestionModel, itemClickListener: SuggestionsAdapter.ItemClickListener?) {
            item as EmojiSuggestionUiModel
            with(itemView) {
                text_emoji_shortname.text = ":${item.text}"
                if (item.emoji.isCustom()) {
                    view_flipper_emoji.displayedChild = 1
                    val sp = SpannableStringBuilder().append(item.emoji.shortname)
                    Glide.with(context).asDrawable().load(item.emoji.url).into(image_emoji)
                } else {
                    text_emoji.text = EmojiParser.parse(context, item.emoji.unicode)
                    view_flipper_emoji.displayedChild = 0
                }
                setOnClickListener {
                    itemClickListener?.onClick(item)
                }
            }
        }
    }
}
