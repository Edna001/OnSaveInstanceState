package com.ednadev.lection2

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.ednadev.lection2.databinding.ActivityMainBinding
import kotlin.properties.Delegates.notNull
import kotlin.random.Random

class ExampleTwoActivity : AppCompatActivity() {

    private var counterValue by notNull<Int>()
    private var counterTextColor by notNull<Int>()
    private var counterIsVisible by notNull<Boolean>()

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }

        with(binding) {
            incrementBtn.setOnClickListener { increment() }
            randomColorBtn.setOnClickListener { setRandomColor() }
            visibilityBtn.setOnClickListener { switchVisibility() }
        }

        if (savedInstanceState == null) {
            counterValue = 0
            counterTextColor = ContextCompat.getColor(this, R.color.purple_700)
            counterIsVisible = true
        } else {
            with(savedInstanceState) {
                counterValue = getInt(KEY_COUNTER)
                counterTextColor = getInt(KEY_COLOR)
                counterIsVisible = getBoolean(KEY_IS_VISIBLE)
            }
        }
        renderState()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        with(outState) {
            putInt(KEY_COUNTER, counterValue)
            putInt(KEY_COLOR, counterTextColor)
            putBoolean(KEY_IS_VISIBLE, counterIsVisible)
        }
    }

    private fun increment() {
        counterValue++
        renderState()
    }

    private fun setRandomColor() {
        counterTextColor = Color.rgb(
            Random.nextInt(256),
            Random.nextInt(256),
            Random.nextInt(256)
        )
        renderState()
    }

    private fun switchVisibility() {
        counterIsVisible = !counterIsVisible
        renderState()
    }

    private fun renderState() = with(binding.counterTxt) {
        setText(counterValue.toString())
        setTextColor(counterTextColor)
        visibility = if (counterIsVisible) View.VISIBLE else View.INVISIBLE
    }

    companion object {
        private val KEY_COUNTER = "COUNTER"
        private val KEY_COLOR = "COLOR"
        private val KEY_IS_VISIBLE = "IS_VISIBLE"
    }
}