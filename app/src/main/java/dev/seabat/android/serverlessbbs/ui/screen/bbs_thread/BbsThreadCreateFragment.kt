package dev.seabat.android.serverlessbbs.ui.screen.bbs_thread

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import dev.seabat.android.serverlessbbs.R
import dev.seabat.android.serverlessbbs.databinding.FragmentBbsThreadCreateBinding

class BbsThreadCreateFragment : Fragment() {

    private var _binding: FragmentBbsThreadCreateBinding? = null
    private val binding get() = _binding!!
    private lateinit var bbsThreadCreateViewModel: BbsThreadCreateViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        this.bbsThreadCreateViewModel =
            ViewModelProvider(this).get(BbsThreadCreateViewModel::class.java)

        _binding = FragmentBbsThreadCreateBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val binding = FragmentBbsThreadCreateBinding.bind(view)
        binding.spinnerNewBbsThreadColor.also { spinner ->
            ArrayAdapter.createFromResource(
                this.requireContext(),
                R.array.color_palette_array,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinner.adapter = adapter
            }
            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View,
                    position: Int,
                    id: Long
                ) {
                    val spinnerParent = parent as Spinner
                    this@BbsThreadCreateFragment.bbsThreadCreateViewModel.changeBackgroundColor(spinnerParent.selectedItem as String)
                }
                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // Do nothing
                }
            }
        }

        this.bbsThreadCreateViewModel.bbsThreadColor.observe(viewLifecycleOwner) {
            binding.boxNewBbsThreadColor.setBackgroundColor(resources.getColor(it, null))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
