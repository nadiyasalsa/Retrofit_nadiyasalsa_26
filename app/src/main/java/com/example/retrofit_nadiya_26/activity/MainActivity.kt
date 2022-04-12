package com.example.retrofit_nadiya_26.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.retrofit_nadiya_26.R
import com.example.retrofit_nadiya_26.api.RetrofitClient
import com.example.retrofit_nadiya_26.model.IndonesiaResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showIndonesia()

        btnProvince.setOnClickListener {
            Intent(this@MainActivity, ProvinceActivity::class.java).also {
                startActivity(it)
            }
        }
    }

    private fun showIndonesia(){
        RetrofitClient.instance.getIndonesia().enqueue(object :
            Callback<ArrayList<IndonesiaResponse>>{
            override fun onFailure(call: Call<ArrayList<IndonesiaResponse>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "${t.message}", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<ArrayList<IndonesiaResponse>>,
                response: Response<ArrayList<IndonesiaResponse>>
            ) {
                val indonesia = response.body()?.get(0)
                val positive = indonesia?.positif
                val hospitilized = indonesia?.dirawat
                val recover = indonesia?.sembuh
                val death = indonesia?.meninggal

                tvPositive.text = positive
                tvHospitalized.text = hospitilized
                tvRecover.text = recover
                tvDeath.text = death
            }
        })
    }
}