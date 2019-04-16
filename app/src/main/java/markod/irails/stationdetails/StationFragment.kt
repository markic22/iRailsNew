package markod.irails.stationdetails

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.stations_fragment_layout.*
import markod.irails.BaseFragment
import markod.irails.IRailsApplication
import markod.irails.R
import javax.inject.Inject

class StationFragment : BaseFragment(), IncomingTrainsToStationContract.View {

    @Inject
    lateinit var incomingTrainsToStationPresenter: IncomingTrainsToStationPresenter

    companion object {

        const val ARGS_STATION_FROM = "fromStation"
        const val ARGS_STATION_TO = "toStation"

        fun newInstance(fromStationToStation: Pair<String, List<Long>>): StationFragment {
            val args = Bundle()
            args.putString(ARGS_STATION_FROM, fromStationToStation.first)
            args.putLongArray(ARGS_STATION_TO, fromStationToStation.second.toLongArray())
            val fragment = StationFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.stations_fragment_layout
    }

    override fun initUI() {
        recyclerView.layoutManager = LinearLayoutManager(activity)
        IRailsApplication.netComponent.inject(this)
        incomingTrainsToStationPresenter.attachView(this)

        val stationCode = arguments?.getString(ARGS_STATION_FROM, "") ?: ""

        swipeRefresh.setOnRefreshListener {
            incomingTrainsToStationPresenter.getArrivingTrainsToStation(stationCode)
        }
        incomingTrainsToStationPresenter.getArrivingTrainsToStation(arguments?.getString(ARGS_STATION_FROM, "")
                ?: "")

    }

    override fun showIncomingTrainsToStation(incomingTrains: List<StationData>) {
        if (recyclerView.adapter != null) {
            (recyclerView.adapter as ArrivingTrainsAdapter).updateList(incomingTrains)
            (recyclerView.adapter as ArrivingTrainsAdapter).notifyDataSetChanged()
        } else {
            recyclerView.adapter = ArrivingTrainsAdapter(incomingTrains)
        }
        if (incomingTrains.isEmpty()) {
            noIncomingTrains.visibility = View.VISIBLE
        } else {
            noIncomingTrains.visibility = View.GONE
        }
        swipeRefresh.isRefreshing = false
    }

    override fun onDestroy() {
        super.onDestroy()
        incomingTrainsToStationPresenter.detachView()
    }

    override fun showError(error: String) {
        Snackbar.make(recyclerView, error, Snackbar.LENGTH_LONG).show()
    }
}