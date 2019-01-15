package chat.dk.android.chatroom.adapter

import DrawableHelper
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import chat.dk.android.R
import chat.dk.android.chatroom.adapter.PeopleSuggestionsAdapter.PeopleSuggestionViewHolder
import chat.dk.android.chatroom.uimodel.suggestion.PeopleSuggestionUiModel
import chat.dk.android.suggestions.model.SuggestionModel
import chat.dk.android.suggestions.ui.BaseSuggestionViewHolder
import chat.dk.android.suggestions.ui.SuggestionsAdapter
import com.facebook.drawee.view.SimpleDraweeView

class PeopleSuggestionsAdapter(context: Context) : SuggestionsAdapter<PeopleSuggestionViewHolder>("@") {

    init {
        val allDescription = context.getString(R.string.suggest_all_description)
        val hereDescription = context.getString(R.string.suggest_here_description)
        val pinnedList = listOf(
                PeopleSuggestionUiModel(imageUri = null,
                        text = "all",
                        username = "all",
                        name = allDescription,
                        status = null,
                        pinned = false,
                        searchList = listOf("all")),
                PeopleSuggestionUiModel(imageUri = null,
                        text = "here",
                        username = "here",
                        name = hereDescription,
                        status = null,
                        pinned = false,
                        searchList = listOf("here"))
        )
        setPinnedSuggestions(pinnedList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeopleSuggestionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.suggestion_member_item, parent,
                false)
        return PeopleSuggestionViewHolder(view)
    }

    class PeopleSuggestionViewHolder(view: View) : BaseSuggestionViewHolder(view) {

        override fun bind(item: SuggestionModel, itemClickListener: SuggestionsAdapter.ItemClickListener?) {
            item as PeopleSuggestionUiModel
            with(itemView) {
                val username = itemView.findViewById<TextView>(R.id.text_username)
                val name = itemView.findViewById<TextView>(R.id.text_name)
                val avatar = itemView.findViewById<SimpleDraweeView>(R.id.image_avatar)
                val statusView = itemView.findViewById<ImageView>(R.id.image_status)
                username.text = item.username
                name.text = item.name
                if (item.imageUri?.isEmpty() != false) {
                    avatar.isVisible = false
                } else {
                    avatar.isVisible = true
                    avatar.setImageURI(item.imageUri)
                }
                val status = item.status
                if (status != null) {
                    val statusDrawable = DrawableHelper.getUserStatusDrawable(status, itemView.context)
                    statusView.setImageDrawable(statusDrawable)
                } else {
                    statusView.isVisible = false
                }
                setOnClickListener {
                    itemClickListener?.onClick(item)
                }
            }
        }
    }
}