package chat.dk.android.preferences.presentation

import chat.dk.android.server.domain.AnalyticsTrackingInteractor
import javax.inject.Inject

class PreferencesPresenter @Inject constructor(
    private val view: PreferencesView,
    private val analyticsTrackingInteractor: AnalyticsTrackingInteractor
) {

    fun loadAnalyticsTrackingInformation() {
        view.setupAnalyticsTrackingView(analyticsTrackingInteractor.get())
    }

    fun enableAnalyticsTracking() {
        analyticsTrackingInteractor.save(true)
    }

    fun disableAnalyticsTracking() {
        analyticsTrackingInteractor.save(false)
    }
}