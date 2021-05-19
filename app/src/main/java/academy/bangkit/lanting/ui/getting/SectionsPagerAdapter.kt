package academy.bangkit.lanting.ui.getting

import academy.bangkit.lanting.ui.getting.first.FirstFragment
import academy.bangkit.lanting.ui.getting.second.SecondFragment
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class SectionsPagerAdapter(activity: GettingActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment = when (position) {
        0 -> FirstFragment()
        1 -> SecondFragment()
        else -> Fragment()
    }

}