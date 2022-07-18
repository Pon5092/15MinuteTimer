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

        //残り時間表示を動的にする
        binding.timer.text = "88:88"
    }
}