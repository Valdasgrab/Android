package lt.vgrabauskas.androidtopics

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import lt.vgrabauskas.androidtopics.databinding.ActivitySecondBinding

class SecondActivity : ActivityLifecycles() {

    private lateinit var binding: ActivitySecondBinding
    private var finishIntentStatus = SECOND_ACTIVITY_ITEM_INTENT_RETURN_UPDATE


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_second)
        binding.item = getIntentExtra()
        binding.secondActivity = this

        getIntentExtra()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.run {
            putParcelable(SECOND_ACTIVITY_ITEM_SAVE_INSTANCE_STATE, binding.item)
            putInt(SECOND_ACTIVITY_FINISH_INTENT_STATUS, finishIntentStatus)
        }
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        with(savedInstanceState) {
            binding.item = getParcelable(SECOND_ACTIVITY_ITEM_SAVE_INSTANCE_STATE)
            finishIntentStatus = this.getInt(SECOND_ACTIVITY_FINISH_INTENT_STATUS)
        }
    }

    private fun getIntentExtra(): Item {
        if (intent.hasExtra(MainActivity.MAIN_ACTIVITY_ITEM_INTENT_OBJECT)) {
            return getExtraFromParcelable(
                intent,
                MainActivity.MAIN_ACTIVITY_ITEM_INTENT_OBJECT
            ) ?: Item(-1, "", "")
        } else if (intent.hasExtra(MainActivity.MAIN_ACTIVITY_ITEM_INTENT_ID)) {

            finishIntentStatus = SECOND_ACTIVITY_ITEM_INTENT_RETURN_NEW
            return Item(
                intent.getIntExtra(MainActivity.MAIN_ACTIVITY_ITEM_INTENT_ID, -1),
                "", ""
            )
        } else {
            finishIntentStatus = RESULT_CANCELED
            return Item(-1, "", "")
        }
    }

//    private fun getExtraFromParcelable(result: Intent?) =
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
//            result?.getParcelableExtra(MainActivity.MAIN_ACTIVITY_ITEM_INTENT_ID, Item::class.java)
//                ?: Item(0, "", "")
//        } else {
//            result?.getParcelableExtra(MainActivity.MAIN_ACTIVITY_ITEM_INTENT_ID)
//                ?: Item(0, "", "")
//        }

    fun onClickCloseButton(view: View) {
        finish()
    }

    fun onCLickSaveButton() {
            val finishIntent = Intent()

            finishIntent.putExtra(SECOND_ACTIVITY_ITEM_INTENT_RETURN_OBJECT, binding.item)

            if (binding.idEditText.text.toString().toInt() < 0) {
                finishIntentStatus = RESULT_CANCELED
            }
            setResult(finishIntentStatus, finishIntent)
        finish()
    }

    companion object {
        const val SECOND_ACTIVITY_ITEM_INTENT_RETURN_OBJECT =
            "package lt.vcs.androidtopics_item_intent_return_onject"
        const val SECOND_ACTIVITY_ITEM_ID = "package lt.vcs.androidtopics.secondactivity_item_id"
        const val SECOND_ACTIVITY_ITEM_TEXT01 =
            "package lt.vcs.androidtopics.secondactivity_item_text01"
        const val SECOND_ACTIVITY_ITEM_TEXT02 =
            "package lt.vcs.androidtopics.secondactivity_item_text02"
        const val SECOND_ACTIVITY_FINISH_INTENT_STATUS =
            "package lt.vcs.androidtopics.secondactivity_finish_intent_status"
        const val SECOND_ACTIVITY_ITEM_INTENT_RETURN_NEW = 101
        const val SECOND_ACTIVITY_ITEM_INTENT_RETURN_UPDATE = 102
        const val SECOND_ACTIVITY_ITEM_SAVE_INSTANCE_STATE =
            "package lt.vcs.androidtopics.secondactivity_save_instance_state"

    }
}
