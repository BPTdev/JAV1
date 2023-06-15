package ch.cpnv.bookmybook

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import ch.cpnv.bookmybook.ui.newbook.NewBookFragment

class DBHelper(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {

    // below is the method for creating a database by a sqlite query
    override fun onCreate(db: SQLiteDatabase) {
        val bookQuery = ("CREATE TABLE " + BOOK_TABLE_NAME + " ("
                + BOOK_ID_COL + " INTEGER PRIMARY KEY, " +
                BOOK_NAME_COL + " TEXT," +
                BOOK_DESC_COL + " TEXT," +
                BOOK_ISBN_COL + " TEXT" + ")")

        db.execSQL(bookQuery)

        val rentQuery = ("CREATE TABLE " + RENT_TABLE_NAME + " ("
                + RENT_ID_COL + " INTEGER PRIMARY KEY, " +
                RENT_NAME_COL + " TEXT," +
                RENT_DATE_START_COL + " DATE," +
                RENT_DATE_END_COL + " DATE" + ")")

        db.execSQL(rentQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        // this method is to check if table already exists
        db.execSQL("DROP TABLE IF EXISTS " + BOOK_TABLE_NAME)
        db.execSQL("DROP TABLE IF EXISTS " + RENT_TABLE_NAME)
        onCreate(db)
    }

    // This method is for adding data in our database
    fun addBook(name : String, desc : String, isbn : String ){

        val values = ContentValues()
        values.put(BOOK_NAME_COL, name)
        values.put(BOOK_DESC_COL, desc)
        values.put(BOOK_ISBN_COL, isbn)
        val db = this.writableDatabase
        db.insert(BOOK_TABLE_NAME, null, values)
        db.close()
    }

    fun getBook(): Cursor? {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM " + BOOK_TABLE_NAME, null)
    }

    fun addRent(name : String, dateStart : String, dateEnd : String ){

        val values = ContentValues()
        values.put(RENT_NAME_COL, name)
        values.put(RENT_DATE_START_COL, dateStart)
        values.put(RENT_DATE_END_COL, dateEnd)
        val db = this.writableDatabase
        db.insert(RENT_TABLE_NAME, null, values)
        db.close()
    }
    fun getRent(): Cursor? {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM " + RENT_TABLE_NAME, null)
    }

    companion object{

        private val DATABASE_NAME = "BOOKMYBOOK"
        private val DATABASE_VERSION = 1


        val BOOK_TABLE_NAME = "Books"
        val BOOK_ID_COL = "id"
        val BOOK_NAME_COL = "name"
        val BOOK_DESC_COL = "description"
        val BOOK_ISBN_COL = "isbn"


        val RENT_TABLE_NAME = "Rents"
        val RENT_ID_COL = "rent_id"
        val RENT_NAME_COL = "rent_name"
        val RENT_DATE_START_COL = "rent_date_start"
        val RENT_DATE_END_COL = "rent_date_end"
    }
}
