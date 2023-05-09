package lt.vgrabauskas.androidtopics

import android.os.Bundle

class SecondActivity : ActivityLifecycles() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        timber("onCreate")
    }
}
