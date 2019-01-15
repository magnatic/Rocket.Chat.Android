package chat.dk.android.chatdetails.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import chat.dk.android.R
import chat.dk.android.chatdetails.adapter.OptionItemHolder
import chat.dk.android.chatdetails.adapter.OptionViewHolder
import chat.dk.android.chatdetails.domain.Option
import chat.dk.android.util.extensions.inflate

class ChatDetailsAdapter: RecyclerView.Adapter<OptionViewHolder>() {
    private val options: MutableList<Option> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OptionViewHolder = OptionViewHolder(parent.inflate(R.layout.item_detail_option))

    override fun onBindViewHolder(holder: OptionViewHolder, position: Int) = holder.bindViews(OptionItemHolder(options[position]))

    override fun getItemCount(): Int = options.size

    fun addOption(name: String, icon: Int, listener: () -> Unit) {
        options.add(Option(name, icon, listener))
        notifyDataSetChanged()
    }
}