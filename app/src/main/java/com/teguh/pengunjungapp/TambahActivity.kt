package com.teguh.pengunjungapp

import android.os.Bundle
import android.util.Log
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.teguh.pengunjungapp.model.action.ActionResponse
import com.teguh.pengunjungapp.model.getdata.DataItem
import com.teguh.pengunjungapp.networking.ConfigNetwork
import kotlinx.android.synthetic.main.activity_tambah.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TambahActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tambah)

        var jenisKelamin: String? = null
        val dataItem = intent.getParcelableExtra<DataItem>(MainActivity.EXTRA_DATA_PENGUNJUNG)

        if (dataItem != null){
            edt_nama_pengunjung.setText(dataItem.namaPengunjung)
            edt_alamat.setText(dataItem.alamat)

            if (dataItem.jenisKelamin == "L")
                (radio_group.getChildAt(0) as RadioButton).isChecked = true
            else if (dataItem.jenisKelamin == "P")
                (radio_group.getChildAt(1) as RadioButton).isChecked = true

            btn_simpan.text = "Perbaharui"
        }

        radio_group.setOnCheckedChangeListener { radioGroup, id ->
            val radio: RadioButton = findViewById(id)
            jenisKelamin = radio.text.toString()
        }

        if (btn_simpan.text == "Simpan") {
                btn_simpan.setOnClickListener {
                    val name = edt_nama_pengunjung.text.toString()
                    val alamat = edt_alamat.text.toString()
                    addPengunjung(name, alamat, jenisKelamin)
                }
        } else if (btn_simpan.text == "Perbaharui") {
           btn_simpan.setOnClickListener {
               val id = dataItem?.id ?: ""
               val name = edt_nama_pengunjung.text.toString()
               val alamat = edt_alamat.text.toString()
               if (jenisKelamin == null){
                   jenisKelamin = dataItem?.jenisKelamin
               }
               updatePengunjung(id, name, jenisKelamin, alamat)
           }
        }
    }

    private fun addPengunjung(namaPengunjung: String, alamatPengunjung: String, jenisKelamin: String?) {
        val input = ConfigNetwork.service().storePengunjung(namaPengunjung, jenisKelamin, alamatPengunjung)
        input.enqueue(object : Callback<ActionResponse>{
            override fun onFailure(call: Call<ActionResponse>, t: Throwable) {
                Log.d(TAG, "onFailure input: " +t.message)
            }

            override fun onResponse(
                call: Call<ActionResponse>,
                response: Response<ActionResponse>
            ) {
                if (response.isSuccessful){
                    val message = response.body()?.message
                    Toast.makeText(this@TambahActivity, message, Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
        })
    }

    private fun updatePengunjung(id: String, nama: String, jenisKelamin: String?, alamat:String){
        val input = ConfigNetwork.service().updatePengunjung(id, nama, jenisKelamin, alamat)
        input.enqueue(object : Callback<ActionResponse>{
            override fun onFailure(call: Call<ActionResponse>, t: Throwable) {
                Log.d(TAG, "onFailure update: " +t.message)
            }

            override fun onResponse(
                call: Call<ActionResponse>,
                response: Response<ActionResponse>
            ) {
                if (response.isSuccessful){
                    val message = response.body()?.message
                    Toast.makeText(this@TambahActivity, message, Toast.LENGTH_SHORT).show()
                    finish()
                }
            }

        })
    }

    companion object {
        const val TAG = "TambahActivity"
    }
}