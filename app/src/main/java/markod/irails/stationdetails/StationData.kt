package markod.irails.stationdetails

import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "ArrayOfObjStationData ")
class StationDataListContainer constructor(
    @field:ElementList(name = "objStationData", required = false, inline = true)
    @param:ElementList(name = "objStationData", required = false, inline = true)
    var stationArrivals: List<StationData>? = null
)

@Root(name = "objStationData", strict = false)
class StationData {
    @set:Element(name = "Servertime")
    @get:Element(name = "Servertime")
    var serverTime: String? = null
    @set:Element(name = "Traincode", required = false)
    @get:Element(name = "Traincode", required = false)
    var trainCode: String? = null
    @set:Element(name = "Querytime")
    @get:Element(name = "Querytime")
    var queryTime: String? = null
    @set:Element(name = "Origin")
    @get:Element(name = "Origin")
    var origin: String? = null
    @set:Element(name = "Stationcode")
    @get:Element(name = "Stationcode")
    var code: String?= null
    @set:Element(name = "Status")
    @get:Element(name = "Status")
    var status: String? = null
    @set:Element(name = "Destination")
    @get:Element(name = "Destination")
    var destination: String? = null
    @set:Element(name = "Destinationtime")
    @get:Element(name = "Destinationtime")
    var destinationTime: String? = null
    @set:Element(name = "Lastlocation", required = false)
    @get:Element(name = "Lastlocation", required = false)
    var lastLocation: String? = null
    @set:Element(name = "Duein")
    @get:Element(name = "Duein")
    var dueIn: String? = null
    @set:Element(name = "Exparrival")
    @get:Element(name = "Exparrival")
    var expectedArrival: String? = null
    @set:Element(name = "Expdepart")
    @get:Element(name = "Expdepart")
    var expectedDeparture: String? = null
    @set:Element(name = "Direction")
    @get:Element(name = "Direction")
    var direction: String? = null
    @set:Element(name = "Locationtype")
    @get:Element(name = "Locationtype")
    var locationType: String? = null
}