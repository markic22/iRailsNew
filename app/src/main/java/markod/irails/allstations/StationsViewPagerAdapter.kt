package markod.irails.allstations

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import markod.irails.stationdetails.StationFragment

class StationsViewPagerAdapter(fragmentManager: FragmentManager,val pages: List<Pair<String, Pair<String, List<Long>>>>)  : FragmentStatePagerAdapter(fragmentManager) {
    var pageTitles: List<String> = pages.map { it.first }

    override fun getItem(position: Int): Fragment {
         return StationFragment.newInstance(pages[position].second)
    }

    override fun getPageTitle(position: Int): CharSequence = pageTitles[position]
    override fun getCount(): Int = pageTitles.size
}