package markod.irails.dagger

import android.content.SharedPreferences
import dagger.Component
import markod.irails.allstations.AllStationsPresenter
import markod.irails.MainActivity
import markod.irails.stationdetails.IncomingTrainsToStationPresenter
import markod.irails.stationdetails.StationFragment
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(modules = [(NetModule::class)])
interface NetComponent {
    val retrofit: Retrofit

    val sharedPreferences: SharedPreferences

    fun inject (mainActivity: MainActivity)
    fun inject (allStationsPresenter: AllStationsPresenter)
    fun inject (incomingTrainsToStationPresenter: IncomingTrainsToStationPresenter)
    fun inject (stationFragment: StationFragment)


}