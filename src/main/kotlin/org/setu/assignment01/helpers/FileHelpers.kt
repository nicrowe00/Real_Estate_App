package org.setu.assignment01.helpers

import java.io.*

fun write( fileName: String, data: String) {

    val file = File(fileName)
    try {
        val outputStreamWriter = OutputStreamWriter(FileOutputStream(file))
        outputStreamWriter.write(data)
        outputStreamWriter.close()
    } catch (e: Exception) {
        println("Cannot read file: " + e.toString())
    }
}

fun read(fileName: String): String {
    val file = File(fileName)
    var str = ""
    try {
        val inputStreamReader = InputStreamReader(FileInputStream(file))
        if (inputStreamReader != null) {
            val bufferedReader = BufferedReader(inputStreamReader)
            val partialStr = StringBuilder()
            var done = false
            while (!done) {
                val line = bufferedReader.readLine()
                done = (line == null);
                if (line != null) partialStr.append(line);
            }
            inputStreamReader.close()
            str = partialStr.toString()
        }
    } catch (e: FileNotFoundException) {
        println("Cannot Find file: " + e.toString())
    } catch (e: IOException) {
        println("Cannot Read file: " + e.toString())
    }
    return str
}

fun exists(fileName: String): Boolean {
    val file = File(fileName)
    return file.exists()
}

