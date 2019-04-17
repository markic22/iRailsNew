package markod.irails.allstations

import markod.irails.mvp.MvpPresenter
import markod.irails.mvp.MvpView

class AllStationsContract{

    interface  View: MvpView {
        fun showStations(stations: List<Station>)
        fun showError(error: String)
    }

    interface Presenter: MvpPresenter<View> {
        fun loadStations()
    }
}