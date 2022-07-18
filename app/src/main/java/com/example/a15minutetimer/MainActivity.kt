package com.example.a15minutetimer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.a15minutetimer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //画面を動的に
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var nowTime = "88:88"
        //残り時間表示を動的になっているか，確認用（88:88）でＯＫ
        binding.timer.text = nowTime

        //ボタン押されたときの動作
        binding.startStop.setOnClickListener {
            nowTime = "start/stop"
            binding.timer.text = nowTime
        }
        binding.plusOneMin.setOnClickListener {
            nowTime = "plusOne"
            binding.timer.text = nowTime
        }
        binding.plusFiveMin.setOnClickListener {
            nowTime = "plusFive"
            binding.timer.text = nowTime
        }
        binding.reset.setOnClickListener {
            nowTime = "reset"
            binding.timer.text = nowTime
        }
        binding.nextLap.setOnClickListener {
            nowTime = "nextLap"
            binding.timer.text = nowTime
        }
    }
}