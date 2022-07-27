package com.example.a15minutetimer

import android.media.AudioAttributes
import android.media.SoundPool
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.a15minutetimer.databinding.ActivityMainBinding
import java.util.*
import kotlin.concurrent.schedule
import androidx.activity.viewModels

class MainActivity : AppCompatActivity() {
    //MainModelのデータ見れるように
    private val viewModel by viewModels<MainModel>()
    private val model get() = viewModel

    // 表示する場所確認
    private var showTime = "88:88" //現在の残り時間表示
    private var showLap = "0" //ラップ数表示

    //オーディオ初期化
    private fun audioIni(): SoundPool? {
        val audioAttributes = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_ALARM).build()
        return SoundPool.Builder()
            .setMaxStreams(1).setAudioAttributes(audioAttributes).build()
    }

    //時間表示更新
    private fun flushTime(binding: ActivityMainBinding){
        showTime = (model.nowTime/60).toString() + ":"
        if((model.nowTime%60)<10) showTime += "0" + (model.nowTime%60)
        else showTime += (model.nowTime%60)
        binding.timer.text = showTime
    }

    //ラップ表示更新
    private fun flushLap(binding: ActivityMainBinding){
        showLap = model.nowLap.toString()
        binding.lapNum.text = showLap
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //画面を動的に
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //音源初期化＆読み込み
        val aSoundPool = audioIni()
        val alarmSound = aSoundPool?.load(this,R.raw.alarm_made_by_me,1)

        //残り時間表示を動的になっているか，確認用（time =88:88, lap = 0）でＯＫ
        binding.timer.text = showTime
        binding.lapNum.text = showLap

        //タイマースタート
        model.startTimer()

        //常に監視
        Timer().schedule(0,1){
            //残り時間更新ごとに変更
            if(model.updateTime){
                model.updateTime = false
                flushTime(binding)
            }
            //残り時間0かつ停止なしでアラーム起動
            if(model.alarmOn) alarmSound?.let {
                model.alarmOn = false
                aSoundPool.play(it, 1.0f, 1.0f, 0,0, 1.0f)
            }
        }

        //ボタン押されたときの動作
        binding.startStop.setOnClickListener {
            model.onFlag("startStop")
        }
        binding.plusOneMin.setOnClickListener {
            model.onFlag("plusOneMin")
        }
        binding.plusFiveMin.setOnClickListener {
            model.onFlag("plusFiveMin")
        }
        binding.reset.setOnClickListener {
            model.onFlag("reset")
        }
        binding.nextLap.setOnClickListener {
            model.onFlag("nextLap")
            flushLap(binding)
        }
    }
}