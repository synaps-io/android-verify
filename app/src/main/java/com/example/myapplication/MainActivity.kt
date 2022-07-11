package com.example.myapplication

import android.Manifest
import android.content.pm.PackageManager
import android.hardware.camera2.CameraAccessException
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import io.synaps.OnFinishListener
import io.synaps.OnReadyListener
import io.synaps.SynapsIndividualVerify


class MainActivity : AppCompatActivity() {
    private var mButton: Button? = null
    private var mWebView: SynapsIndividualVerify? = null;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mButton = findViewById<Button>(R.id.buttonPanel);
        mWebView = findViewById<SynapsIndividualVerify>(R.id.synaps);
        mButton!!.setOnClickListener(View.OnClickListener {
            try {
                mWebView!!.launch("91eec035-13beb208-083d64e2-86ec7693")
            } catch (e: CameraAccessException) {
                ActivityCompat.requestPermissions(this,
                    arrayOf<String>( Manifest.permission.CAMERA),
                    10);
            }
        })
        mWebView!!.setOnReadyListener(OnReadyListener {
            Log.d("OmarSy", "ready")
        })
        mWebView!!.setOnFinishListener(OnFinishListener {
            Log.d("OmarSy", "finish")
        })
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            10 -> if (grantResults.isNotEmpty()
                && grantResults[0] == PackageManager.PERMISSION_GRANTED
            ) {
                mWebView!!.launch("91eec035-13beb208-083d64e2-86ec7693")
                Toast.makeText(this@MainActivity, "Permission Granted!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this@MainActivity, "Permission Denied!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}