package mobi.samdroid.objectiaacademydemo.signup

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import mobi.samdroid.objectiaacademydemo.R
import mobi.samdroid.objectiaacademydemo.base.DataStoreManager
import mobi.samdroid.objectiaacademydemo.base.extensions.showSnackBar
import mobi.samdroid.objectiaacademydemo.databinding.ActivitySignUpBinding
import mobi.samdroid.objectiaacademydemo.main.MainActivity
import mobi.samdroid.objectiaacademydemo.signup.viewmodels.SignUpViewModel

class SignUpActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivitySignUpBinding
    private val mViewModel: SignUpViewModel by viewModels()

    private val mainLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                showSnackBar(mBinding.root, getString(R.string.sign_up_successful))
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
    }

    override fun onStart() {
        super.onStart()

        mViewModel.getSavedData(this@SignUpActivity)

        setObservers()
        setListeners()
    }

    private fun setObservers() {
        mViewModel.liveUsername().observe(this) { username ->
            mBinding.editTextUsername.setText(username)
        }

        mViewModel.livePassword().observe(this) { password ->
            mBinding.editTextPassword.setText(password)
        }

        mViewModel.liveRememberMe().observe(this) { isRememberMe ->
            mBinding.checkboxRememberMe.isChecked = isRememberMe
        }

        mViewModel.liveLoggedIn().observe(this) { isLoggedIn ->
            if (isLoggedIn) {
                navigateToMainScreen()
            }
        }
    }

    private fun setListeners() {
        mBinding.textViewSignIn.setOnClickListener {
            showSnackBar(mBinding.root, getString(R.string.sign_in_clicked))
        }

        mBinding.buttonSignUp.setOnClickListener {
            val username = mBinding.editTextUsername.text.toString()
            val password = mBinding.editTextPassword.text.toString()
            val isRememberMe = mBinding.checkboxRememberMe.isChecked

            if (mViewModel.isInputValidated(username, password)) {
                if (mViewModel.isUsernameLengthValid(username)) {
                    mViewModel.saveData(this@SignUpActivity, isRememberMe, username, password)
                    navigateToMainScreen()
                } else {
                    showSnackBar(mBinding.root, getString(R.string.invalid_username_length))
                }
            } else {
                showSnackBar(mBinding.root, getString(R.string.invalid_input))
            }
        }
    }

    private fun navigateToMainScreen() {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra(
            MainActivity.EXTRA_USERNAME,
            mBinding.editTextUsername.text.toString()
        )
        intent.putExtra(
            MainActivity.EXTRA_PASSWORD,
            mBinding.editTextPassword.text.toString()
        )
        mainLauncher.launch(intent)
    }
}