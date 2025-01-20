package mobi.samdroid.objectiaacademydemo.main.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import mobi.samdroid.objectiaacademydemo.R
import mobi.samdroid.objectiaacademydemo.base.fragments.BaseFragment
import mobi.samdroid.objectiaacademydemo.base.models.ObjectiaUser
import mobi.samdroid.objectiaacademydemo.databinding.FragmentDetailsBinding

class DetailsFragment : BaseFragment() {
    private lateinit var mBinding: FragmentDetailsBinding
    private lateinit var mUser: ObjectiaUser

    companion object {
        const val ARG_USER = "user"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentDetailsBinding.inflate(inflater)
        return mBinding.root
    }

    override fun onStart() {
        super.onStart()

        fetchArgs()
        setViews()
    }

    private fun fetchArgs() {
        mUser = arguments?.getSerializable(ARG_USER) as ObjectiaUser
    }

    private fun setViews() {
        mBinding.textViewDetails.text = getString(R.string.title_details, mUser.username)
    }
}