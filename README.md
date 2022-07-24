# 15分計るアプリ

ステータス：作成開始
進捗状況：一時停止可能に
作る理由：何時間も同じことに悩みたくないから

## 作成手順

1. ファイル作成
2. 見た目の作成
3. テキストをを動的に
4. ボタンの機能を一部実装
5. タイマー機能実装（15分からカウントダウンするだけ）
6. 時間追加可能に
7. 一時停止可能に
8. リセット可能に
9. ラップ数の更新
10. 
11. 未定

### 重要ファイル役割メモ
|  ファイル名  |  役割  |
| ---- | ---- |
|  activity_main.xml  |  画面の見た目  |
|  MainActivity  |  メインのプログラム  |

### idメモ
|  id  |  役割  |
| ---- | ---- |
|  title  |  タイトルの表示  |
|  timer  |  残り時間表示  |
|  laps  |  テキスト「ラップ数」表示  |
|  lap_num  | 　ラップ数表示  |
|  start_stop  |  開始，一時停止  |
|  plus_one_min  |  一分追加  |
|  plus_five_min  |  五分追加  |
|  reset  |  リセット  |
|  next_lap  |  ラップ数追加して次へ  |


### 重要変数メモ
|  変数名  |  ファイル名  |  役割  |
| ---- | ---- | ---- |
|  binding  |  MainActivity  | activity_main.xmlを参照 |
|  showTime  |  MainActivity  | 表示する時間 |
|  nowTime  |  MainActivity  | 現在のターマーの時間(秒） |
|  stop  |  MainActivity  | 一時停止かどうか |
