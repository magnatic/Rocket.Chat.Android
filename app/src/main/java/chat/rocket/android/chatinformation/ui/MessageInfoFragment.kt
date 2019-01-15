package chat.dk.android.chatinformation.ui

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
import chat.dk.android.chatinformation.adapter.ReadReceiptAdapter
import chat.dk.android.chatinformation.presentation.MessageInfoPresenter
import chat.dk.android.chatinformation.presentation.MessageInfoView
import chat.dk.android.chatinformation.viewmodel.ReadReceiptViewModel
import chat.dk.android.util.extensions.showToast
import chat.dk.android.util.extensions.ui
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_message_info.*
import javax.inject.Inject

fun newInstance(messageId: String): Fragment {
    return MessageInfoFragment().apply {
        arguments = Bundle(1).apply {
            putString(BUNDLE_MESSAGE_ID, messageId)
        }
    }
}

internal const val TAG_MESSAGE_INFO_FRAGMENT = "MessageInfoFragment"
private const val BUNDLE_MESSAGE_ID = "message_id"

class MessageInfoFragment : Fragment(), MessageInfoView {
    @Inject
    lateinit var presenter: MessageInfoPresenter
    @Inject
    lateinit var analyticsManager: AnalyticsManager
    private lateinit var adapter: ReadReceiptAdapter
    private lateinit var messageId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
        setHasOptionsMenu(true)

        val bundle = arguments
        if (bundle != null) {
            messageId = bundle.getString(BUNDLE_MESSAGE_ID)
        } else {
            requireNotNull(bundle) { "no arguments supplied when the fragment was instantiated" }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_message_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        presenter.loadReadReceipts(messageId = messageId)

        analyticsManager.logScreenView(ScreenViewEvent.MessageInfo)
    }

    private fun setupRecyclerView() {
        // Initialize the endlessRecyclerViewScrollListener so we don't NPE at onDestroyView
        val linearLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, true)
        adapter = ReadReceiptAdapter()
        linearLayoutManager.stackFromEnd = true
        receipt_list.layoutManager = linearLayoutManager
        receipt_list.itemAnimator = DefaultItemAnimator()
        receipt_list.adapter = adapter
    }

    override fun showGenericErrorMessage() {
        showToast(R.string.msg_generic_error)
    }

    override fun showLoading() {
        ui {
            view_loading.isVisible = true
            view_loading.show()
        }
    }

    override fun hideLoading() {
        ui {
            view_loading.isVisible = false
            view_loading.hide()
        }
    }

    override fun showReadReceipts(messageReceipts: List<ReadReceiptViewModel>) {
        ui {
            adapter.addAll(messageReceipts)
        }
    }
}