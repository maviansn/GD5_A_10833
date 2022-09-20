package room

import android.content.Context
import androidx.room.*

@Database(
    entities = [Note::class],
    version = 1 )
abstract class NoteDB: RoomDatabase() {
    abstract fun noteDao() : NoteDao
    companion object {
        @Volatile private var instance : NoteDB? = null
        private val LOCK = Any()
        operator fun invoke(context: Context) = instance ?:
        synchronized(LOCK){
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }
        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                NoteDB::class.java,
                "note12345.db"
            ).build()
    } }