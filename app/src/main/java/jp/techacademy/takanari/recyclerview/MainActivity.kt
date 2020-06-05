package jp.techacademy.takanari.recyclerview

//import android.support.v7.app.AppCompatActivity
import android.media.AudioTrack
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
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
        const val NONE_NOTE = 0.0
        const val EIGHTH_NOTE = 0.125
        const val FORTH_NOTE = 0.25
        const val HALF_NOTE = 0.5
        const val WHOLE_NOTE = 1.0
    }

    // Sound生成クラス
    private var soundGenerator: DigitalSoundGenerator? = null
    // Sound再生クラス
    private var audioTrack: AudioTrack? = null

    private var audioTrack2: AudioTrack? = null

    // 譜面データ
    private val soundList0 = ArrayList<SoundDto>()//休符
    private val soundList1 = ArrayList<SoundDto>()//ド
    private val soundList2 = ArrayList<SoundDto>()//レ
    private val soundList3 = ArrayList<SoundDto>()//ミ
    private val soundList4 = ArrayList<SoundDto>()//ファ
    private val soundList5 = ArrayList<SoundDto>()//ソ
    private val soundList6 = ArrayList<SoundDto>()//ラ
    private val soundList7 = ArrayList<SoundDto>()//シ
    private val soundList8 = ArrayList<SoundDto>()//ド(高)
    private val soundList9 = ArrayList<SoundDto>()//レ(高)
    private val soundList10 = ArrayList<SoundDto>()//ミ(高)
    private val soundList11 = ArrayList<SoundDto>()//ファ(高)

    private val soundListTest = ArrayList<SoundDto>()





    private var soundtest: Boolean = false

    //タイマーが動いてる時に時間数える変数
    private var mTimer: Timer? = null
    //不明　ハンドラーを入れてる？
    private var mHandler = Handler()

//    private var flag:Boolean=false

    enum class SelectMelody {
        C, D, E,F,G,A,B,X,Y,Z,P,N
    }

    var v = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        soundGenerator = DigitalSoundGenerator(44100, 44100)

        for (i in 0 until 40) {
            soundList1.add(
                SoundDto(
                    generateSound(
                        soundGenerator!!,
                        DigitalSoundGenerator.FREQ_C,
                        HALF_NOTE
                    ), HALF_NOTE
                )
            )
            soundList2.add(
                SoundDto(
                    generateSound(
                        soundGenerator!!,
                        DigitalSoundGenerator.FREQ_D,
                        HALF_NOTE
                    ), HALF_NOTE
                )
            )
            soundList3.add(
                SoundDto(
                    generateSound(
                        soundGenerator!!,
                        DigitalSoundGenerator.FREQ_E,
                        HALF_NOTE
                    ), HALF_NOTE
                )
            )
            soundList4.add(
                SoundDto(
                    generateSound(
                        soundGenerator!!,
                        DigitalSoundGenerator.FREQ_F,
                        HALF_NOTE
                    ), HALF_NOTE
                )
            )
            soundList5.add(
                SoundDto(
                    generateSound(
                        soundGenerator!!,
                        DigitalSoundGenerator.FREQ_G,
                        HALF_NOTE
                    ), HALF_NOTE
                )
            )
            soundList6.add(
                SoundDto(
                    generateSound(
                        soundGenerator!!,
                        DigitalSoundGenerator.FREQ_A,
                        HALF_NOTE
                    ), HALF_NOTE
                )
            )
            soundList7.add(
                SoundDto(
                    generateSound(
                        soundGenerator!!,
                        DigitalSoundGenerator.FREQ_B,
                        HALF_NOTE
                    ), HALF_NOTE
                )
            )
            soundList8.add(
                SoundDto(
                    generateSound(
                        soundGenerator!!,
                        DigitalSoundGenerator.FREQ_X,
                        HALF_NOTE
                    ), HALF_NOTE
                )
            )
            soundList9.add(
                SoundDto(
                    generateSound(
                        soundGenerator!!,
                        DigitalSoundGenerator.FREQ_Y,
                        HALF_NOTE
                    ), HALF_NOTE
                )
            )
            soundList10.add(
                SoundDto(
                    generateSound(
                        soundGenerator!!,
                        DigitalSoundGenerator.FREQ_Z,
                        HALF_NOTE
                    ), HALF_NOTE
                )
            )
            soundList11.add(
                SoundDto(
                    generateSound(
                        soundGenerator!!,
                        DigitalSoundGenerator.FREQ_P,
                        HALF_NOTE
                    ), HALF_NOTE
                )
            )
        }
        soundList0.add(SoundDto(generateEmptySound(soundGenerator!!, EIGHTH_NOTE), EIGHTH_NOTE))
        // SoundGeneratorクラスをサンプルレート44100で作成

        audioTrack = soundGenerator!!.audioTrack
        audioTrack2 = soundGenerator!!.audioTrack
        startbutton.setOnClickListener(mOnstartClickListener)
        v = SelectMelody.N.toString()


