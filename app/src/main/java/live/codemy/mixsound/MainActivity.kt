package live.codemy.mixsound

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import live.codemy.mixsoundlib.MixSound
import live.codemy.mixsoundlib.SoundType

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClick(view: View) {
        when (view) {
            imageViewMic -> {
                MixSound.getInstance(this).recordSound()
                "imgMic clicked !! " showToast this

            }
            imageViewFast -> {
                MixSound.getInstance(this).changeSound(SoundType.Fast)
                "imgFast clicked !! " showToast this
            }
            imageViewChimpmunk -> {
                MixSound.getInstance(this).changeSound(SoundType.Chipmunk)
                "imgChimpmunk clicked !! " showToast this
            }
            imageViewSlow -> {
                MixSound.getInstance(this).changeSound(SoundType.Slow)
                "imgSlow clicked !! " showToast this
            }
            imageViewDartvader -> {
                MixSound.getInstance(this).changeSound(SoundType.DarthVader)
                "imgDartvader clicked !! " showToast this
            }

        }
    }

    override fun onStop() {

        MixSound.getInstance(this).textToSpeech.stop()
        MixSound.getInstance(this).textToSpeech.shutdown()

        super.onStop()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            MixSound.CODE_SPEECH_RECOGNIZER -> {
                data?.let {
                    val result = it.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
//                    MixSound.recordSound = result.first()
                    MixSound.recordSound = """
                                                    
                            Look
                            If you had
                            One shot
                            Or one opportunity
                            To seize everything you ever wanted
                            In one moment
                            Would you capture it
                            Or just let it slip?
                            Yo
                        
                    """.trimIndent()
                }
            }

        }

    }
}

infix fun String.showToast(context: Context) {
    Toast.makeText(context, this, Toast.LENGTH_SHORT).show()
}