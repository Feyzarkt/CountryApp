package com.huawei.countryapp.view

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.huawei.countryapp.R
import com.huawei.hms.aaid.HmsInstanceId
import com.huawei.hms.ads.HwAds
import com.huawei.hms.common.ApiException

class MainActivity : AppCompatActivity() {

    companion object{
        lateinit var activity: MainActivity
    }

    private val TAG = "HUAWEI_PUSH"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        activity = this
        getToken()

        // Initialize the Petal Ads SDK.
        HwAds.init(this)
    }

    private fun getToken() {
        // Create a thread.
        object : Thread() {
            override fun run() {
                try {
                    // Obtain the app ID from the agconnect-services.json file.
                    val appId = "111061767"

                    // Set tokenScope to HCM.
                    val tokenScope = "HCM"
                    val token = HmsInstanceId.getInstance(this@MainActivity).getToken(appId, tokenScope)
                    Log.i(TAG, "get token:$token")

                    // Check whether the token is null.
                    if (!TextUtils.isEmpty(token)) {
                        sendRegTokenToServer(token)
                    }
                } catch (e: ApiException) {
                    Log.e(TAG, "get token failed, $e")
                }
            }
        }.start()
    }

    private fun sendRegTokenToServer(token: String) {
        Log.i(TAG, "sending token to server. token:$token")
    }

}