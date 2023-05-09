package lt.vgrabauskas.androidtopics

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import timber.log.Timber

open class ActivityLifecycles : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        timber("onCreate")
    }

    override fun onStart() {
        super.onStart()
        timber("onStart")
    }

    override fun onResume() {
        super.onResume()
        timber("onResume")
    }

    override fun onPause() {
        super.onPause()
        timber("onPause")
    }

    override fun onStop() {
        super.onStop()
        timber("onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        timber("onDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        timber("onRestart")
    }

    open fun timber(actionName: String) {
        Timber.i(
            """
                **************************
                * ${this.localClassName}
                * $actionName
                **************************
            """.trimIndent()
        )
    }
}
