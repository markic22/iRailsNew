package markod.irails.network

import io.reactivex.Observable
import markod.irails.allstations.StationListContainer
import markod.irails.stationdetails.StationDataListContainer
import retrofit2.http.GET
import retrofit2.http.Query

interface IRailApi{
    @GET("/realtime/realtime.asmx/getAllStationsXML")
    fun getStations(): Observable<StationListContainer>

    @GET("/realtime/realtime.asmx/getStationDataByCodeXML")
    fun getStationsIncomingTrains(@Query("StationCode") stationCode: String): Observable<StationDataListContainer>
}