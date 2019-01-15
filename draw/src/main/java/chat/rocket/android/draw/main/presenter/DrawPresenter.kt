package chat.dk.android.draw.main.presenter

import android.graphics.Bitmap
import chat.dk.android.core.lifecycle.CancelStrategy
import chat.dk.android.util.extension.compressImageAndGetByteArray
import chat.dk.android.util.extension.launchUI
import javax.inject.Inject

class DrawPresenter @Inject constructor(
    private val view: DrawView,
    private val strategy: CancelStrategy
) {

    fun processDrawingImage(bitmap: Bitmap) {
        launchUI(strategy) {
            val byteArray = bitmap.compressImageAndGetByteArray("image/png")
            if (byteArray != null) {
                view.sendByteArray(byteArray)
            } else {
                view.showWrongProcessingMessage()
            }
        }
    }
}