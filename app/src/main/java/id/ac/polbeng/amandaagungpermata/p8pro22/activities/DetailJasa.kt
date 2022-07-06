package id.ac.polbeng.amandaagungpermata.p8pro22.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import id.ac.polbeng.amandaagungpermata.p8pro22.R
import id.ac.polbeng.amandaagungpermata.p8pro22.helpers.Config
import id.ac.polbeng.amandaagungpermata.p8pro22.models.Jasa
import kotlinx.android.synthetic.main.activity_detail_jasa.*

class DetailJasa : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_jasa)

        val receiveJasa = intent.getSerializableExtra(Config.EXTRA_JASA) as? Jasa

        if(receiveJasa != null){
            tvNamaJasa.setText(receiveJasa.namaJasa)
            tvPenyedia.setText(receiveJasa.namaPenyedia)
            tvKontak.setText(receiveJasa.nomorHP)
            tvDeskripsiSingkat.setText(receiveJasa.deskripsiSingkat)
            tvUraianDeskripsi.setText(receiveJasa.uraianDeskripsi)
            tvRating.text = receiveJasa.rating.toString()
            Glide.with(this)
                .load(Config.IMAGE_URL + receiveJasa.gambar)
                .into(imgJasa)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        openMain()
        return true
    }

    override fun onBackPressed() {
        moveTaskToBack(true)
        openMain()     }

    private fun openMain(){
        val intent = Intent(applicationContext, MainActivity::class.java)
        intent.putExtra(Config.EXTRA_FRAGMENT_ID, R.id.nav_beranda)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }
}