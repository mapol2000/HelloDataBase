package mapol2000.hellodatabase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_write_book.*


class WriteBookActivity : AppCompatActivity() {

    var wbaet = arrayOfNulls<EditText>(5)
    var etid = arrayOf(R.id.brand, R.id.bkName, R.id.author, R.id.pubDate, R.id.price)

    lateinit var dbHelper: BookHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write_book)

        title = "WriteBook"

        for (i in etid.indices) {
            wbaet[i] = findViewById(etid[i])
        }

    }

    fun goWriteOK(v: View) {

        // 도서정보 항목에 입력값이 없는 경우
        // Toast로 "도서정보가 입력되지 않았어요!"라는 메세지 출력
        for (i in etid.indices) {
            if (wbaet[i]!!.text.toString() == "") {
                Toast.makeText(this, "도서정보가 입력되지 않았어요!", Toast.LENGTH_SHORT).show()
                return
            }
        }

        var brand = wbaet[0]!!.text.toString()
        var bkName = wbaet[1]!!.text.toString()
        var author = wbaet[2]!!.text.toString()
        var pubDate = wbaet[3]!!.text.toString()
        var price = wbaet[4]!!.text.toString()

        dbHelper = BookHelper(this, 2)
        dbHelper.insertBook(Book(brand, bkName, author, pubDate, price))
        dbHelper.close()

        Toast.makeText(this, "도서 등록 완료", Toast.LENGTH_SHORT).show()

//        goReset(v) // 입력값 초기화
        goList(v)

    }

    fun goReset(v: View) {
        for (i in etid.indices) {
            wbaet[i]!!.setText("")
        }
    }

    fun goList(v:View) {
        val intent = Intent(this, ListBookActivity::class.java)
        startActivity(intent)
    }

}