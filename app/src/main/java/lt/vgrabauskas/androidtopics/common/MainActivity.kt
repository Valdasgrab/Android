package lt.vgrabauskas.androidtopics.common

import android.os.Bundle
import lt.vgrabauskas.androidtopics.R

class MainActivity : ActivityLifecyclesPresentation() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}