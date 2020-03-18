package com.blueheartcare.coroutinebeginner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private var RESULT_1 = "RESULT_1"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    private suspend fun fakeApiRequest(){
        var result1=getResult1FromApi()
        println("debug : $result1")
        setTextOnMainThread(result1)
    }

    private suspend fun setTextOnMainThread(result1: String) {
        withContext(Main) {
            val newText = btn.text.toString() + "\n$result1"
            btn.text = newText
        }
    }

    private suspend fun getResult1FromApi(): String {
        logThread("getResult1FromApi")
        return RESULT_1
    }

    private fun logThread(methodName: String) {
        println("debug : $methodName : ${Thread.currentThread().name}")
    }

    fun Start(view: View) {

        CoroutineScope(IO).launch {
            fakeApiRequest()
        }
    }
}

