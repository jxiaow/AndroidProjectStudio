package cn.xwj.kotlinforandroid

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import org.jetbrains.anko.custom.async
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.longToast
import org.jetbrains.anko.uiThread
import java.util.*
import kotlin.collections.HashMap

class MainActivity : AppCompatActivity() {

    private val itemList = listOf(
            "Mon 6/23 - Sunny - 31/17",
            "Tue 6/24 - Foggy - 21/8",
            "Wed 6/25 - Cloudy - 22/17",
            "Thurs 6/26 - Sunny - 18/11"
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val forecastList = findViewById<RecyclerView>(R.id.forecast_list)
        forecastList.layoutManager = LinearLayoutManager(this)
        forecastList.adapter = ForecastListAdapter(itemList)

        doAsync {
            Request(url = "https://www.baidu.com").run()
            uiThread { longToast("Request Performed") }
        }

        val f1 = Forecast(Date(), 27.5f, "Shiny day")
        val f2 = f1.copy(temperature = 27f)

        val (date, temperature, detail) = f1

        val map = mapOf<String, String>()
        for ((key, value) in map) {
            Log.d(javaClass.simpleName, "key: $key, value: $value")
        }
    }

}
