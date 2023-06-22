package ch.cpnv.bookmybook

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import ch.cpnv.bookmybook.ui.books.Book
import ch.cpnv.bookmybook.ui.books.Rent

class DBHelper(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {

    // below is the method for creating a database by a SQLite query
    override fun onCreate(db: SQLiteDatabase) {
        val bookQuery = ("CREATE TABLE " + BOOK_TABLE_NAME + " ("
                + BOOK_ID_COL + " INTEGER PRIMARY KEY, " +
                BOOK_NAME_COL + " TEXT," +
                BOOK_DESC_COL + " TEXT," +
                BOOK_ISBN_COL + " TEXT" + ")")

        db.execSQL(bookQuery)

        val rentQuery = ("CREATE TABLE " + RENT_TABLE_NAME + " ("
                + RENT_ID_COL + " INTEGER PRIMARY KEY, " +
                RENT_CONTACT_ID_COL + " INTEGER, " +
                RENT_BOOK_ID_COL + " INTEGER," +
                RENT_DATE_END_COL + " DATE," +
                RENT_DATE_START_COL + " DATE" + ")")

        db.execSQL(rentQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        // this method is to check if the table already exists
        db.execSQL("DROP TABLE IF EXISTS $BOOK_TABLE_NAME")
        db.execSQL("DROP TABLE IF EXISTS $RENT_TABLE_NAME")
        onCreate(db)
    }

    // This method is for adding data to our database
    fun addBook(name: String, desc: String, isbn: String) {
        val values = ContentValues()
        values.put(BOOK_NAME_COL, name)
        values.put(BOOK_DESC_COL, desc)
        values.put(BOOK_ISBN_COL, isbn)
        val db = this.writableDatabase
        db.insert(BOOK_TABLE_NAME, null, values)
        db.close()
    }

    fun getBooks(): List<Book> {
        val bookList = mutableListOf<Book>()
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $BOOK_TABLE_NAME", null)

        cursor.use {
            val idIndex = it.getColumnIndexOrThrow(BOOK_ID_COL)
            val nameIndex = it.getColumnIndexOrThrow(BOOK_NAME_COL)
            val descIndex = it.getColumnIndexOrThrow(BOOK_DESC_COL)
            val isbnIndex = it.getColumnIndexOrThrow(BOOK_ISBN_COL)

            while (it.moveToNext()) {
                val id = it.getInt(idIndex)
                val name = it.getString(nameIndex)
                val description = it.getString(descIndex)
                val isbn = it.getString(isbnIndex)
                val book = Book(id, name, description, isbn)
                bookList.add(book)
            }
        }

        return bookList
    }



    fun addRent(book_id: Int,contact_id: Int, dateStart: String, dateEnd: String) {
        val values = ContentValues()
        values.put(RENT_BOOK_ID_COL, book_id)
        values.put(RENT_CONTACT_ID_COL, contact_id)
        values.put(RENT_DATE_START_COL, dateStart)
        values.put(RENT_DATE_END_COL, dateEnd)
        val db = this.writableDatabase
        db.insert(RENT_TABLE_NAME, null, values)
        db.close()
    }

    fun getRent(): List<Rent> {
        val rentList = mutableListOf<Rent>()
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $RENT_TABLE_NAME", null)

        cursor.use {
            val idIndex = it.getColumnIndexOrThrow(RENT_ID_COL)
            val bookIdIndex = it.getColumnIndexOrThrow(BOOK_ID_COL)
            val endDateIndex = it.getColumnIndexOrThrow(RENT_DATE_END_COL)
            val startDateIndex = it.getColumnIndexOrThrow(RENT_DATE_START_COL)

            while (it.moveToNext()) {
                val id = it.getInt(idIndex)
                val bookId = it.getInt(bookIdIndex)
                val endDate = it.getString(endDateIndex)
                val startDate = it.getString(startDateIndex)
                val rent = Rent(id, bookId, startDate, endDate)
                rentList.add(rent)
            }
        }

        return rentList
    }



    companion object {

        private const val DATABASE_NAME = "BOOKMYBOOK"
        private const val DATABASE_VERSION = 1

        const val BOOK_TABLE_NAME = "Books"
        const val BOOK_ID_COL = "id"
        const val BOOK_NAME_COL = "name"
        const val BOOK_DESC_COL = "description"
        const val BOOK_ISBN_COL = "isbn"

        const val RENT_TABLE_NAME = "Rents"
        const val RENT_CONTACT_ID_COL = "contact_id"
        const val RENT_ID_COL = "rent_id"
        const val RENT_BOOK_ID_COL = "rent_book_id"
        const val RENT_DATE_START_COL = "rent_date_start"
        const val RENT_DATE_END_COL = "rent_date_end"
    }
}
