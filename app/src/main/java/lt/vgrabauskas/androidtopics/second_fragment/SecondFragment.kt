package lt.vgrabauskas.androidtopics.second_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import lt.vgrabauskas.androidtopics.R
import lt.vgrabauskas.androidtopics.common.FragmentLifecyclesPresentation
import lt.vgrabauskas.androidtopics.databinding.FragmentFirstBinding
import lt.vgrabauskas.androidtopics.databinding.FragmentSecondBinding

class SecondFragment : FragmentLifecyclesPresentation() {


    private val viewModel: SecondFragmentViewModel by viewModels()

    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val TAG = "second_fragment"
        fun newInstance() = SecondFragment()
    }
}