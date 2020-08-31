package br.xpi.utils

import java.util.Date
import java.text.SimpleDateFormat
import java.util.Calendar


object DataUtils {

    def getDtMovto(args: Array[String]): String = {
        var paramData = ""
        try {
            paramData = args(1).replace("-", "")
        }
        catch {
            case _: Exception =>
                paramData = getCurrentTime()
        }
        paramData
    }

    def getCurrentTime(): String = {
        val dt_curr = new SimpleDateFormat("yyyy-MM-dd")
            .format(Calendar.getInstance().getTime())
        dt_curr
    }

    def stringToDate(dtFotoRef:String = dateToString()): Date = {
        val simpleDateFormat:SimpleDateFormat = new SimpleDateFormat("dd/MM/yyyy")
        val calendar:Calendar = Calendar.getInstance()
        val date:Date = simpleDateFormat.parse(dtFotoRef)
        calendar.setTime(date)
        date
    }

    def dateToString(date: Date= Calendar.getInstance().getTime, format: String = "yyyyMMdd"): String = {
        val simpleDateFormat:SimpleDateFormat = new SimpleDateFormat(format)
        simpleDateFormat.format(date)
    }

    def getTimestamp(): String ={
        System.currentTimeMillis().toString
    }

}
