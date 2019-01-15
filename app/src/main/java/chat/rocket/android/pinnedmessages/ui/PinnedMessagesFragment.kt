package chat.dk.android.pinnedmessages.ui

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
import chat.dk.android.chatroom.ui.ChatRoomActivity
import chat.dk.android.chatroom.uimodel.BaseUiModel
import chat.dk.android.helper.EndlessRecyclerViewScrollListener
import chat.dk.android.pinnedmessages.presentation.PinnedMessagesPresenter
import chat.dk.android.pinnedmessages.presentation.PinnedMessagesView
import chat.dk.android.server.domain.AnalyticsTrackingInteractor
import chat.dk.android.util.extensions.inflate
import chat.dk.android.util.extensions.showToast
import chat.dk.android.util.extensions.ui
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_pinned_messages.*
import javax.inject.Inject

fun newInstance(chatRoomId: String): Fragment {
    return PinnedMessagesFragment().apply {
        arguments = Bundle(1).apply {
            putString(BUNDLE_CHAT_ROOM_ID, chatRoomId)
        }
    }
}

internal const val TAG_PINNED_MESSAGES_FRAGMENT = "PinnedMessagesFragment"
private const val BUNDLE_CHAT_ROOM_ID = "chat_room_id"

class PinnedMessagesFragment : Fragment(), PinnedMessagesView {
    @Inject
    lateinit var presenter: PinnedMessagesPresenter
    @Inject
    lateinit var analyticsManager: AnalyticsManager
    private lateinit var chatRoomId: String
    private val adapter = ChatRoomAdapter(enableActions = false)

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
    ): View? = container?.inflate(R.layout.fragment_pinned_messages)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupToolbar()
        presenter.loadPinnedMessages(chatRoomId)

        analyticsManager.logScreenView(ScreenViewEvent.PinnedMessages)
    }

    override fun showPinnedMessages(pinnedMessages: List<BaseUiModel<*>>) {
        ui {
            if (recycler_view_pinned.adapter == null) {
                recycler_view_pinned.adapter = adapter

                val linearLayoutManager = LinearLayoutManager(context)
                recycler_view_pinned.layoutManager = linearLayoutManager
                recycler_view_pinned.itemAnimator = DefaultItemAnimator()
                if (pinnedMessages.size >= 30) {
                    recycler_view_pinned.addOnScrollListener(object :
                        EndlessRecyclerViewScrollListener(linearLayoutManager) {
                        override fun onLoadMore(
                            page: Int,
                            totalItemsCount: Int,
                            recyclerView: RecyclerView
                        ) {
                            presenter.loadPinnedMessages(chatRoomId)
                        }

                    })
                }
                pin_view.isVisible = pinnedMessages.isEmpty()
            }
            adapter.appendData(pinnedMessages)
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
            it.setToolbarTitle(getString(R.string.title_pinned_messages))
            it.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp)
        }
    }
}