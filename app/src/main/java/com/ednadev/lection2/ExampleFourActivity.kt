package com.ednadev.lection2

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.ednadev.lection2.databinding.ActivityMainBinding

class ExampleFourActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    private val viewModel by viewModels<ExampleFourViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }

        with(binding) {
            incrementBtn.setOnClickListener { viewModel.increment() }
            randomColorBtn.setOnClickListener { viewModel.setRandomColor() }
            visibilityBtn.setOnClickListener { viewModel.switchVisibility() }
        }

        if (viewModel.state.value == null) {
            viewModel.initState(
                ExampleFourViewModel.State(
                    counterValue = 0,
                    counterTextColor = ContextCompat.getColor(this, R.color.purple_700),
                    counterIsVisible = true
                )
            )
        }

        viewModel.state.observe(this, Observer {
            renderState(it)
        })
    }

    private fun renderState(state: ExampleFourViewModel.State) = with(binding.counterTxt) {
        setText(state.counterValue.toString())
        setTextColor(state.counterTextColor)
        visibility = if (state.counterIsVisible) View.VISIBLE else View.INVISIBLE
    }
}