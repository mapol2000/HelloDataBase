package mapol2000.hellodatabase

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.lang.StringBuilder

class ListBookActivity : AppCompatActivity() {

//    lateinit var dbHelper: MemberHelper

    lateinit var dbHelper: BookHelper
    lateinit var rv: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_book)

        rv = findViewById(R.id.recyclerView)

        var books: MutableList<Book> = readBookAll()
//        Log.d("listbook", "${books.get(0).bkName}")

        val rvAdapter = RVAdapter(this, books) { bk -> goView(bk, this)}
        rv.layoutManager = LinearLayoutManager(this)
        rv.setHasFixedSize(true)
        rv.adapter = rvAdapter

}
    // 리사이클러뷰 어댑터 클래스
    class RVAdapter(var context: Context, var books: MutableList<Book>, val itemClick: (Book) -> Unit) : RecyclerView.Adapter<RVAdapter.Holder>() {

        inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView){
            var tvBkName = itemView?.findViewById<TextView>(R.id.tvBkName)
            var tvAuthor = itemView?.findViewById<TextView>(R.id.tvAuthor)
            var tvPubDate = itemView?.findViewById<TextView>(R.id.tvPubDate)

            fun bind(bk: Book, context: Context) {
                tvBkName.text = bk.bkName
                tvAuthor.text = bk.author
                tvPubDate.text = bk.pubDate

                itemView.setOnClickListener { itemClick(bk)}
            }

        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
          val view = LayoutInflater.from(context).inflate(R.layout.bk_recyclerview, parent, false)
            return Holder(view)
        }

        override fun getItemCount(): Int {
            return books.size
        }

        override fun onBindViewHolder(holder: Holder, position: Int) {
            holder?.bind(books[position], context)
        }

    }

    private fun goView(bk: Book, context: Context) {
        val intent = Intent(this, ViewBookActivity::class.java)
        intent.putExtra("bkid", bk.bid)
        startActivity(intent)
    }

//    fun sqliteTest(context: Context) {
//        dbHelper = BookHelper(context, 1)
//        dbHelper.insertBook(Book("한빛미디어", "GAN 첫걸음", "타리크 라시드", "2021-03-10", "25000"))
//        dbHelper.insertBook(Book("한빛비즈", "부시파일럿, 나는 길이 없는 곳으로 간다", "오현호", "2021-03-10", "25000"))
//
//        var rs = dbHelper.selectBook()
//        var sb: StringBuilder = StringBuilder()
//        while(rs.moveToNext()) {
//            sb.append(rs.getString(rs.getColumnIndex("bid")))
//            sb.append(rs.getString(rs.getColumnIndex("bkName")))
//            sb.append(rs.getString(rs.getColumnIndex("pubDate")))
//            sb.append(rs.getString(rs.getColumnIndex("price")))
//            sb.append("\n")
//        }
//        rs.close()
//        Log.d("book", sb.toString())
//
//        rs = dbHelper.selectOneBook("1")
//        sb = StringBuilder()
//        while(rs.moveToNext()) {
//            sb.append(rs.getString(rs.getColumnIndex("bid")))
//            sb.append(rs.getString(rs.getColumnIndex("bkName")))
//            sb.append(rs.getString(rs.getColumnIndex("author")))
//            sb.append(rs.getString(rs.getColumnIndex("pubDate")))
//            sb.append(rs.getString(rs.getColumnIndex("price")))
//            sb.append(rs.getString(rs.getColumnIndex("regDate")))
//            sb.append("\n")
//        }
//        rs.close()
//        Log.d("book", sb.toString())
//
//        dbHelper.close()
//
//
//        //        dbHelper = MemberHelper(this)
////        dbHelper.insertMember("abc123", "987xyz")
////        dbHelper.insertMember("xyz987", "123abc")
////        Log.d("helper", "회원 데이터 저장성공")
//
////        var rs = dbHelper.selectMember2()
//////        var rs = dbHelper.selectMember()
////        var sb = StringBuilder()
////
////        sb.append("데이터 갯수: ${rs.count.toString()} \n")
////        while (rs.moveToNext()) {
////            val uid = rs.getString(rs.getColumnIndex("uid"))
////            val pwd = rs.getString(rs.getColumnIndex("pwd"))
////            sb.append("${uid} ${pwd} \n")
////        }
////        rs.close() // 커서 닫기
////        Log.d("helper", sb.toString())
////    }
//
////    var rs = dbHelper.selectMember3("abc123")
////    //        var rs = dbHelper.selectMember()
////    var sb = StringBuilder()
////
////    sb.append("데이터 갯수: ${rs.count.toString()} \n")
////    while (rs.moveToNext()) {
////        val cnt = rs.getString(rs.getColumnIndex("cnt"))
////        sb.append("조회한 데이터 수: ${cnt} \n")
////    }
////    rs.close() // 커서 닫기
////    Log.d("helper", sb.toString())
//
//    }

    // book 테이블에서 도서목록을 조회해서 동적배열로 넘김
    fun readBookAll(): MutableList<Book> {
        var books: MutableList<Book> = arrayListOf()
        var dbHelper = BookHelper(this, 2)

        val rs = dbHelper.selectBook()
        while (rs.moveToNext()) {
            var bid = rs.getString(rs.getColumnIndex("bid"))
            var bkName = rs.getString(rs.getColumnIndex("bkName"))
            var pubDate = rs.getString(rs.getColumnIndex("pubDate"))
            var price = rs.getString(rs.getColumnIndex("price"))

//            var bk = Book("", bkName, "", pubDate, price)
//            bk.bid = bid
            var bk = Book(bid, bkName, pubDate, price)

            books.add(bk)
        }
        rs.close()
        dbHelper.close()

        return books
    }

    fun goWrite(v: View) {
        val intent = Intent(this, WriteBookActivity::class.java)
        startActivity(intent)
    }



}


