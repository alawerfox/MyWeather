package ru.avito.demo.myweather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jakewharton.threetenabp.AndroidThreeTen
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import ru.avito.demo.myweather.di.dependency
import ru.avito.demo.myweather.ui.main.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AndroidThreeTen.init(this)

        startKoin {
            androidContext(this@MainActivity)
            modules(dependency)
        }
    }
}