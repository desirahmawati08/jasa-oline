package id.ac.polbeng.amandaagungpermata.p8pro22.services

import id.ac.polbeng.amandaagungpermata.p8pro22.models.DefaultResponse
import id.ac.polbeng.amandaagungpermata.p8pro22.models.LoginResponse
import id.ac.polbeng.amandaagungpermata.p8pro22.models.User
import retrofit2.Call
import retrofit2.http.*

interface UserServices {
    @GET("login")
    fun loginUser(
        @QueryMap filter: HashMap<String, String>
    ): Call<LoginResponse>

    @POST("users")
    fun registerUser(
        @Body newUser: User
    ): Call<DefaultResponse>

    @PUT("users")
    fun updateUser(
        @Body updatedUser: User
    ): Call<DefaultResponse>

    @DELETE("users/{id}")
    fun deleteUser(
        @Path("id") id: Int
    ): Call<DefaultResponse>
}