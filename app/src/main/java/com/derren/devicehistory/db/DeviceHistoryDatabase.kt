package com.derren.deviceHistory.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.derren.deviceHistory.model.Device

class DeviceHistoryDatabase(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        // 테이블 생성
        val createTable = "CREATE TABLE $TABLE_NAME (" +
                "${Device.COLUMN_ID} INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "${Device.COLUMN_SERIAL_NUMBER} TEXT, " +
                "${Device.COLUMN_LOCATION} TEXT, " +
                "${Device.COLUMN_LAST_REPAIR_DATE} TEXT, " +
                "${Device.COLUMN_REPAIR_CONTENT} TEXT)"
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // 기존 테이블 삭제 후 재생성
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    companion object {
        private const val DATABASE_NAME = "device_history.db"
        private const val DATABASE_VERSION = 1
        const val TABLE_NAME = "devices"
    }
}
