package markod.irails

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import markod.irails.allstations.AllStationsContract
import markod.irails.allstations.AllStationsPresenter
import markod.irails.allstations.Station
import markod.irails.allstations.StationsViewPagerAdapter
import javax.inject.Inject

class MainActivity : AppCompatActivity(), AllStationsContract.View {

    @Inject
    lateinit var allStationsPresenter: AllStationsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        IRailsApplication.netComponent.inject(this)
        allStationsPresenter.attachView(this)
        allStationsPresenter.loadStations()
        tryAgain.setOnClickListener {
            allStationsPresenter.loadStations()
        }

    }

    override fun showStations(stations: List<Station>) {
        val whichStationsToShow = stations.filter { it.code == "SKILL" || it.code == "ARKLW" }
        /*
        with the below list, i wanted to get all stations names that i will show, paired with the information regarding from which station to which stationS
        i want to show current trains. This is done so we can dynamicly add remove stations above and we will dynamicly generate each station with seperate page
        on tab layout. If you add 3 stations in above list instead of 2, each station fragment will display outbound trains to other 2 stations. It turned out you cannot do that with current
        api, or at least i haven't found a way.
         */
        val listOfStationsAndRelations = whichStationsToShow
            .map { station ->
                station.name to Pair<String, List<Long>>(station.code ?: "", whichStationsToShow
                    .filter { it.id != station.id }.map { it.id?.toLong() ?: -1 })
            }

        view_pager.adapter = StationsViewPagerAdapter(
            supportFragmentManager, listOfStationsAndRelations
        )
        tab_layout.setupWithViewPager(view_pager)
        view_pager.visibility = View.VISIBLE
        tab_layout.visibility = View.VISIBLE
        tryAgain.visibility = View.GONE
    }

    override fun showError(error: String) {
        view_pager.visibility = View.GONE
        tab_layout.visibility = View.GONE
        tryAgain.visibility = View.VISIBLE
        Snackbar.make(view_pager, error, Snackbar.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        allStationsPresenter.detachView()
    }
}
