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

        var showTime = "88:88"
        var nowTime =15*60
        //残り時間表示を動的になっているか，確認用（88:88）でＯＫ
        binding.timer.text = showTime

        fun flushTime(){
            showTime = (nowTime/60).toString() + ":"
            if((nowTime%60)<10) showTime += "0" + (nowTime%60)
            else showTime += (nowTime%60)
            binding.timer.text = showTime
        }

        //一秒ごとに動作
        Timer().schedule(0, 1000) {
            nowTime--
            flushTime()
        }
        //ボタン押されたときの動作
        binding.startStop.setOnClickListener {
            showTime = "start/stop"
            binding.timer.text = showTime
        }
        binding.plusOneMin.setOnClickListener {
            nowTime += 60
            flushTime()
        }
        binding.plusFiveMin.setOnClickListener {
            nowTime += 5 * 60
            flushTime()
        }
        binding.reset.setOnClickListener {
            showTime = "reset"
            binding.timer.text = showTime
        }
        binding.nextLap.setOnClickListener {
            showTime = "nextLap"
            binding.timer.text = showTime
        }
    }
}