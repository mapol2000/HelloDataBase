package mapol2000.hellodatabase

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MemberHelper(context: Context?) : SQLiteOpenHelper(context, dbname, null, dbversion) {

    // SQLite 헬퍼 클래스 관련 상수
    companion object {
        const val dbname = "mapol20002" // 디비명
        const val dbversion = 1
    }

    // 테이블 생성관련 함수
    override fun onCreate(sqlite: SQLiteDatabase?) {
        sqlite?.execSQL("create table if not exists member2 (uid text, pwd text)")
    }

    // 테이블을 삭제하고 다시 테이블을 만드는 함수
    override fun onUpgrade(sqlite: SQLiteDatabase?, oldVer: Int, newVer: Int) {
        sqlite?.execSQL("drop table if exists member2")
        onCreate(sqlite)
    }

    // member 테이블에 정보 저장하는 함수
    fun insertMember(uid: String, pwd: String) {
        val sqlite = readableDatabase   // 데이터 베이스 객체 가져옴
        val params = ContentValues()    // HashMap 형태로 변수이름과 값 정의

        params.put("uid", uid)
        params.put("pwd", pwd)

        sqlite.insert("member2", null, params)
    }

    // member 테이블 조회
    fun selectMember() : Cursor {
        val db = readableDatabase
        var resultSet = arrayOf("uid", "pwd") // select할 컬럼 지정
        var sortOrder = ""                    // 정렬 기준

        return db.query("member2", // 테이블명
            resultSet,
            null,       // where 절에 필요한 컬럼명
            null,   // where 절에 필요한 값
            null,       // groupby 절에 필요한 컬럼명
            null,       // having 절에 필요한 컬럼명
            sortOrder
            )
    }

    fun selectMember2() : Cursor {
        val db = readableDatabase

        return db.rawQuery("select uid, pwd from member2", null)
    }

    fun selectMember3(uid: String) : Cursor {
        val db = readableDatabase

        return db.rawQuery("select count(uid) as cnt from member2 where uid = ?", arrayOf(uid))
    }

}