package com.ednadev.lection2

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.ednadev.lection2.databinding.ActivityMainBinding
import kotlin.random.Random

class ExampleOneActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }

        with(binding) {
            incrementBtn.setOnClickListener { increment() }
            randomColorBtn.setOnClickListener { setRandomColor() }
            visibilityBtn.setOnClickListener { switchVisibility() }
        }
    }

    private fun increment() {
        var counter = binding.counterTxt.text.toString().toInt()
        counter++
        binding.counterTxt.setText(counter.toString())
    }

    private fun setRandomColor() {
        val randomColor = Color.rgb(
            Random.nextInt(256),
            Random.nextInt(256),
            Random.nextInt(256)
        )
        binding.counterTxt.setTextColor(randomColor)
    }

    private fun switchVisibility() = with(binding.counterTxt) {
        visibility = if (visibility == View.VISIBLE)
            View.INVISIBLE
        else
            View.VISIBLE
    }
}