package markod.irails.allstations

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root
import org.simpleframework.xml.ElementList

@Root(name = "ArrayOfObjStation")
class StationListContainer constructor(
    @field:ElementList(name = "objStation", required = false, inline = true)
    @param:ElementList(name = "objStation", required = false, inline = true)
    var stations: List<Station>? = null
)

@Root(name = "objStation", strict = false)
class Station {
    @set:Element(name = "StationDesc")
    @get:Element(name = "StationDesc")
    var name: String = ""
    @set:Element(name = "StationAlias", required = false)
    @get:Element(name = "StationAlias", required = false)
    var alias: String? = null
    @set:Element(name = "StationLatitude")
    @get:Element(name = "StationLatitude")
    var latitude: String? = null
    @set:Element(name = "StationLongitude")
    @get:Element(name = "StationLongitude")
    var longitude: String? = null
    @set:Element(name = "StationCode")
    @get:Element(name = "StationCode")
    var code: String?= null
    @set:Element(name = "StationId")
    @get:Element(name = "StationId")
    var id: String? = null
}


