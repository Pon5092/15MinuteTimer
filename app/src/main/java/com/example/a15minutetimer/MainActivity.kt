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
    //オーディオ初期化
    private fun audioIni(): SoundPool? {
        val audioAttributes = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_ALARM).build()
        return SoundPool.Builder()
            .setMaxStreams(1).setAudioAttributes(audioAttributes).build()
    }

    //時間止めるの解除
    private fun unlockTimer(): Boolean {
        return false
    }

    private val viewModel by viewModels<MainModel>()
    private val vars get() = viewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        //画面を動的に
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //音源初期化＆読み込み
        val aSoundPool = audioIni()
        val alarmSound = aSoundPool?.load(this,R.raw.alarm_made_by_me,1)

        //残り時間表示を動的になっているか，確認用（time =88:88, lap = 0）でＯＫ
        binding.timer.text = vars.showTime
        binding.lapNum.text = vars.showLap
        fun flushTime(){
            vars.showTime = (vars.nowTime/60).toString() + ":"
            if((vars.nowTime%60)<10) vars.showTime += "0" + (vars.nowTime%60)
            else vars.showTime += (vars.nowTime%60)
            binding.timer.text = vars.showTime
        }
        fun flushLap(){
            vars.showLap = vars.nowLap.toString()
            binding.lapNum.text = vars.showLap
        }

        fun reset(){
            vars.delayTime = 0
            vars.nowTime = vars.lapTime
            flushTime()
        }
        //一秒ごとに動作
        Timer().schedule(0, 1) {
            if(!vars.stop){
                vars.delayTime++
                if(vars.delayTime >= 1000){
                    if(!vars.timeZero) {
                        vars.nowTime--
                        flushTime()
                    }
                    vars.delayTime = 0
                }
                if(!vars.timeZero && vars.nowTime <= 0) alarmSound?.let {
                    vars.timeZero = true
                    aSoundPool.play(it, 1.0f, 1.0f, 0,0, 1.0f)
                }
            }
        }
        //ボタン押されたときの動作
        binding.startStop.setOnClickListener {
            vars.stop = when (vars.stop) {
                true -> false
                false -> true
            }
        }
        binding.plusOneMin.setOnClickListener {
            vars.nowTime += vars.oneMinute
            vars.timeZero =unlockTimer()
            flushTime()
        }
        binding.plusFiveMin.setOnClickListener {
            vars.nowTime += vars.fiveMinutes
            vars.timeZero =unlockTimer()
            flushTime()
        }
        binding.reset.setOnClickListener {
            vars.timeZero =unlockTimer()
            reset()
        }
        binding.nextLap.setOnClickListener {
            vars.timeZero =unlockTimer()
            reset()
            vars.nowLap++
            flushLap()
        }
    }
}