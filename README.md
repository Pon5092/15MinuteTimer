# 15分計るアプリ

ステータス：作成開始  
進捗状況：タイマーが0になった時アラームがなるように
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
10. タイマーが0になった時アラームがなるように
11. アラームが鳴った後，次へボタンで次のラップに行けるように
12. アラーム鳴ってから表示を15分経過しましたに
13. デバイスの方向変えても問題ないように
14. 未定

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