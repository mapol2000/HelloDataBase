package mapol2000.hellodatabase.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "book")
class BookEntity(@PrimaryKey val bid: Int,
                 val bkName: String?,
                 val author: String?,
                 val pubDate: String?,
                 val price: String?,
                 val regDate: String?)
