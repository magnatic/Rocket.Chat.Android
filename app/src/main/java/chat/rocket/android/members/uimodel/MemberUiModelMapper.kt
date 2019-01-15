package chat.dk.android.members.uimodel

import chat.dk.android.server.domain.GetCurrentServerInteractor
import chat.dk.android.server.domain.GetSettingsInteractor
import chat.dk.android.server.domain.baseUrl
import chat.rocket.common.model.User
import chat.rocket.core.model.Value
import javax.inject.Inject

class MemberUiModelMapper @Inject constructor(
    serverInteractor: GetCurrentServerInteractor,
    getSettingsInteractor: GetSettingsInteractor
) {
    private var settings: Map<String, Value<Any>> = getSettingsInteractor.get(serverInteractor.get()!!)
    private val baseUrl = settings.baseUrl()

    fun mapToUiModelList(memberList: List<User>): List<MemberUiModel> {
        return memberList.map { MemberUiModel(it, settings, baseUrl) }
    }
}
