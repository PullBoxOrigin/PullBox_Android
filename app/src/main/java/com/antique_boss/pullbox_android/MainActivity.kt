package com.antique_boss.pullbox_android

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.updatePadding
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.antique_boss.pullbox_android.databinding.ActivityMainBinding
import com.antique_boss.pullbox_android.viewmodel.AppViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val viewModel by lazy { ViewModelProvider(this).get(AppViewModel::class.java) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        initialize()
    }

    private fun initialize() {
        setupEdgeStyle()
        setupBottomNavigationView()
        setupObservers()
    }

    private fun setupBottomNavigationView() {
        val navController = supportFragmentManager.findFragmentById(R.id.fragment_container_view)?.findNavController()
        navController?.let {
            //TODO Jetpack Navigation <-> BottomNavigationView connect
            binding.bottomNavigationView.setupWithNavController(it)
            it.addOnDestinationChangedListener { _, destination, _ ->
                when(destination.id) {
                    com.antique_boss.portfolio.R.id.portfolioFragment -> binding.bottomNavigationView.visibility = View.VISIBLE
                    com.antique_boss.registration.R.id.registrationFragment -> binding.bottomNavigationView.visibility = View.VISIBLE
                    else -> binding.bottomNavigationView.visibility = View.GONE
                }
            }
        }
    }

    private fun setupEdgeStyle() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            WindowCompat.setDecorFitsSystemWindows(window, false)
        } else {
            binding.root.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
        }

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { view, insets ->
            view.updatePadding(
                top = insets.systemWindowInsets.top
            )
            insets
        }

    }

    private fun setupObservers() {
        viewModel.auth.observe(this) {
            val navController = supportFragmentManager.findFragmentById(R.id.fragment_container_view)?.findNavController()
            navController?.navigate(R.id.global_action_main)
        }
    }
}