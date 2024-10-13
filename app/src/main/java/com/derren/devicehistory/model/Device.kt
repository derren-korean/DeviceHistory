package com.derren.deviceHistory.model

data class Device(
    val id: Long? = null,  // id는 자동 생성되므로 null로 설정
    val serialNumber: String,
    val location: String,
    val lastRepairDate: String,
    val repairContent: String
) {
    companion object {
        const val COLUMN_ID = "id"
        const val COLUMN_SERIAL_NUMBER = "serial_number"
        const val COLUMN_LOCATION = "location"
        const val COLUMN_LAST_REPAIR_DATE = "last_repair_date"
        const val COLUMN_REPAIR_CONTENT = "repair_content"
    }
}
