package com.ands.wb1weekcomponents.contentprovider

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

//не реализовано, кастом БД
class MyHelper(context: Context?): SQLiteOpenHelper(context, "database", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE ACTABLE(_id INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, MEANING TEXT)")
        db?.execSQL("INSERT INTO ACTABLE(NAME, MEANING) VALUES ('MCA', 'Master of Computer Applications')")
        db?.execSQL("INSERT INTO ACTABLE(NAME, MEANING) VALUES ('MCA', 'Bachelor of Computer Applications')")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }
}