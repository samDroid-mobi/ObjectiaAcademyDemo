package mobi.samdroid.objectiaacademydemo.main.fragments

import android.Manifest
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import mobi.samdroid.objectiaacademydemo.R
import mobi.samdroid.objectiaacademydemo.base.extensions.ObjectiaUtils
import mobi.samdroid.objectiaacademydemo.base.fragments.BaseFragment
import mobi.samdroid.objectiaacademydemo.base.interfaces.IClickListener
import mobi.samdroid.objectiaacademydemo.base.models.ObjectiaUser
import mobi.samdroid.objectiaacademydemo.databinding.FragmentMainBinding
import mobi.samdroid.objectiaacademydemo.main.adapters.UsersAdapter
import mobi.samdroid.objectiaacademydemo.main.viewmodels.MainViewModel


class MainFragment : BaseFragment(), IClickListener {
    private lateinit var mBinding: FragmentMainBinding
    private val mViewModel: MainViewModel by viewModels()

    private val mRequestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                ObjectiaUtils.callPhoneNumber(requireContext(), mViewModel.user.phone)
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentMainBinding.inflate(inflater)
        return mBinding.root
    }

    override fun onStart() {
        super.onStart()

        setViews()
    }

    private fun setViews() {
        mBinding.recyclerViewMembers.layoutManager =
            LinearLayoutManager(
                context,
                LinearLayoutManager.VERTICAL,
                false
            ) // @reverseLayout: to show the data in a reversed order

        mBinding.recyclerViewMembers.adapter = UsersAdapter(
            mViewModel.getUsers()
        ).apply {
            listener = this@MainFragment
        }

        // using .apply is basically doing the below:
        /**    val adapter = UsersAdapter(arrayListOf(
        ObjectiaUser("Sam", "Shouman", "+961 3 943 517"),
        ObjectiaUser("Imad", "Hassan", "+961 3 123 456"),
        ObjectiaUser("Loai", "Darsa", "+961 3 777 456"),
        ObjectiaUser("Rayane", "Khaled", "+961 3 452 001"),
        ))

        adapter.listener = this **/
    }

    override fun onItemClick(user: ObjectiaUser) {
        val bundle = Bundle()
        bundle.putSerializable(DetailsFragment.ARG_USER, user)
        findNavController().navigate(R.id.action_MainFragment_to_DetailsFragment, bundle)
    }

    override fun onCallClick(user: ObjectiaUser) {
        mViewModel.user = user

        if (ObjectiaUtils.isPermissionGranted(requireContext(), Manifest.permission.CALL_PHONE)) {
            ObjectiaUtils.callPhoneNumber(requireContext(), user.phone)
        } else {
            mRequestPermissionLauncher.launch(Manifest.permission.CALL_PHONE)
        }
    }
}