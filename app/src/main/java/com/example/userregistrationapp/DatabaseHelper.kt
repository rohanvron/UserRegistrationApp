package com.example.userregistrationapp

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = ("CREATE TABLE $TABLE_NAME ("
                + "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "$COLUMN_NAME TEXT,"
                + "$COLUMN_DOB TEXT,"
                + "$COLUMN_EMAIL TEXT)")
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun addUser(user: UserData): Long {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COLUMN_NAME, user.name)
        values.put(COLUMN_DOB, user.dob)
        values.put(COLUMN_EMAIL, user.email)

        return db.insert(TABLE_NAME, null, values)
    }

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "UserDatabase"
        const val TABLE_NAME = "Users"
        const val COLUMN_ID = "id"
        const val COLUMN_NAME = "name"
        const val COLUMN_DOB = "dob"
        const val COLUMN_EMAIL = "email"
    }
}
