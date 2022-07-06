package id.ac.polbeng.amandaagungpermata.p8pro22.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import id.ac.polbeng.amandaagungpermata.p8pro22.R
import id.ac.polbeng.amandaagungpermata.p8pro22.helpers.Config

class SplashScreenAcktivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen_acktivity)
        Handler().postDelayed({
            startActivity(Intent(this, Login::class.java))
            finish()
        }, Config.SPLASH_TIME_OUT)
    }
}