package mobi.samdroid.objectiaacademydemo.signup

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import mobi.samdroid.objectiaacademydemo.R
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

        mViewModel.liveIsUserAddedToDatabase().observe(this) {
            navigateToMainScreen()
        }

        mViewModel.liveIsUsernameAvailable().observe(this) { isAvailable ->
            if (isAvailable) {
                mViewModel.saveData(
                    this@SignUpActivity,
                    isRememberMeChecked(),
                    getEnteredUsername(),
                    getEnteredPassword()
                )
                mViewModel.addUserToDatabase(this, getEnteredUsername(), getEnteredPassword())
            } else {
                showSnackBar(mBinding.root, getString(R.string.username_exists))
            }
        }

        mViewModel.liveIsUserRegistered().observe(this) { isRegistered ->
            if (isRegistered) {
                mViewModel.saveData(
                    this@SignUpActivity,
                    isRememberMeChecked(),
                    getEnteredUsername(),
                    getEnteredPassword()
                )
                navigateToMainScreen()
            } else {
                showSnackBar(mBinding.root, getString(R.string.user_not_registered))
            }
        }
    }

    private fun setListeners() {
        mBinding.textViewSignIn.setOnClickListener {
            mViewModel.checkIfUserRegistered(this, getEnteredUsername(), getEnteredPassword())
        }

        mBinding.buttonSignUp.setOnClickListener {
            if (mViewModel.isInputValidated(getEnteredUsername(), getEnteredPassword())) {
                if (mViewModel.isUsernameLengthValid(getEnteredUsername())) {
                    mViewModel.checkIfUsernameExists(this, getEnteredUsername())
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

    private fun getEnteredUsername(): String = mBinding.editTextUsername.text.toString()
    private fun getEnteredPassword(): String = mBinding.editTextPassword.text.toString()
    private fun isRememberMeChecked(): Boolean = mBinding.checkboxRememberMe.isChecked
}