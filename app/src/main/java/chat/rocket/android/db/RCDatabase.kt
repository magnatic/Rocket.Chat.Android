package chat.dk.android.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import chat.dk.android.db.model.AttachmentActionEntity
import chat.dk.android.db.model.AttachmentEntity
import chat.dk.android.db.model.AttachmentFieldEntity
import chat.dk.android.db.model.ChatRoomEntity
import chat.dk.android.db.model.MessageChannels
import chat.dk.android.db.model.MessageEntity
import chat.dk.android.db.model.MessageFavoritesRelation
import chat.dk.android.db.model.MessageMentionsRelation
import chat.dk.android.db.model.MessagesSync
import chat.dk.android.db.model.ReactionEntity
import chat.dk.android.db.model.UrlEntity
import chat.dk.android.db.model.UserEntity
import chat.dk.android.emoji.internal.db.StringListConverter

@Database(
    entities = [
        UserEntity::class, ChatRoomEntity::class, MessageEntity::class,
        MessageFavoritesRelation::class, MessageMentionsRelation::class,
        MessageChannels::class, AttachmentEntity::class,
        AttachmentFieldEntity::class, AttachmentActionEntity::class, UrlEntity::class,
        ReactionEntity::class, MessagesSync::class
    ],
    version = 11,
    exportSchema = true
)
@TypeConverters(StringListConverter::class)
abstract class RCDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun chatRoomDao(): ChatRoomDao
    abstract fun messageDao(): MessageDao
}
