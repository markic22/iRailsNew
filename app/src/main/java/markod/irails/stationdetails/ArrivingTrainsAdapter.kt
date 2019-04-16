package markod.irails.stationdetails

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import markod.irails.R

class ArrivingTrainsAdapter(var incomingTrains: List<StationData>)
    : RecyclerView.Adapter<ArrivingTrainsAdapter.ViewHolder>() {

    override fun getItemCount(): Int {
        return incomingTrains.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        internal val dueIn: TextView = view.findViewById(R.id.dueIn)
        internal val arrivalAt: TextView = view.findViewById(R.id.arrival)
        internal val previousStation: TextView = view.findViewById(R.id.previousStation)
        internal val directionAndLocationType: TextView = view.findViewById(R.id.directionAndLocation)
        internal val destination: TextView = view.findViewById(R.id.destination)
        internal val state: TextView = view.findViewById(R.id.status)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.arriving_train_adapter_item, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val context = viewHolder.itemView.context
        val train = incomingTrains[position]
        viewHolder.dueIn.text = "${train.dueIn} min"
        viewHolder.arrivalAt.text = train.expectedArrival
        viewHolder.previousStation.text = train.lastLocation
        viewHolder.directionAndLocationType.text = "${train.direction}/${train.locationType}"
        viewHolder.destination.text = train.destination
        viewHolder.state.text = train.status
    }

    fun updateList (incomingTrains: List<StationData>){
        this.incomingTrains = incomingTrains
    }
}