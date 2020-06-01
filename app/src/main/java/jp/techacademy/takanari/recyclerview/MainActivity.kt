package jp.techacademy.takanari.recyclerview

//import android.support.v7.app.AppCompatActivity
import android.media.AudioManager
import android.media.AudioTrack
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import jp.techacademy.takanari.testmelody.DigitalSoundGenerator
import jp.techacademy.takanari.testmelody.SoundDto
import kotlinx.android.synthetic.main.activity_main.*

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



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        soundGenerator = DigitalSoundGenerator(44100, 44100)


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


        setVolumeControlStream(AudioManager.STREAM_MUSIC)

        recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false) // 1
        recyclerView.addItemDecoration(DividerItemDecoration(this, RecyclerView.VERTICAL)) // 2
        recyclerView.adapter = MainActivityAdapter { item ->
            //音を鳴らすコード書くとよい
            // 再生用AudioTrackは、同じサンプルレートで初期化したものを利用する
            // start sound
            if (soundtest == false) {
                soundtest = true
                val th = Thread(this)
                th.start()
            }else{
                soundtest = false
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
        // 再生開始
        audioTrack!!.play()

        // スコアデータを書き込む
        for ((sound) in soundList) {
            audioTrack!!.write(sound, 0, sound.size)
        }
        // 再生停止
        audioTrack!!.stop()
    }
}