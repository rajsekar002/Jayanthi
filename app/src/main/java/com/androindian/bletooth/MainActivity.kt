package com.androindian.bletooth

import android.bluetooth.BluetoothAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val out = findViewById<View>(R.id.out) as TextView
        val button1 = findViewById<View>(R.id.button1) as Button
        val button2 = findViewById<View>(R.id.button2) as Button
        val button3 = findViewById<View>(R.id.button3) as Button
        val mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
        if (mBluetoothAdapter == null) {
            out.append("device not supported")
        }
        button1.setOnClickListener {
            if (!mBluetoothAdapter!!.isEnabled) {
                val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT)
            }
        }
        button2.setOnClickListener {
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
        button3.setOnClickListener {
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