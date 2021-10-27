package com.ednadev.lection2

import android.graphics.Color
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.ednadev.lection2.databinding.ActivityMainBinding
import kotlinx.android.parcel.Parcelize
import java.io.Serializable
import kotlin.random.Random

class ExampleThreeActivity : AppCompatActivity() {

    lateinit var state: State

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }

        with(binding) {
            incrementBtn.setOnClickListener { increment() }
            randomColorBtn.setOnClickListener { setRandomColor() }
            visibilityBtn.setOnClickListener { switchVisibility() }
        }

        state = if (savedInstanceState == null) {
            State(
                counterValue = 0,
                counterTextColor = ContextCompat.getColor(this, R.color.purple_700),
                counterIsVisible = true
            )
        } else {
            savedInstanceState.getParcelable<State>(KEY_STATE) as State
        }

        renderState()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(KEY_STATE, state)
    }

    private fun increment() {
        state.counterValue++
        renderState()
    }

    private fun setRandomColor() {
        state.counterTextColor = Color.rgb(
            Random.nextInt(256),
            Random.nextInt(256),
            Random.nextInt(256)
        )
        renderState()
    }

    private fun switchVisibility() {
        state.counterIsVisible = !state.counterIsVisible
        renderState()
    }

    private fun renderState() = with(binding.counterTxt) {
        setText(state.counterValue.toString())
        setTextColor(state.counterTextColor)
        visibility = if (state.counterIsVisible) View.VISIBLE else View.INVISIBLE
    }

    @Parcelize
    data class State(
        var counterValue: Int,
        var counterTextColor: Int,
        var counterIsVisible: Boolean
    ) : Parcelable

    companion object {
        private val KEY_STATE = "STATE"
    }
}