package markod.irails.allstations

import markod.irails.mvp.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import markod.irails.network.IRailApi

class AllStationsPresenter(private val iRailApi: IRailApi) : BasePresenter<AllStationsContract.View>(), AllStationsContract.Presenter {

    override fun loadStations() {
        val subscriber = iRailApi.getStations()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe (
                        { data -> mvpView?.showStations(data.stations!!) },
                        { error ->  mvpView?.showError(error.localizedMessage)}
                )
        subscriptions.add(subscriber)
    }
}