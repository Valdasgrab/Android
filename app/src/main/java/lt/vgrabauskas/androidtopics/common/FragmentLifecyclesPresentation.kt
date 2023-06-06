package lt.vgrabauskas.androidtopics.common

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

open class FragmentLifecyclesPresentation : Fragment() {

    /**
     * UPWARD STATE TRANSITIONS
     * CREATED
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logInfo("onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        logInfo("onCreateView")
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        logInfo("onViewCreated")
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        logInfo("onViewStateRestored")
    }

    /**
     * UPWARD STATE TRANSITIONS
     * STARTED
     */

    override fun onStart() {
        super.onStart()
        logInfo("onStart")
    }

    /**
     * UPWARD STATE TRANSITIONS
     * RESUME
     */

    override fun onResume() {
        super.onResume()
        logInfo("onResume")
    }

    /**
     * DOWNWARD STATE TRANSITIONS
     */

    override fun onPause() {
        super.onPause()
        logInfo("onPause")
    }

    override fun onStop() {
        super.onStop()
        logInfo("onStop")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        logInfo("onSaveInstanceState")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        logInfo("onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        logInfo("onDestroy")
    }

    fun logInfo(actionName: String, tagName: String = "my_log_msg_" + this.tag) {
        val message =
            """
                **************************
                * ${this.tag}
                * ${this.host}
                * $actionName
                **************************
            """.trimIndent()

        Log.i(tagName, message)
    }
}
