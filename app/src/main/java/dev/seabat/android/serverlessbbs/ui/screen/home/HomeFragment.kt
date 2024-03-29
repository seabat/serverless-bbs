package dev.seabat.android.serverlessbbs.ui.screen.home

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import dev.seabat.android.serverlessbbs.R
import dev.seabat.android.serverlessbbs.databinding.FragmentHomeBinding
import dev.seabat.android.serverlessbbs.ui.pager.BbsThreadListFragment
import dev.seabat.android.serverlessbbs.ui.pager.BbsThreadListFragment.Companion.ARG_TYPE
import dev.seabat.android.serverlessbbs.ui.pager.BbsThreadListType

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewPageAdapter: HomeViewPagerAdapter
    private lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        this.setupViewPager(binding)

        return root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        this.setupTab(binding)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.action_settings -> {
            true
        }
        R.id.action_bss_thread_add -> {
            val bundle = Bundle()
            this.findNavController().navigate(R.id.action_navHome_to_bbsThreadCreate, bundle)
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    private fun setupViewPager(binding: FragmentHomeBinding) {
        viewPageAdapter = HomeViewPagerAdapter(this)
        viewPager = binding.pager
        viewPager.adapter = viewPageAdapter
    }

    private fun setupTab(binding: FragmentHomeBinding) {
        TabLayoutMediator(binding.tabLayout, binding.pager) { tab, position ->
            tab.text = when(BbsThreadListType.convertToListType(position).value) {
                "ALL" -> getString(R.string.tab_all)
                "UNREAD" -> getString(R.string.tab_unread)
                "FOLLOW" -> getString(R.string.tab_follow)
                else -> "UNKNOWN"
            }
        }.attach()
    }
}

class HomeViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = BbsThreadListType.size
    override fun createFragment(position: Int): Fragment {
        val fragment = BbsThreadListFragment()
        fragment.arguments = Bundle().apply {
            putSerializable(ARG_TYPE, BbsThreadListType.convertToListType(position))
        }
        return fragment
    }
}