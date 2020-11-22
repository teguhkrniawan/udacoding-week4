package com.teguh.pengunjungapp.networking

import com.teguh.pengunjungapp.model.action.ActionResponse
import com.teguh.pengunjungapp.model.getdata.PengunjungResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.*

interface PengunjungService {

    // get data pengunjung
    @GET("getAllPengunjung.php")
    fun getAllPengunjung(): Call<PengunjungResponse>

    // get data by id
    @GET("getAllPengunjung.php")
    fun getPengunjungById(
        @Query("id") id: Int
    ): Call<PengunjungResponse>

    // insert data pengunjung
    @FormUrlEncoded
    @POST("storePengunjung.php")
    fun storePengunjung(
        @Field("nama_pengunjung") namaPengunjung: String,
        @Field("jenis_kelamin") jenisKelamin: String?,
        @Field("alamat") alamat: String
    ): Call<ActionResponse>

    // update data pengunjung
    @FormUrlEncoded
    @POST("updatePengunjung.php")
    fun updatePengunjung(
        @Field("id") id: String,
        @Field("nama_pengunjung") namaPengunjung: String,
        @Field("jenis_kelamin") jenisKelamin: String?,
        @Field("alamat") alamat: String
    ): Call<ActionResponse>

    // delete data pengunjung
    @FormUrlEncoded
    @POST("hapusPengunjung.php")
    fun deletePengunjung(
        @Field("id") id: String?
    ): Call<ActionResponse>

}