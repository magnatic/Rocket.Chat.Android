package chat.dk.android.draw.widget

import android.graphics.Path
import java.io.Serializable

interface Action : Serializable {

    fun perform(path: Path)
}