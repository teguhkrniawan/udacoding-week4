package com.teguh.pengunjungapp.networking

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class ConfigNetwork {

    companion object {
        private fun getRetrofit(): Retrofit {
            return Retrofit.Builder()
                .baseUrl("http://192.168.43.58/weekfinal/")
                //.baseUrl("http://10.0.2.2/weekfinal/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        fun service(): PengunjungService {
            return getRetrofit().create(PengunjungService::class.java)
        }

    }
}