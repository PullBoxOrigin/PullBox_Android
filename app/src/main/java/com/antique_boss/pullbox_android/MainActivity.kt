package com.antique_boss.pullbox_android

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.updatePadding
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.antique_boss.pullbox_android.databinding.ActivityMainBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val googleSignInLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        initialize()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        initialize()
    }

    private fun initialize() {
        if(Firebase.auth.currentUser != null) {
            setupNavigationGraph()
            setupEdgeStyle()
            setupBottomNavigationView()
        } else {
            googleSignInLauncher.launch(Intent(this, GoogleSignInActivity::class.java))
        }
    }

    private fun setupNavigationGraph() {
        val navController = supportFragmentManager.findFragmentById(R.id.fragment_container_view)?.findNavController()
        navController?.let {
            val navGraph = it.navInflater.inflate(R.navigation.app_nav_graph)
            navGraph.setStartDestination(R.id.portfolio_nav_graph)
            navController.setGraph(navGraph, null)
        }
    }

    private fun setupBottomNavigationView() {
        val navController = supportFragmentManager.findFragmentById(R.id.fragment_container_view)?.findNavController()
        navController?.let {
            //TODO Jetpack Navigation <-> BottomNavigationView connect
            binding.bottomNavigationView.setupWithNavController(it)
            it.addOnDestinationChangedListener { _, destination, _ ->
                when (destination.id) {
                    com.antique_boss.portfolio.R.id.portfolioFragment -> binding.bottomNavigationView.visibility = View.VISIBLE
                    com.antique_boss.registration.R.id.registrationFragment -> binding.bottomNavigationView.visibility = View.VISIBLE
                    com.antique_boss.news.R.id.newsFragment -> binding.bottomNavigationView.visibility = View.VISIBLE
                    com.antique_boss.settings.R.id.settingsFragment -> binding.bottomNavigationView.visibility = View.VISIBLE

                    else -> binding.bottomNavigationView.visibility = View.GONE
                }
            }
        }
    }
    /*
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
                top = insets.systemWindowInsets.top,
                bottom = insets.systemWindowInsets.bottom
            )
            insets
        }
    }

     */

    private fun setupEdgeStyle() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            WindowCompat.setDecorFitsSystemWindows(window, false)
        } else {
            binding.root.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
        }

        binding.bottomNavigationView.setOnApplyWindowInsetsListener(null)
        binding.bottomNavigationView.setPadding(0, 0, 0, 0)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { view, insets ->
            view.updatePadding(
                top = insets.systemWindowInsets.top,
                bottom = insets.systemWindowInsets.bottom
            )
            insets
        }
    }
}