//        setVolumeControlStream(AudioManager.STREAM_MUSIC)
            recyclerView1.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false) // 1
            recyclerView1.addItemDecoration(DividerItemDecoration(this, RecyclerView.VERTICAL)) // 2
            recyclerView1.adapter = MainActivityAdapter(soundList1) { item ->
                //音を鳴らすコード書くとよい
                // 再生用AudioTrackは、同じサンプルレートで初期化したものを利用する
                // start sound
//                startbutton.setOnClickListener {
//                    mTimer = Timer()


                v = SelectMelody.C.toString()

                if (item == false) {
                    val th = Thread(this)
                    th.start()
                } else {
//                    soundListTest.add(SoundDto(generateEmptySound(soundGenerator!!, EIGHTH_NOTE), EIGHTH_NOTE))
                }
            }
            recyclerView2.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false) // 1
            recyclerView2.addItemDecoration(DividerItemDecoration(this, RecyclerView.VERTICAL)) // 2
            recyclerView2.adapter = MainActivityAdapter(soundList2) { item ->
                //音を鳴らすコード書くとよい
                // 再生用AudioTrackは、同じサンプルレートで初期化したものを利用する
                // start sound
//                startbutton.setOnClickListener {
//                    mTimer = Timer()

                v = SelectMelody.D.toString()

                if (item == false) {
                    val ht = Thread(this)
                    ht.start()
                } else {
                    v = SelectMelody.N.toString()
                }
            }
            recyclerView3.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false) // 1
            recyclerView3.addItemDecoration(DividerItemDecoration(this, RecyclerView.VERTICAL)) // 2
            recyclerView3.adapter = MainActivityAdapter(soundList3) { item ->
                //音を鳴らすコード書くとよい
                // 再生用AudioTrackは、同じサンプルレートで初期化したものを利用する
                // start sound
//                startbutton.setOnClickListener {
//                    mTimer = Timer()

                v = SelectMelody.E.toString()

                if (item == false) {
                    val ht = Thread(this)
                    ht.start()
                } else {
                    v = SelectMelody.N.toString()
                }
            }
            recyclerView4.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false) // 1
            recyclerView4.addItemDecoration(DividerItemDecoration(this, RecyclerView.VERTICAL)) // 2
            recyclerView4.adapter = MainActivityAdapter(soundList4) { item ->
                //音を鳴らすコード書くとよい
                // 再生用AudioTrackは、同じサンプルレートで初期化したものを利用する
                // start sound
//                startbutton.setOnClickListener {
//                    mTimer = Timer()

                v = SelectMelody.F.toString()

                if (item == false) {
                    val ht = Thread(this)
                    ht.start()
                } else {
                }
            }
            recyclerView5.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false) // 1
            recyclerView5.addItemDecoration(DividerItemDecoration(this, RecyclerView.VERTICAL)) // 2
            recyclerView5.adapter = MainActivityAdapter(soundList5) { item ->
                //音を鳴らすコード書くとよい
                // 再生用AudioTrackは、同じサンプルレートで初期化したものを利用する
                // start sound
//                startbutton.setOnClickListener {
//                    mTimer = Timer()

                v = SelectMelody.G.toString()

                if (item == false) {
                    val ht = Thread(this)
                    ht.start()
                } else {
                }
            }
            recyclerView6.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false) // 1
            recyclerView6.addItemDecoration(DividerItemDecoration(this, RecyclerView.VERTICAL)) // 2
            recyclerView6.adapter = MainActivityAdapter(soundList6) { item ->
                //音を鳴らすコード書くとよい
                // 再生用AudioTrackは、同じサンプルレートで初期化したものを利用する
                // start sound
//                startbutton.setOnClickListener {
//                    mTimer = Timer()

                v = SelectMelody.A.toString()

                if (item == false) {
                    val ht = Thread(this)
                    ht.start()
                } else {
                }
            }
            recyclerView7.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false) // 1
            recyclerView7.addItemDecoration(DividerItemDecoration(this, RecyclerView.VERTICAL)) // 2
            recyclerView7.adapter = MainActivityAdapter(soundList7) { item ->
                //音を鳴らすコード書くとよい
                // 再生用AudioTrackは、同じサンプルレートで初期化したものを利用する
                // start sound
//                startbutton.setOnClickListener {
//                    mTimer = Timer()

                v = SelectMelody.B.toString()

                if (item == false) {
                    val ht = Thread(this)
                    ht.start()
                } else {
                }
            }
            recyclerView8.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false) // 1
            recyclerView8.addItemDecoration(DividerItemDecoration(this, RecyclerView.VERTICAL)) // 2
            recyclerView8.adapter = MainActivityAdapter(soundList8) { item ->
                //音を鳴らすコード書くとよい
                // 再生用AudioTrackは、同じサンプルレートで初期化したものを利用する
                // start sound
//                startbutton.setOnClickListener {
//                    mTimer = Timer()

                v = SelectMelody.X.toString()

                if (item == false) {
                    val ht = Thread(this)
                    ht.start()
                } else {
                }
            }
            recyclerView9.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false) // 1
            recyclerView9.addItemDecoration(DividerItemDecoration(this, RecyclerView.VERTICAL)) // 2
            recyclerView9.adapter = MainActivityAdapter(soundList9) { item ->
                //音を鳴らすコード書くとよい
                // 再生用AudioTrackは、同じサンプルレートで初期化したものを利用する
                // start sound
//                startbutton.setOnClickListener {
//                    mTimer = Timer()

                v = SelectMelody.Y.toString()

                if (item == false) {
                    val ht = Thread(this)
                    ht.start()
                } else {
                }
            }
            recyclerView10.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false) // 1
            recyclerView10.addItemDecoration(DividerItemDecoration(this, RecyclerView.VERTICAL)) // 2
            recyclerView10.adapter = MainActivityAdapter(soundList10) { item ->
                //音を鳴らすコード書くとよい
                // 再生用AudioTrackは、同じサンプルレートで初期化したものを利用する
                // start sound
//                startbutton.setOnClickListener {
//                    mTimer = Timer()

                v = SelectMelody.Z.toString()

                if (item == false) {
                    val ht = Thread(this)
                    ht.start()
                } else {
                }
            }
            recyclerView11.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false) // 1
            recyclerView11.addItemDecoration(DividerItemDecoration(this, RecyclerView.VERTICAL)) // 2
            recyclerView11.adapter = MainActivityAdapter(soundList11) { item ->
                //音を鳴らすコード書くとよい
                // 再生用AudioTrackは、同じサンプルレートで初期化したものを利用する
                // start sound
//                startbutton.setOnClickListener {
//                    mTimer = Timer()

                v = SelectMelody.P.toString()

                if (item == false) {
                    val ht = Thread(this)
                    ht.start()
                } else {
                }
            }
