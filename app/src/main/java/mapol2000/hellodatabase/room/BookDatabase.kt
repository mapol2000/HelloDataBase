package mapol2000.hellodatabase.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [BookEntity::class], version = 1)
abstract class BookDatabase : RoomDatabase() {
    abstract fun bookDao() : BookDao

    companion object{

        private var INSTANCE : BookDatabase? = null

        fun getDatabase(context : Context) : BookDatabase?{
            if(INSTANCE == null){
                synchronized(BookDatabase::class){
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        BookDatabase::class.java,
                        "mapol20004") // 디비명
                        .build()
                }
            }
            return INSTANCE
        }
    }
}