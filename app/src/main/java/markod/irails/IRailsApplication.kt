package markod.irails

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import markod.irails.dagger.DaggerNetComponent
import markod.irails.dagger.NetComponent
import markod.irails.dagger.NetModule

class IRailsApplication : Application() {

    companion object {
        lateinit var netComponent: NetComponent
    }

    override fun onCreate() {
        super.onCreate()
        //enable vectors for api below 21
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        netComponent = DaggerNetComponent.builder().netModule(NetModule(this)).build()
    }
}
