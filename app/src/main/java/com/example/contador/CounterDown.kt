package com.example.contador


import android.os.CountDownTimer
import android.util.Log

//class CounterDown(var segundos: Long, var loquehacealhacertick: (Long) -> Unit) {
//    private var counterState: Boolean = false
//
//    private val myCounter = object : CountDownTimer((segundos * 1000L), 1000) {
//        override fun onTick(millisUntilFinished: Long) {
//            if (counterState) loquehacealhacertick(millisUntilFinished / 1000)
//        }
//
//        override fun onFinish() {
//            counterState = false
//            Log.i("CounterDown", "Tiempo finalizado")
//        }
//    }
//
//    fun toggle() {
//        if (this.counterState) {
//            this.cancel()
//        } else {
//            this.start()
//        }
//    }
//
//    fun start() {
//        counterState = true
//        this.myCounter.start()
//    }
//
//    fun cancel() {
//        counterState = false
//        this.myCounter.cancel()
//    }
//}