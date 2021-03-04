package mapol2000.hellodatabase.room

import androidx.room.*

@Dao
interface BookDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBook(book: BookEntity)

    @Update
    fun updateBook(vararg book: BookEntity)

    @Delete
    fun deleteBook(vararg book: BookEntity)

    @Query("select bid, bkName, pubDate, price from book order by bid desc")
    fun readBookAll() : List<BookEntity>

    @Query("select * from book where bid = :bid")
    fun readOneBook(bid: String) : BookEntity

}