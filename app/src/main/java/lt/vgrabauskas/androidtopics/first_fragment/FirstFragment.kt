package lt.vgrabauskas.androidtopics.first_fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import lt.vgrabauskas.androidtopics.R
import lt.vgrabauskas.androidtopics.common.FragmentLifecyclesPresentation

class FirstFragment : FragmentLifecyclesPresentation() {


    private val viewModel: FirstFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }


    companion object {
        fun newInstance() = FirstFragment()
    }
}