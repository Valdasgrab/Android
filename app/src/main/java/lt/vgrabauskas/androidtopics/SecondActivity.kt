package lt.vgrabauskas.androidtopics

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class SecondActivity : ActivityLifecycles() {

    private lateinit var idEditText: EditText
    private lateinit var text01EditText: EditText
    private lateinit var text02EditText: EditText
    private lateinit var closeButton: Button
    private lateinit var saveButton: Button
    private var finishIntentStatus = SECOND_ACTIVITY_ITEM_INTENT_RETURN_UPDATE

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        idEditText = findViewById(R.id.idEditText)
        text01EditText = findViewById(R.id.text01EditText)
        text02EditText = findViewById(R.id.text02EditText)
        closeButton = findViewById(R.id.closeButton)
        saveButton = findViewById(R.id.saveButton)


        getIntentExtra()
        setClickListenerOfCloseButton()
        setClickListenerOfSaveButton()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.run {
            putString(SECOND_ACTIVITY_ITEM_ID, idEditText.text.toString())
            putString(SECOND_ACTIVITY_ITEM_TEXT01, text01EditText.text.toString())
            putString(SECOND_ACTIVITY_ITEM_TEXT02, text02EditText.text.toString())
            putInt(SECOND_ACTIVITY_FINISH_INTENT_STATUS, finishIntentStatus)
        }
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        with(savedInstanceState) {
            idEditText.setText(getString(SECOND_ACTIVITY_ITEM_ID))
            text01EditText.setText(getString(SECOND_ACTIVITY_ITEM_TEXT01))
            text02EditText.setText(getString(SECOND_ACTIVITY_ITEM_TEXT02))
            finishIntentStatus = this.getInt(SECOND_ACTIVITY_FINISH_INTENT_STATUS)
        }
    }

    private fun getIntentExtra() {
        if (intent.hasExtra(MainActivity.MAIN_ACTIVITY_ITEM_ID)) {
            var item: Item = getExtraFromParcelable(intent)
            idEditText.setText(item.id.toString())
            text01EditText.setText(item.text01)
            text02EditText.setText(item.text02)
        } else {
            finishIntentStatus = SECOND_ACTIVITY_ITEM_INTENT_RETURN_NEW
        }
    }

    private fun getExtraFromParcelable(result: Intent?) =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            result?.getParcelableExtra(MainActivity.MAIN_ACTIVITY_ITEM_ID, Item::class.java)
                ?: Item(0, "", "")
        } else {
            result?.getParcelableExtra(MainActivity.MAIN_ACTIVITY_ITEM_ID)
                ?: Item(0, "", "")
        }

    private fun setClickListenerOfCloseButton() {
        closeButton.setOnClickListener {
            finish()
        }
    }

    private fun setClickListenerOfSaveButton() {
        saveButton.setOnClickListener {
            val finishIntent = Intent()
            finishIntent.putExtra(SECOND_ACTIVITY_ITEM_ID, (idEditText.text.toString()).toInt())
            finishIntent.putExtra(SECOND_ACTIVITY_ITEM_TEXT01, text01EditText.text.toString())
            finishIntent.putExtra(SECOND_ACTIVITY_ITEM_TEXT02, text02EditText.text.toString())
            setResult(finishIntentStatus, finishIntent)
            finish()
        }
    }

    companion object {
        const val SECOND_ACTIVITY_ITEM_ID = "package lt.vcs.androidtopics.secondactivity_item_id"
        const val SECOND_ACTIVITY_ITEM_TEXT01 =
            "package lt.vcs.androidtopics.secondactivity_item_text01"
        const val SECOND_ACTIVITY_ITEM_TEXT02 =
            "package lt.vcs.androidtopics.secondactivity_item_text02"
        const val SECOND_ACTIVITY_FINISH_INTENT_STATUS =
            "package lt.vcs.androidtopics.secondactivity_finish_intent_status"
        const val SECOND_ACTIVITY_ITEM_INTENT_RETURN_NEW = 101
        const val SECOND_ACTIVITY_ITEM_INTENT_RETURN_UPDATE = 102
    }
}
