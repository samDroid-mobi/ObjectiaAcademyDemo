package mobi.samdroid.objectiaacademydemo.main.viewholders

import androidx.recyclerview.widget.RecyclerView
import mobi.samdroid.objectiaacademydemo.base.models.ObjectiaUser
import mobi.samdroid.objectiaacademydemo.databinding.ViewHolderUserBinding

class UserViewHolder(val binding: ViewHolderUserBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(user: ObjectiaUser) {
        binding.textViewUsername.text = user.username
        binding.textViewPassword.text = user.password
    }
}