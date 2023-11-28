package com.androindian.bletooth

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat

class MainActivity : AppCompatActivity() {

    var out:TextView?=null
    var button1:Button?=null
    var button2:Button?=null
    var button3:Button?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        out = findViewById(R.id.out)
        button1 = findViewById(R.id.button1)
        button2 = findViewById(R.id.button2)
        button3 = findViewById(R.id.button3)
        val mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
        if (mBluetoothAdapter == null) {
            out?.append("device not supported")
        }
        button1?.setOnClickListener {
            if (!mBluetoothAdapter!!.isEnabled) {
                val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                if (ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.BLUETOOTH_CONNECT
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return
                }
                startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT)
            }
        }
        button2?.setOnClickListener {
            if (!mBluetoothAdapter!!.isDiscovering) {
                //out.append("MAKING YOUR DEVICE DISCOVERABLE");
                Toast.makeText(
                    applicationContext, "MAKING YOUR DEVICE DISCOVERABLE",
                    Toast.LENGTH_LONG
                )
                val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE)
                startActivityForResult(enableBtIntent, REQUEST_DISCOVERABLE_BT)
            }
        }
        button3?.setOnClickListener {
            mBluetoothAdapter!!.disable()
            //out.append("TURN_OFF BLUETOOTH");
            Toast.makeText(applicationContext, "TURNING_OFF BLUETOOTH", Toast.LENGTH_LONG)
        }
    }



    companion object {
        private const val REQUEST_ENABLE_BT = 0
        private const val REQUEST_DISCOVERABLE_BT = 0
    }
}