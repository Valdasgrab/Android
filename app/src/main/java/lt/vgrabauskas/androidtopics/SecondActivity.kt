package lt.vgrabauskas.androidtopics

import android.os.Bundle

class SecondActivity : ActivityLifecycles() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        timber("onCreate")

        val id = intent.getIntExtra(MainActivity.MAIN_ACTIVITY_ITEM_ID, -1)
        val text01 = intent.getStringExtra(MainActivity.MAIN_ACTIVITY_ITEM_TEXT01)
        val text02 = intent.getStringExtra(MainActivity.MAIN_ACTIVITY_ITEM_TEXT02)
    }
}
