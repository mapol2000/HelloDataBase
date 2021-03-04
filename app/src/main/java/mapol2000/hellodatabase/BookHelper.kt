package mapol2000.hellodatabase

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class BookHelper(context: Context, version: Int) : SQLiteOpenHelper(context, dbname, null, version) {

    companion object {
        const val dbname: String = "mapol20003"
        const val version: Int = 1
    }

    override fun onOpen(sqlite: SQLiteDatabase) {
        super.onOpen(sqlite)
        sqlite.disableWriteAheadLogging()
    }

    // onCreate 호출시첨: readableDatabase, writableDatabase 호출시
    override fun onCreate(sqlite: SQLiteDatabase?) {
        sqlite?.execSQL("create table if not exists book (bid integer primary key autoincrement, brand text, bkName text, author text, pubDate text, price integer, regDate timestamp default current_timestamp)")
    }

    // onUpgrade 호출시점: 기존 버젼과 매개변수로 전달받은 버젼이 다른 경우
    // 만일, 기존 데이터를 보존하려면 기존 데이터를 백업하는 코드를 추가로 작성한 후 테이블 삭제해야 함
    override fun onUpgrade(sqlite: SQLiteDatabase?, oldVer: Int, newVer: Int) {
        // 데이터 대피코드 작성
        sqlite?.execSQL("drop table if exists book")
        onCreate(sqlite)
    }

    // 전체 도서목록 조회
    fun selectBook() : Cursor {
        val db = readableDatabase
        return db.rawQuery("select bid, bkName, pubDate, price from book order by bid desc", null)
    }

    // 도서목록 상세 조회
    fun selectOneBook(bid: String): Cursor {
        val db = readableDatabase
        return db.rawQuery("select * from book where bid = ?", arrayOf(bid.toString()))
    }

    // 도서정보 입력
    fun insertBook(bk: Book) {
        val db = readableDatabase
        db.execSQL("insert into book (brand, bkName, author, pubDate, price) values (?, ?, ?, ?, ?)", arrayOf(bk.brand, bk.bkName, bk.author, bk.pubDate, bk.price))
    }
}
