package mobi.samdroid.objectiaacademydemo.main.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import mobi.samdroid.objectiaacademydemo.R
import mobi.samdroid.objectiaacademydemo.base.fragments.BaseFragment
import mobi.samdroid.objectiaacademydemo.base.models.ObjectiaUser
import mobi.samdroid.objectiaacademydemo.databinding.FragmentDetailsBinding
import mobi.samdroid.objectiaacademydemo.main.viewmodels.DetailsViewModel

class DetailsFragment : BaseFragment() {
    private lateinit var mBinding: FragmentDetailsBinding
    private val mViewModel: DetailsViewModel by viewModels()

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
        setObservers()

        mViewModel.getDescription()
    }

    private fun fetchArgs() {
        mViewModel.user = arguments?.getSerializable(ARG_USER) as ObjectiaUser
    }

    private fun setViews() {
        mBinding.textViewDetails.text = getString(R.string.title_details, mViewModel.user.username)
    }

    private fun setObservers() {
        mViewModel.liveDescription().observe(viewLifecycleOwner) {
            mBinding.textViewDescription.text = it
        }
    }
}