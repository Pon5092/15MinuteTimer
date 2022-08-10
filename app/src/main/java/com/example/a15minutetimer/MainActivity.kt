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
    private val mainModel get() = viewModel

    //historyText(履歴データ）の保存場所認識
    private val historyModel by viewModels<HistoryModel>()
    private val historySave get() = historyModel.historyText

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
        val time: Int = if(mainModel.timeZero) mainModel.timeSum
        else mainModel.nowTime

        showTime = (time/60).toString()
        if(mainModel.timeZero){
            binding.timer.text = showTime + "分経過"
        } else{
            showTime += ":"
            if((time%60)<10) showTime += "0" + (time%60)
            else showTime += (time%60)
            binding.timer.text = showTime
        }
    }

    //ラップ表示更新
    private fun flushLap(binding: ActivityMainBinding){
        showLap = mainModel.nowLap.toString()
        binding.lapNum.text = showLap
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //画面を動的に
        val binding = ActivityMainBinding.inflate(layoutInflater)
        //履歴画面認識＆変数適応
        val controller = HistoryController()
        //履歴画面をメイン画面に組み込み
        binding.recyclerView.adapter = controller.adapter

        setContentView(binding.root)
        //
        val test = "てすと"
        historySave.add(test)
        historySave.add(test)
        controller.setData(historySave)

        //音源初期化＆読み込み
        val aSoundPool = audioIni()
        val alarmSound = aSoundPool?.load(this,R.raw.alarm_made_by_me,1)


        //残り時間表示を動的になっているか，確認用（time =88:88, lap = 0）でＯＫ
        binding.timer.text = showTime
        binding.lapNum.text = showLap

        //タイマースタート
        mainModel.startTimer()

        //常に監視
        Timer().schedule(0,1){
            //残り時間更新ごとに変更
            if(mainModel.updateTime){
                mainModel.updateTime = false
                flushTime(binding)
            }
            //残り時間0かつ停止なしでアラーム起動
            if(mainModel.alarmOn) alarmSound?.let {
                mainModel.alarmOn = false
                aSoundPool.play(it, 1.0f, 1.0f, 0,0, 1.0f)
            }
        }

        //ボタン押されたときの動作
        binding.startStop.setOnClickListener {
            mainModel.onFlag("startStop")
        }
        binding.plusOneMin.setOnClickListener {
            mainModel.onFlag("plusOneMin")
        }
        binding.plusFiveMin.setOnClickListener {
            mainModel.onFlag("plusFiveMin")
        }
        binding.reset.setOnClickListener {
            mainModel.onFlag("reset")
        }
        binding.nextLap.setOnClickListener {
            mainModel.onFlag("nextLap")
            flushLap(binding)
        }
    }
}