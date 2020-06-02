package jp.techacademy.takanari.recyclerview

//import android.support.v7.app.AppCompatActivity
import android.media.AudioTrack
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import jp.techacademy.takanari.testmelody.DigitalSoundGenerator
import jp.techacademy.takanari.testmelody.SoundDto
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity(),Runnable {

    companion object {
        // 音符の長さ
        const val EIGHTH_NOTE = 0.125
        const val FORTH_NOTE = 0.25
        const val HALF_NOTE = 0.5
        const val WHOLE_NOTE = 1.0
    }

    // Sound生成クラス
    private var soundGenerator: DigitalSoundGenerator? = null
    // Sound再生クラス
    private var audioTrack: AudioTrack? = null
    // 譜面データ
    private val soundList = ArrayList<SoundDto>()

    private var soundtest:Boolean=false

    //タイマーが動いてる時に時間数える変数
    private var mTimer: Timer? = null
    //不明　ハンドラーを入れてる？
    private var mHandler = Handler()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        soundGenerator = DigitalSoundGenerator(44100, 44100)

        mTimer = Timer()

        soundList.add(
            SoundDto(
                generateSound(
                    soundGenerator!!,
                    DigitalSoundGenerator.FREQ_E,
                    HALF_NOTE
                ), HALF_NOTE
            )
        )
        // SoundGeneratorクラスをサンプルレート44100で作成

        audioTrack = soundGenerator!!.audioTrack


//        setVolumeControlStream(AudioManager.STREAM_MUSIC)

        recyclerView1.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false) // 1
        recyclerView1.addItemDecoration(DividerItemDecoration(this, RecyclerView.VERTICAL)) // 2
        recyclerView1.adapter = MainActivityAdapter { item ->
            //音を鳴らすコード書くとよい
            // 再生用AudioTrackは、同じサンプルレートで初期化したものを利用する
            // start sound
            if (item == false) {
                val th = Thread(this)
                th.start()
            }else{

            }
        }
    }
    private fun generateSound(gen: DigitalSoundGenerator, freq: Double, length: Double): ByteArray {
        return gen.getSound(freq, length)
    }

    override fun run() {

        // 再生中なら一旦止める
        if (audioTrack!!.playState == AudioTrack.PLAYSTATE_PLAYING) {
            audioTrack!!.stop()
            audioTrack!!.reloadStaticData()
        }
        mHandler.post {
            // 再生開始
            audioTrack!!.play()
        }

        // スコアデータを書き込む
        for ((sound) in soundList) {
            audioTrack!!.write(sound, 0, sound.size)
        }
        // 再生停止
        audioTrack!!.stop()
    }
}