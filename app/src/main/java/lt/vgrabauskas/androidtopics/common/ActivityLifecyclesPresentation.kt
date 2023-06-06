package lt.vgrabauskas.androidtopics.common

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity


open class ActivityLifecyclesPresentation : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logInfo("onCreate")
    }

    override fun onStart() {
        super.onStart()
        logInfo("onStart")
    }

    override fun onResume() {
        super.onResume()
        logInfo("onResume")
    }

    override fun onPause() {
        super.onPause()
        logInfo("onPause")
    }

    override fun onStop() {
        super.onStop()
        logInfo("onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        logInfo("onDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        logInfo("onRestart")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        logInfo("onSaveInstanceState")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        logInfo("onRestoreInstanceState")
    }


    fun logInfo(actionName: String, tagName: String = "my_log_msg_" + this.localClassName) {
        val message =
            """
                **************************
                * ${this.localClassName}
                * $actionName
                **************************
            """.trimIndent()

        Log.i(tagName, message)
    }
}
