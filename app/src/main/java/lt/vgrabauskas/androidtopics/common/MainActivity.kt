package lt.vgrabauskas.androidtopics.common

import android.os.Bundle
import androidx.fragment.app.commit
import lt.vgrabauskas.androidtopics.R
import lt.vgrabauskas.androidtopics.first_fragment.FirstFragment

class MainActivity : ActivityLifecyclesPresentation() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.commit {
            replace(
                R.id.fragmentContainerView,
                FirstFragment.newInstance(),
                "first_fragment"
            )
            setReorderingAllowed(true)
        }
    }
}