//        }
    }

    private fun generateSound(gen: DigitalSoundGenerator, freq: Double, length: Double): ByteArray {
        return gen.getSound(freq, length)
    }

    private fun generateEmptySound(gen: DigitalSoundGenerator, length: Double): ByteArray {
        return gen.getEmptySound(length)
    }


    override fun run() {

        // 再生中なら一旦止める
        if (audioTrack!!.playState == AudioTrack.PLAYSTATE_PLAYING) {
            audioTrack!!.stop()
            audioTrack!!.reloadStaticData()
        }
            audioTrack!!.play()

//        if (audioTrack2!!.playState == AudioTrack.PLAYSTATE_PLAYING) {
//            audioTrack2!!.stop()
//            audioTrack2!!.reloadStaticData()
//        }


            if (v == SelectMelody.N.toString()) {
                // スコアデータを書き込む
                for ((sound) in soundList0) {
                    audioTrack!!.write(sound, 0, sound.size)
                }


            } else if (v == SelectMelody.C.toString()) {
                // スコアデータを書き込む
                if (soundtest==true) {
                    for ((sound) in soundList1) {
                        audioTrack!!.write(sound, 0, sound.size)
//                    audioTrack2!!.write(sound, 0, sound.size)
                    }
                }
                else if (soundtest==false) {
                    audioTrack!!.write(soundList1[1].sound, 0, 1)

                }




            } else if (v == SelectMelody.D.toString()) {
                for ((sound) in soundList2) {
                    audioTrack!!.write(sound, 0, sound.size)
//                    audioTrack2!!.write(sound, 0, sound.size)
                }
            } else if (v == SelectMelody.E.toString()) {
                for ((sound) in soundList3) {
                    audioTrack!!.write(sound, 0, sound.size)
//                    audioTrack2!!.write(sound, 0, sound.size)
                }
            } else if (v == SelectMelody.F.toString()) {
                for ((sound) in soundList4) {
                    audioTrack!!.write(sound, 0, sound.size)
//                    audioTrack2!!.write(sound, 0, sound.size)
                }
            } else if (v == SelectMelody.G.toString()) {
                for ((sound) in soundList5) {
                    audioTrack!!.write(sound, 0, sound.size)
//                    audioTrack2!!.write(sound, 0, sound.size)
                }
            } else if (v == SelectMelody.A.toString()) {
                for ((sound) in soundList6) {
                    audioTrack!!.write(sound, 0, sound.size)
//                    audioTrack2!!.write(sound, 0, sound.size)
                }
            } else if (v == SelectMelody.B.toString()) {
                for ((sound) in soundList7) {
                    audioTrack!!.write(sound, 0, sound.size)
//                    audioTrack2!!.write(sound, 0, sound.size)
                }
            } else if (v == SelectMelody.X.toString()) {
                for ((sound) in soundList8) {
                    audioTrack!!.write(sound, 0, sound.size)
//                    audioTrack2!!.write(sound, 0, sound.size)
                }
            } else if (v == SelectMelody.Y.toString()) {
                for ((sound) in soundList9) {
                    audioTrack!!.write(sound, 0, sound.size)
//                    audioTrack2!!.write(sound, 0, sound.size)
                }
            } else if (v == SelectMelody.Z.toString()) {
                for ((sound) in soundList10) {
                    audioTrack!!.write(sound, 0, sound.size)
//                    audioTrack2!!.write(sound, 0, sound.size)
                }
            } else if (v == SelectMelody.P.toString()) {
                for ((sound) in soundList11) {
                    audioTrack!!.write(sound, 0, sound.size)
//                    audioTrack2!!.write(sound, 0, sound.size)
                }
            }


            // 再生停止
            audioTrack!!.stop()
        audioTrack2!!.stop()
        }
    private val mOnstartClickListener = View.OnClickListener {
        soundtest=true
            val ht = Thread(this)
            ht.start()
    }

}
