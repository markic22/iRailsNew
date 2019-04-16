package markod.irails.stationdetails

import com.example.network.mvp.MvpPresenter
import com.example.network.mvp.MvpView

class IncomingTrainsToStationContract{

    interface  View: MvpView {
        fun showIncomingTrainsToStation(incomingTrains: List<StationData>)
        fun showError(error: String)
    }

    interface Presenter: MvpPresenter<View> {
        fun getArrivingTrainsToStation(stationCode: String)
    }
}
