package mapol2000.hellodatabase

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import mapol2000.hellodatabase.room.BookDatabase
import mapol2000.hellodatabase.room.BookEntity

class RoomActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)

//        addBook(applicationContext).start()
//        Log.d("room1", "도서정보 입력")

        modifyBook(applicationContext).start()
        Log.d("room1", "101 도서정보 수정")

        removeBook(applicationContext).start()
        Log.d("room1", "101 도서정보 삭제")

        getBook(applicationContext,"102").start()
        Log.d("room1", "103 도서정보 조회")


    }

    class addBook(val context: Context) : Thread() {
        override fun run() {
            var bk = BookEntity(100, "도서제목1", "저자1", "2021-03-01", "15000", "2021-02-25")
            BookDatabase.getDatabase(context)!!.bookDao().insertBook(bk)

            bk = BookEntity(101, "도서제목2", "저자2", "2021-03-01", "15000", "2021-02-25")
            BookDatabase.getDatabase(context)!!.bookDao().insertBook(bk)

            bk = BookEntity(102, "도서제목3", "저자3", "2021-03-01", "15000", "2021-02-25")
            BookDatabase.getDatabase(context)!!.bookDao().insertBook(bk)
        }

    }

    class modifyBook(val context: Context) : Thread() {
        override fun run() {
            var bk = BookEntity(101, "도서제목2b", "저자2b", "2021-03-05", "35000", "2021-03-25")
            BookDatabase.getDatabase(context)!!.bookDao().updateBook(bk)
        }
    }

    class removeBook(val context: Context) : Thread() {
        override fun run() {
            var bk = BookEntity(101, null, null, null, null, null)
            BookDatabase.getDatabase(context)!!.bookDao().deleteBook(bk)
        }
    }

    class getBook(val context: Context, val bid: String) : Thread() {
        override fun run() {
           var bk = BookDatabase.getDatabase(context)!!.bookDao().readOneBook(bid)

            Log.d("room1", "${bk.bkName} ${bk.author} ${bk.price}")
        }
    }

    class getAllBook(val context: Context) : Thread() {
        override fun run() {
            var bks = BookDatabase.getDatabase(context)!!.bookDao().readBookAll()

            for (bk in bks) {
                Log.d("room1", "${bk.bid} ${bk.bkName} ${bk.pubDate} ${bk.price}\n")
            }
        }
    }


}