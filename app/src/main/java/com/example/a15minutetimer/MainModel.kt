package com.example.a15minutetimer

import androidx.lifecycle.ViewModel
import java.util.*
import kotlin.concurrent.schedule

class MainModel : ViewModel() {
    //変数
    private val lapTime = 15*60 //一ラップの長さ
    private val oneMinute = 1*60 //一分
    private val fiveMinutes = 5*60 //五分
    var nowTime =lapTime //現在の残り時間
    var timeSum =lapTime //合計の経過時間
    private var backTime = nowTime //少し前の残り時間
    private var delayTime = 0 //ミリ秒保存
    var nowLap = 0 //ラップ数
    private var stop = false //一時停止中か
    var timeZero = false //ゼロ秒か
    //フラグ管理
    private var startStop = false
    private var plusOneMin = false
    private var plusFiveMin = false
    private var reset = false
    private var nextLap = false
    var updateTime = false
    var alarmOn = false

    //タイマースタート
    fun startTimer(){
        Timer().schedule(0, 1) {
            if(!stop){
                delayTime++
                //一秒ごとに起動
                if(delayTime >= 1000) {
                    if (!timeZero) {
                        nowTime--
                    }
                    delayTime = 0
                }
            }
            if(backTime != nowTime){
                updateTime = true
                backTime = nowTime
            }
            if(!stop && !timeZero && nowTime <= 0){
                alarmOn = true
                timeZero = true
                updateTime = true
            }
        }
    }

    //タイマーリセット
    private fun reset(){
        delayTime = 0
        nowTime = lapTime
        timeSum = lapTime
    }

    //時間止めるの解除
    private fun unlockTimer() {
        timeZero = false
    }

    //ボタンが押されたとき(など)の処理
    fun onFlag(flag:String){
        when(flag){
            "startStop" -> startStop = true
            "plusOneMin" -> plusOneMin = true
            "plusFiveMin" -> plusFiveMin = true
            "reset" -> reset = true
            "nextLap" -> nextLap = true
        }
        if(startStop){
            startStop = false
            stop = when (stop) {
                true -> false
                false -> true
            }
        }
        if(plusOneMin){
            plusOneMin = false
            nowTime += oneMinute
            timeSum += oneMinute
            unlockTimer()
        }
        if(plusFiveMin){
            plusFiveMin = false
            nowTime += fiveMinutes
            timeSum += fiveMinutes
            unlockTimer()
        }
        if(reset){
            reset = false
            unlockTimer()
            reset()
        }
        if(nextLap){
            nextLap = false
            unlockTimer()
            reset()
            nowLap++
        }
    }
}