package dev.seabat.android.serverlessbbs.ui.screen.bbs_thread

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import dev.seabat.android.serverlessbbs.databinding.FragmentBbsThreadCreateBinding

class BbsThreadCreateFragment : Fragment() {

    private var _binding: FragmentBbsThreadCreateBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val bbsThreadCreateViewModel =
            ViewModelProvider(this).get(BbsThreadCreateViewModel::class.java)

        _binding = FragmentBbsThreadCreateBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
