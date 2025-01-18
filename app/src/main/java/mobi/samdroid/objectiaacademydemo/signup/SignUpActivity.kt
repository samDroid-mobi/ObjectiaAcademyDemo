package mobi.samdroid.objectiaacademydemo.signup

import android.app.ComponentCaller
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import mobi.samdroid.objectiaacademydemo.main.MainActivity
import mobi.samdroid.objectiaacademydemo.R
import mobi.samdroid.objectiaacademydemo.databinding.ActivitySignUpBinding
import mobi.samdroid.objectiaacademydemo.base.extensions.showSnackBar

class SignUpActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivitySignUpBinding

    private val mainLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if(result.resultCode == RESULT_OK) {
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
        setListeners()
    }

    private fun setListeners() {
        mBinding.checkboxRememberMe.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                showSnackBar(mBinding.root, getString(R.string.remember_me_checked))
            } else {
                showSnackBar(mBinding.root, getString(R.string.remember_me_unchecked))
            }
        }

        mBinding.textViewSignIn.setOnClickListener {
            showSnackBar(mBinding.root, getString(R.string.sign_in_clicked))
        }

        mBinding.buttonSignUp.setOnClickListener {
            if (isInputValidated()) {
                if(isUsernameLengthValid()) {
                    val intent = Intent(this, MainActivity::class.java)
                    intent.putExtra(MainActivity.EXTRA_USERNAME, mBinding.editTextUsername.text.toString())
                    intent.putExtra(MainActivity.EXTRA_PASSWORD, mBinding.editTextPassword.text.toString())
                    mainLauncher.launch(intent)
                } else {
                    showSnackBar(mBinding.root, getString(R.string.invalid_username_length))
                }
            } else {
                showSnackBar(mBinding.root, getString(R.string.invalid_input))
            }
        }
    }

    private fun isInputValidated(): Boolean {
        return mBinding.editTextUsername.text.toString()
            .isNotEmpty() && mBinding.editTextPassword.text.toString().isNotEmpty()
    }

    private fun isUsernameLengthValid(): Boolean {
        return mBinding.editTextUsername.text.toString().length >= 3
    }
}