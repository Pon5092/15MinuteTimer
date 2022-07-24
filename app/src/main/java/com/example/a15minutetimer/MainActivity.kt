package com.example.a15minutetimer

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

        val baseTime = 15*60
        val oneMinute = 1*60
        val fiveMites = 5*60
        var showTime = "88:88"
        var nowTime =baseTime
        var delayTime = 0
        var stop = false
        //残り時間表示を動的になっているか，確認用（88:88）でＯＫ
        binding.timer.text = showTime

        fun flushTime(){
            showTime = (nowTime/60).toString() + ":"
            if((nowTime%60)<10) showTime += "0" + (nowTime%60)
            else showTime += (nowTime%60)
            binding.timer.text = showTime
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
            nowTime += fiveMites
            flushTime()
        }
        binding.reset.setOnClickListener {
            delayTime = 0
            nowTime = baseTime
            flushTime()
        }
        binding.nextLap.setOnClickListener {
            showTime = "nextLap"
            binding.timer.text = showTime
        }
    }
}