package com.example.a15minutetimer

import androidx.lifecycle.ViewModel

class MainModel : ViewModel() {
    //変数
    val lapTime = 15*60 //一ラップの長さ
    val oneMinute = 1*60 //一分
    val fiveMinutes = 5*60 //五分
    var showTime = "88:88" //現在の残り時間表示
    var nowTime =lapTime //現在の残り時間
    var backTime = nowTime //少し前の残り時間
    var delayTime = 0 //ミリ秒保存
    var showLap = "0" //ラップ数表示
    var nowLap = 0 //ラップ数
    var stop = false //一時停止中か
    var timeZero = false //ゼロ秒か
}