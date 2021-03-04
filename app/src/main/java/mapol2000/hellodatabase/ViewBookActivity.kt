package mapol2000.hellodatabase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView

class ViewBookActivity : AppCompatActivity() {

    var vbatv = arrayOfNulls<TextView>(6)
    var tvid = arrayOf(R.id.vbrand, R.id.vbkName, R.id.vauthor, R.id.vpubDate, R.id.vprice, R.id.vregDate)
    lateinit var dbHelper: BookHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_book)

        title = "ViewBook"

        var intent = Intent()
        var bkid = intent.getStringExtra("bkid")
        var bk = readOneBook(bkid.toString())

        for (i in vbatv.indices) {
            vbatv[i] = findViewById(tvid[i])
        }

        vbatv[0]?.setText(bk!!.brand)
        vbatv[1]?.setText(bk!!.bkName)
        vbatv[2]?.setText(bk!!.author)
        vbatv[3]?.setText(bk!!.pubDate)
        vbatv[4]?.setText(bk!!.price)
        vbatv[5]?.setText(bk!!.regDate)

    }

    fun readOneBook(bid: String) : Book? {
        var book: Book? = null
        var dbHelper = BookHelper(this, 2)
        var rs = dbHelper.selectOneBook(bid)

        while (rs.moveToNext()) {
            var bid = rs.getString(rs.getColumnIndex("bid"))
            var brand = rs.getString(rs.getColumnIndex("brand"))
            var bkName = rs.getString(rs.getColumnIndex("bkName"))
            var author = rs.getString(rs.getColumnIndex("author"))
            var pubDate = rs.getString(rs.getColumnIndex("pubDate"))
            var price = rs.getString(rs.getColumnIndex("price"))
            var regDate = rs.getString(rs.getColumnIndex("regDate"))

            book = Book(bid,brand,bkName,author,pubDate,price,regDate)

        }
        rs.close()
        dbHelper.close()

        return book
    }


}