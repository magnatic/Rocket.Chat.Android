package chat.dk.android.mentions.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import chat.dk.android.R
import chat.dk.android.analytics.AnalyticsManager
import chat.dk.android.analytics.event.ScreenViewEvent
import chat.dk.android.chatdetails.ui.ChatDetailsActivity
import chat.dk.android.chatroom.adapter.ChatRoomAdapter
import chat.dk.android.chatroom.uimodel.BaseUiModel
import chat.dk.android.helper.EndlessRecyclerViewScrollListener
import chat.dk.android.mentions.presentention.MentionsPresenter
import chat.dk.android.mentions.presentention.MentionsView
import chat.dk.android.util.extensions.inflate
import chat.dk.android.util.extensions.showToast
import chat.dk.android.util.extensions.ui
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_mentions.*
import javax.inject.Inject

fun newInstance(chatRoomId: String): Fragment {
    return MentionsFragment().apply {
        arguments = Bundle(1).apply {
            putString(BUNDLE_CHAT_ROOM_ID, chatRoomId)
        }
    }
}

internal const val TAG_MENTIONS_FRAGMENT = "MentionsFragment"
private const val BUNDLE_CHAT_ROOM_ID = "chat_room_id"

class MentionsFragment : Fragment(), MentionsView {

    private lateinit var chatRoomId: String
    private val adapter = ChatRoomAdapter(enableActions = false)
    @Inject
    lateinit var presenter: MentionsPresenter
    @Inject
    lateinit var analyticsManager: AnalyticsManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)

        val bundle = arguments
        if (bundle != null) {
            chatRoomId = bundle.getString(BUNDLE_CHAT_ROOM_ID)
        } else {
            requireNotNull(bundle) { "no arguments supplied when the fragment was instantiated" }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = container?.inflate(R.layout.fragment_mentions)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupToolbar()
        presenter.loadMentions(chatRoomId)

        analyticsManager.logScreenView(ScreenViewEvent.Mentions)
    }

    override fun showMentions(mentions: List<BaseUiModel<*>>) {
        ui {
            if (recycler_view.adapter == null) {
                recycler_view.adapter = adapter

                val linearLayoutManager = LinearLayoutManager(context)
                recycler_view.layoutManager = linearLayoutManager
                recycler_view.itemAnimator = DefaultItemAnimator()
                if (mentions.size >= 30) {
                    recycler_view.addOnScrollListener(object :
                        EndlessRecyclerViewScrollListener(linearLayoutManager) {
                        override fun onLoadMore(
                            page: Int,
                            totalItemsCount: Int,
                            recyclerView: RecyclerView
                        ) {
                            presenter.loadMentions(chatRoomId)
                        }

                    })
                }
                group_no_mention.isVisible = mentions.isEmpty()
            }
            adapter.appendData(mentions)
        }
    }

    override fun showMessage(resId: Int) {
        ui {
            showToast(resId)
        }
    }

    override fun showMessage(message: String) {
        ui {
            showToast(message)
        }
    }

    override fun showGenericErrorMessage() = showMessage(getString(R.string.msg_generic_error))

    override fun showLoading() {
        ui { view_loading.isVisible = true }
    }

    override fun hideLoading() {
        ui { view_loading.isVisible = false }
    }

    private fun setupToolbar() {
        (activity as ChatDetailsActivity).let {
            it.setToolbarTitle(getString(R.string.msg_mentions))
            it.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp)
        }
    }
}