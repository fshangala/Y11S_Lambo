package com.fshangala.y11slambo

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private var webView: WebView? = null
    private var slaveStatus: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        webView = findViewById(R.id.webView)
        true.also { webView!!.settings.javaScriptEnabled = it }
        slaveStatus = findViewById(R.id.slaveStatus)
        startBrowser()
    }

    private fun startBrowser(){
        val url = "https://jack9.io/d/index.html#/"
        webView!!.loadUrl(url)
        webView!!.webViewClient = object : WebViewClient(){
            override fun onPageFinished(view: WebView?, url: String?) {
                SystemClock.sleep(5000)
                view!!.evaluateJavascript("document.querySelector(\"input[name='loginName']\").value='fishing'"){
                    runOnUiThread{
                        slaveStatus!!.text = it
                    }
                }
                view!!.evaluateJavascript("document.querySelector(\"input[name='password']\").value='somebody'"){
                    runOnUiThread{
                        slaveStatus!!.text = it
                    }
                }
                runOnUiThread{
                    slaveStatus!!.text = "Loaded!"
                }
            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                runOnUiThread{
                    slaveStatus!!.text = "Loading..."
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.y11menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.preferencesBtn -> {
                openConfig()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun openConfig(){
        val intent = Intent(this,ConfigActivity::class.java)
        startActivity(intent)
    }
}