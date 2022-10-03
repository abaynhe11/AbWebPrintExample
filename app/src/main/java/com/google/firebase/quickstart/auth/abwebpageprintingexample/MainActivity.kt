package com.google.firebase.quickstart.auth.abwebpageprintingexample

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.print.PrintAttributes
import android.print.PrintManager
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    //val myWebView : WebView = findViewById(R.id.myWebView)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        configureWebView()

    }
    @SuppressLint("SetJavaScriptEnabled")
    private fun configureWebView() {


        myWebView?.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView, request: WebResourceRequest
            ): Boolean {
                return super.shouldOverrideUrlLoading(
                    view, request)
            }
        }
        myWebView?.settings?.javaScriptEnabled = true
        myWebView?.loadUrl(
            "https://developer.android.com/google/index.html")

    }
    private fun createWebPrintJob(webView: WebView?) {

        val printManager = this
            .getSystemService(Context.PRINT_SERVICE) as PrintManager

        val printAdapter = webView?.createPrintDocumentAdapter("MyDocument")

        val jobName = getString(R.string.app_name) + " Print Test"

        printManager.print(jobName, printAdapter!!,
            PrintAttributes.Builder().build())
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_web_print, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.action_print) {
            createWebPrintJob(myWebView)
        }
        return super.onOptionsItemSelected(item)
    }

}