package id.afdaldev.footballmatchscheduleapp.data.network

import id.afdaldev.footballmatchscheduleapp.BuildConfig
import id.afdaldev.footballmatchscheduleapp.data.model.*
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {

    @GET("api/v1/json/${BuildConfig.TSDB_API_KEY}/lookupleague.php")
    fun getLookUpLeague(
        @Query("id") id: String
    ): Call<LookUpLeague>

    @GET("api/v1/json/${BuildConfig.TSDB_API_KEY}/eventspastleague.php")
    fun getPastEvent(
        @Query("id") id: String
    ): Call<Event>

    @GET("api/v1/json/${BuildConfig.TSDB_API_KEY}/eventsnextleague.php")
    fun getNextEvent(
        @Query("id") id: String
    ): Call<Event>

    @GET("api/v1/json/${BuildConfig.TSDB_API_KEY}/lookupevent.php")
    fun getLookUpEvent(
        @Query("id") id: String
    ): Call<Event>

    @GET("api/v1/json/${BuildConfig.TSDB_API_KEY}/searchevents.php")
    fun getSearch(
        @Query("e") e: String
    ): Call<Search>

    @GET("api/v1/json/${BuildConfig.TSDB_API_KEY}/searchteams.php")
    fun getSearchTeams(@Query("t") t: String): Call<Team>

    @GET("api/v1/json/${BuildConfig.TSDB_API_KEY}/lookup_all_teams.php")
    suspend fun getLookUpAllTeams(@Query("id") id: String): Team

    @GET("api/v1/json/${BuildConfig.TSDB_API_KEY}/lookupteam.php")
    suspend fun getLookUpTeam(@Query("id") id: String): Team

    @GET("api/v1/json/${BuildConfig.TSDB_API_KEY}/lookuptable.php")
    suspend fun getLookUpTable(@Query("l") l: String): LookUpTable

    @GET("api/v1/json/${BuildConfig.TSDB_API_KEY}/searchplayers.php")
    suspend fun getSearchPlayers(@Query("t") t: String): Player

    @GET("api/v1/json/${BuildConfig.TSDB_API_KEY}/lookupplayer.php")
    suspend fun getLookUpPlayer(@Query("id") id: String): LookUpPlayer

    companion object {
        operator fun invoke(): APIService {
            return Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(APIService::class.java)
        }
    }
}