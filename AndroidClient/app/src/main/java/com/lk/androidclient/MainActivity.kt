package com.lk.androidclient

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.neovisionaries.ws.client.*


class MainActivity : AppCompatActivity() {
    companion object{
        const val TAG = "MainActivity"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Create a WebSocket. The timeout value set above is used.
        val factory = WebSocketFactory().setConnectionTimeout(5000)
        val ws = factory.createSocket("ws://10.100.102.9:8080/")
        ws.addListener(webSocketAdapter)
        try {
            ws.connectAsynchronously()
        }catch (e: WebSocketException){
            Log.e(TAG, "websocket exception", e)
        }
    }

    private val webSocketAdapter = object : WebSocketAdapter(){
        override fun onConnected(websocket: WebSocket?, headers: MutableMap<String, MutableList<String>>?) {
            Log.d(TAG, "onConnected: ")
        }

        override fun onConnectError(websocket: WebSocket?, exception: WebSocketException?) {
            Log.d(TAG, "onConnectError: ")
        }

        override fun onBinaryFrame(websocket: WebSocket?, frame: WebSocketFrame?) {
            val data = frame?.payload
            data?.let { Log.d(TAG, "onBinaryFrame: test: ${it[4]}, size: ${data.size}") }
        }

        override fun onFrame(websocket: WebSocket?, frame: WebSocketFrame?) {
            Log.d(TAG, "onFrame: ")
        }

        override fun onTextFrame(websocket: WebSocket?, frame: WebSocketFrame?) {
            Log.d(TAG, "onTextFrame: ")
        }

        override fun onTextMessage(websocket: WebSocket?, text: String?) {
            Log.d(TAG, "onTextMessage: $text")
        }


    }
}