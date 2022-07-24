package com.example.a15minutetimer

import android.media.AudioAttributes
import android.media.AudioManager
import android.media.SoundPool
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.a15minutetimer.databinding.ActivityMainBinding
import java.util.*
import kotlin.concurrent.schedule

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //画面を動的に
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //オーディオ
        val audioAttributes = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_ALARM).build()
        var aSoundPool = SoundPool.Builder()
            .setMaxStreams(1).setAudioAttributes(audioAttributes).build()
        var alarmSound = aSoundPool.load(this,R.raw.alarm_made_by_me,1)
        aSoundPool.play(alarmSound, 1.0f, 1.0f, 0,0, 1.0f)
        //変数
        val lapTime = 15*60 //一ラップの長さ
        val oneMinute = 1*60 //一分
        val fiveMinutes = 5*60 //五分
        var showTime = "88:88" //現在の残り時間表示
        var nowTime =lapTime //現在の残り時間
        var delayTime = 0 //ミリ秒保存
        var showLap = "0" //ラップ数表示
        var nowLap = 0 //ラップ数
        var stop = false //一時停止中か
        //残り時間表示を動的になっているか，確認用（time =88:88, lap = 0）でＯＫ
        binding.timer.text = showTime
        binding.lapNum.text = showLap
        fun flushTime(){
            showTime = (nowTime/60).toString() + ":"
            if((nowTime%60)<10) showTime += "0" + (nowTime%60)
            else showTime += (nowTime%60)
            binding.timer.text = showTime
        }
        fun flushLap(){
            showLap = nowLap.toString()
            binding.lapNum.text = showLap
        }

        fun reset(){
            delayTime = 0
            nowTime = lapTime
            flushTime()
        }
        //一秒ごとに動作
        Timer().schedule(0, 1) {
            if(!stop){
                delayTime++
                if(delayTime >= 1000){
                    nowTime--
                    flushTime()
                    delayTime = 0
                }
            }
        }
        //ボタン押されたときの動作
        binding.startStop.setOnClickListener {
            stop = when (stop) {
                true -> false
                false -> true
            }
        }
        binding.plusOneMin.setOnClickListener {
            nowTime += oneMinute
            flushTime()
        }
        binding.plusFiveMin.setOnClickListener {
            nowTime += fiveMinutes
            flushTime()
        }
        binding.reset.setOnClickListener {
            reset()
        }
        binding.nextLap.setOnClickListener {
            reset()
            nowLap++
            flushLap()
        }
    }
}