package markod.irails.stationdetails

import markod.irails.mvp.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import markod.irails.network.IRailApi

class IncomingTrainsToStationPresenter(private val iRailApi: IRailApi) : BasePresenter<IncomingTrainsToStationContract.View>(), IncomingTrainsToStationContract.Presenter {

    override fun getArrivingTrainsToStation(stationCode: String) {
        val subscriber = iRailApi.getStationsIncomingTrains(stationCode)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { data ->
                            mvpView?.showIncomingTrainsToStation(data.stationArrivals?.sortedBy { it.dueIn?.toInt() }
                                    ?: emptyList())
                        },
                        { error -> mvpView?.showError(error.localizedMessage) }
                )
        subscriptions.add(subscriber)
    }

}