package markod.irails.stationdetails

import markod.irails.mvp.MvpPresenter
import markod.irails.mvp.MvpView

class IncomingTrainsToStationContract{

    interface  View: MvpView {
        fun showIncomingTrainsToStation(incomingTrains: List<StationData>)
        fun showError(error: String)
    }

    interface Presenter: MvpPresenter<View> {
        fun getArrivingTrainsToStation(stationCode: String)
    }
}
