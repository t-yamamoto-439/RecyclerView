package jp.techacademy.takanari.testmelody

import android.media.AudioAttributes
import android.media.AudioFormat
import android.media.AudioTrack
import kotlin.math.ceil
import kotlin.math.sin

/**
 * ドレミファソラシドの音階を作成する
 *
 * @param sampleRate
 * @param bufferSize
 */
class DigitalSoundGenerator(
    private val sampleRate: Int,
    private val bufferSize: Int
) {

    companion object {
        // とりあえず１オクターブ分の音階を確保（半音階含む）
//        const val FREQ_A = 220.0//ラ
//        const val FREQ_As = 233.081880//ラ＃？
//        const val FREQ_B = 246.941650//シ
        const val FREQ_C = 261.625565//ド
        const val FREQ_Cs = 277.182630//ド＃？
        const val FREQ_D = 293.664767//レ
        const val FREQ_Ds = 311.126983//レ＃
        const val FREQ_E = 329.627556//ミ
        const val FREQ_F = 349.228231//ファ
        const val FREQ_Fs = 369.994227//ファ＃
        const val FREQ_G = 391.994535//ソ
        const val FREQ_Gs = 415.304697//ソ＃
        const val FREQ_A  = 440.0//ラ
        const val FREQ_As = 466.163761//ラ＃
        const val FREQ_B  = 493.883301//シ
        const val FREQ_X = 523.251//ド(高)
        const val FREQ_Y = 587.330//レ(高)
        const val FREQ_Z = 659.255//ミ
        const val FREQ_P = 698.456//ファ(高)
    }

    var audioTrack = AudioTrack.Builder()
        .setAudioAttributes(
            AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_UNKNOWN)
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build()
        )
        .setAudioFormat(
            AudioFormat.Builder()
                .setEncoding(AudioFormat.ENCODING_DEFAULT)
                .setSampleRate(sampleRate)
                .setChannelMask(AudioFormat.CHANNEL_OUT_MONO)
                .build()
        )
        .setBufferSizeInBytes(bufferSize)
        .build()

    /**
     *
     * @param frequency 鳴らしたい音の周波数
     * @param soundLength 音の長さ
     * @return 音声データ
     */
    fun getSound(frequency: Double, soundLength: Double): ByteArray {
        // byteバッファを作成
        val buffer = ByteArray(ceil(bufferSize * soundLength).toInt())
        for (i in buffer.indices) {
            var wave = i / (this.sampleRate / frequency) * (Math.PI * 2)
            wave = sin(wave)
            buffer[i] =
                (if (wave > 0.0) java.lang.Byte.MAX_VALUE else java.lang.Byte.MIN_VALUE).toByte()
        }

        return buffer
    }

    /**
     * いわゆる休符
     * @param soundLength
     * @return 無音データ
     */
    fun getEmptySound(soundLength: Double): ByteArray {
        val buff = ByteArray(ceil(bufferSize * soundLength).toInt())

        for (i in buff.indices) {
            buff[i] = 0.toByte()
        }
        return buff
    }
}