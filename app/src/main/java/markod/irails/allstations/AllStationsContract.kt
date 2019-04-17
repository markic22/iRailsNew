package markod.irails.allstations

import com.example.network.mvp.MvpPresenter
import com.example.network.mvp.MvpView

class AllStationsContract{

    interface  View: MvpView {
        fun showStations(stations: List<Station>)
        fun showError(error: String)
    }

    interface Presenter: MvpPresenter<View>{
        fun loadStations()
    }
}