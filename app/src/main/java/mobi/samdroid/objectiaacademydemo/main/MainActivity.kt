package mobi.samdroid.objectiaacademydemo.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import mobi.samdroid.objectiaacademydemo.R
import mobi.samdroid.objectiaacademydemo.databinding.ActivityMainBinding
import mobi.samdroid.objectiaacademydemo.main.viewmodels.MainParentViewModel
import mobi.samdroid.objectiaacademydemo.signup.SignUpActivity

class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding
    private lateinit var navController: NavController
    private val mViewModel: MainParentViewModel by viewModels()

    companion object {
        const val EXTRA_USERNAME = "username"
        const val EXTRA_PASSWORD = "password"
    }

    private lateinit var username: String
    private lateinit var password: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
    }

    override fun onStart() {
        super.onStart()
        getExtras()
        setViews()
        setListeners()
    }

    private fun getExtras() {
        username = intent.getStringExtra(EXTRA_USERNAME) ?: ""
        password = intent.getStringExtra(EXTRA_PASSWORD) ?: ""
    }

    private fun setViews() {
        mBinding.textViewWelcome.text = getString(R.string.welcome, username)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_nav_host) as NavHostFragment
        // Get the NavController
        navController = navHostFragment.navController
    }

    private fun setListeners() {
        mBinding.textViewWelcome.setOnClickListener {
            setResult(RESULT_OK)
            finish()
        }

        mBinding.imageButtonLogout.setOnClickListener {
            mViewModel.logout(this@MainActivity)

            val intent = Intent(this, SignUpActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}