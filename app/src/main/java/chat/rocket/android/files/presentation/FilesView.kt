package chat.dk.android.files.presentation

import android.net.Uri
import chat.dk.android.core.behaviours.LoadingView
import chat.dk.android.core.behaviours.MessageView
import chat.dk.android.files.uimodel.FileUiModel

interface FilesView : MessageView, LoadingView {

    /**
     * Shows the list of files for the current room.
     *
     * @param dataSet The data set to show.
     * @param total The total number of files.
     */
    fun showFiles(dataSet: List<FileUiModel>, total: Long)

    /**
     * Plays a media file (audio/video).
     *
     * @param url The file url to play its media.
     */
    fun playMedia(url: String)

    /**
     * Opens an image file
     *
     * @param url The file url to open its image.
     * @param name The file name.
     */
    fun openImage(url: String, name: String)

    /**
     * Opens a document file (.pdf, .txt and so on).
     *
     * @param uri The file uri to open its document.
     */
    fun openDocument(uri: Uri)
}