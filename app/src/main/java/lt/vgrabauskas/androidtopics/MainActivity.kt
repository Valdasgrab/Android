package lt.vgrabauskas.androidtopics

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    lateinit var button: Button


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        timber("onCreate")

        button = findViewById(R.id.button)
        button.setOnClickListener {
            setContentView(R.layout.second_activity)
           // startActivity(Intent(this, SecondActivity::class.java))

        }


    }

    override fun onStart() {
        super.onStart()
        timber("onStart")
    }

    override fun onResume() {
        super.onResume()
        timber("onResume")
    }

    override fun onStop() {
        super.onStop()
        timber("onStop")
    }

    override fun onPause() {
        super.onPause()
        timber("onPause")
    }

    override fun onDestroy() {
        super.onDestroy()
        timber("onDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        timber("onRestart")
    }

    fun timber(text: String) {

        Timber.i("""
            *****************
            * ${this.localClassName}
            * $text
            *****************
        """.trimIndent())
    }
}