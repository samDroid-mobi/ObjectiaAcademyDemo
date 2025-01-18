package mobi.samdroid.objectiaacademydemo.main.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import mobi.samdroid.objectiaacademydemo.R
import mobi.samdroid.objectiaacademydemo.databinding.FragmentMainBinding


class MainFragment : Fragment() {
    private lateinit var mBinding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentMainBinding.inflate(inflater)
        return mBinding.root
    }

    override fun onStart() {
        super.onStart()

        setListeners()
    }

    private fun setListeners() {
        mBinding.textViewTitle.setOnClickListener {
            findNavController().navigate(R.id.action_MainFragment_to_DetailsFragment)
        }
    }
}