package com.example.newsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_coroutines_example.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CoroutinesExampleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutines_example)

        fetchInformation()
    }

    private fun fetchInformation() {
        CoroutineScope(IO).launch {
            val info = getInformation()
            printResult(info)
        }

        CoroutineScope(IO).launch {
            val text = getOldInformation("Heelo")
            printResult(text)
        }
    }

    //retrofit request
    private suspend fun getInformation(): String {
        val result = "result 1"
        delay(2000)
        return result
    }
    //retrofit request
    private suspend fun getOldInformation(result: String): String {
        // Здесь может быть запрос в бд/ретрофит
        val oldResult = "result 2"
        delay(2000)
        return "$result $oldResult"
    }

    private suspend fun printResult(result: String) {
        withContext(Main) {
            textView.text = result
        }
    }
}