package com.teguh.pengunjungapp.model.getdata

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class PengunjungResponse(

	@field:SerializedName("data")
	val data: ArrayList<DataItem>? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("isSuccess")
	val isSuccess: Boolean? = null
)

@Parcelize
data class DataItem(

	@field:SerializedName("nama_pengunjung")
	val namaPengunjung: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("jenis_kelamin")
	val jenisKelamin: String? = null,

	@field:SerializedName("alamat")
	val alamat: String? = null
): Parcelable
