package com.example.mamamo

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.text.BoringLayout

class mamamodb(context:Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    private val CREATE_TABLE_USER = ("CREATE TABLE $TABLE_USER" +
            "$USER_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
            "$USER_NAME TEXT," +
            "$USER_EMAIL TEXT," +
            "$USER_PASS TEXT);")
    private val CREATE_TABLE_ITEM = ("CREATE TABLE $TABLE_ITEM " +
            "$ITEM_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
            "$USER_ID INTEGER FOREIGN KEY REFERENCES user_tbl(user_id)," +
            "$ITEM_NAME TEXT," +
            "$ITEM_PIC BLOB," +
            "$ITEM_INPUT INTEGER," +
            "$ITEM_EXPIRY INTEGER," +
            "$ITEM_RIPENESS INTEGER," +
            "$ITEM_TYPE TEXT);")
    private val CREATE_TABLE_RECOM = ("CREATE TABLE $TABLE_RECOMMENDATION " +
            "$RECOM_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
            "$ITEM_ID INTEGER FOREIGN KEY REFERENCCES item_tbl(item_id)" +
            "$RECOM_INGREDIENTS TEXT," +
            "$RECOM_PROCEDURES TEXT);")
    private val DROP_TABLE_USER = "DROP TABLE IF EXISTS $TABLE_USER"
    private val DROP_TABLE_ITEM = "DROP TABLE IF EXISTS $TABLE_ITEM"
    private val DROP_TABLE_RECOM = "DROP TABLE IF EXISTS $TABLE_RECOMMENDATION"

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_TABLE_USER)
        db?.execSQL(CREATE_TABLE_ITEM)
        db?.execSQL(CREATE_TABLE_RECOM)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL(DROP_TABLE_USER)
        db?.execSQL(DROP_TABLE_ITEM)
        db?.execSQL(DROP_TABLE_RECOM)
    }

    fun registerUser(username: String, email: String, pass: String) {
        val db = this.writableDatabase
        val value = ContentValues()
        value.put(USER_NAME, username)
        value.put(USER_EMAIL, email)
        value.put(USER_PASS, pass)

        db.insert(TABLE_USER, null, value)
        db.close()
    }

    fun loginUser(username: String, pass: String) : Boolean{
        val columns = arrayOf(USER_ID)
        val db = this.readableDatabase
        val selection = "$USER_NAME = ? AND $USER_PASS = ?"
        val selectionArgs = arrayOf(username, pass)

        val cursor = db.query(TABLE_USER, columns, selection, selectionArgs, null, null, null)
        val cursorCount = cursor.count
        cursor.close()
        db.close()

        return cursorCount > 0
    }

    companion object {

        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "mamamo.db"
        private const val TABLE_USER = "user_tbl"
        private const val TABLE_ITEM = "item_tbl"
        private const val TABLE_RECOMMENDATION = "recom_tbl"

        private const val USER_ID = "user_id"
        private const val USER_NAME = "user_name"
        private const val USER_EMAIL = "user_email"
        private const val USER_PASS = "user_password"

        private const val ITEM_ID = "item_id"
        private const val ITEM_NAME = "item_name"
        private const val ITEM_PIC = "item_pic"
        private const val ITEM_INPUT = "item_input"
        private const val ITEM_EXPIRY = "item_expiry"
        private const val ITEM_RIPENESS = "item_ripeness"
        private const val ITEM_TYPE = "item_type"

        private const val RECOM_ID = "recom_id"
        private const val RECOM_INGREDIENTS = "recom_ingredients"
        private const val RECOM_PROCEDURES = "recom_procedures"
    }
}