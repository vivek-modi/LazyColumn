package com.example.lazycolumn

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.lazycolumn.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel by viewModel<MainActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViewModel()
        setContentView(binding.root)
    }

    private fun setupViewModel() {
        viewModel.requestCompleteLiveData.observe(this) { dataReturned ->
            if (dataReturned) {
                setupView()
            }
        }

        viewModel.createData()
    }

    private fun setupView() {
        binding.composeView.setContent {
            AppBarScaffold(R.string.app_name, true) {

            }
        }
    }
}