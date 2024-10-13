package com.derren.deviceHistory.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import com.derren.deviceHistory.model.Device

class DeviceRepository(context: Context) {
    private val dbHelper = DeviceHistoryDatabase(context)

    // Create (Insert)
    fun insertDevice(device: Device): Long {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(Device.COLUMN_SERIAL_NUMBER, device.serialNumber)
            put(Device.COLUMN_LOCATION, device.location)
            put(Device.COLUMN_LAST_REPAIR_DATE, device.lastRepairDate)
            put(Device.COLUMN_REPAIR_CONTENT, device.repairContent)
        }
        return db.insert(DeviceHistoryDatabase.TABLE_NAME, null, values)
    }

    // Read (Select)
    fun getAllDevices(): Cursor {
        val db = dbHelper.readableDatabase
        val query = "SELECT * FROM ${DeviceHistoryDatabase.TABLE_NAME}"
        return db.rawQuery(query, null)
    }

    // Update
    fun updateDevice(device: Device): Int {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(Device.COLUMN_SERIAL_NUMBER, device.serialNumber)
            put(Device.COLUMN_LOCATION, device.location)
            put(Device.COLUMN_LAST_REPAIR_DATE, device.lastRepairDate)
            put(Device.COLUMN_REPAIR_CONTENT, device.repairContent)
        }
        return db.update(DeviceHistoryDatabase.TABLE_NAME, values, "${Device.COLUMN_ID} = ?", arrayOf(device.id.toString()))
    }

    // Delete
    fun deleteDevice(id: Long): Int {
        val db = dbHelper.writableDatabase
        return db.delete(DeviceHistoryDatabase.TABLE_NAME, "${Device.COLUMN_ID} = ?", arrayOf(id.toString()))
    }

    fun saveDevice(device: Device): Long {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(Device.COLUMN_SERIAL_NUMBER, device.serialNumber)
            put(Device.COLUMN_LOCATION, device.location)
            put(Device.COLUMN_LAST_REPAIR_DATE, device.lastRepairDate)
            put(Device.COLUMN_REPAIR_CONTENT, device.repairContent)
        }

        return if (device.id == null) {
            // Insert new record if ID is null
            db.insert(DeviceHistoryDatabase.TABLE_NAME, null, values)
        } else {
            // Update existing record if ID is not null
            db.update(DeviceHistoryDatabase.TABLE_NAME, values, "${Device.COLUMN_ID} = ?", arrayOf(device.id.toString())).toLong()
        }
    }
}
