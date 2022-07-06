package id.ac.polbeng.amandaagungpermata.p8pro22.services

import id.ac.polbeng.amandaagungpermata.p8pro22.models.JasaResponse
import retrofit2.Call
import retrofit2.http.GET

interface JasaService {
    @GET("services")
    fun getJasa() : Call<JasaResponse>
}