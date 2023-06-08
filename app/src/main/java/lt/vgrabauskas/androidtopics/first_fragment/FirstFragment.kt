package lt.vgrabauskas.androidtopics.first_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import lt.vgrabauskas.androidtopics.R
import lt.vgrabauskas.androidtopics.common.FragmentLifecyclesPresentation
import lt.vgrabauskas.androidtopics.common.MainActivity
import lt.vgrabauskas.androidtopics.databinding.FragmentFirstBinding
import lt.vgrabauskas.androidtopics.second_fragment.SecondFragment

class FirstFragment : FragmentLifecyclesPresentation() {


    private val viewModel: FirstFragmentViewModel by viewModels()

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onClickOpenButton()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onClickOpenButton() {
        binding.openSecondFragmentButton.setOnClickListener {
//            val mainActivity = activity as MainActivity
//            mainActivity.openFragment()
            (activity as MainActivity).openFragment(
                SecondFragment.newInstance(),
                SecondFragment.TAG
            )
        }
    }

    companion object {
        const val TAG = "first_fragment"
        fun newInstance() = FirstFragment()
    }
}