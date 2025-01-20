package mobi.samdroid.objectiaacademydemo.main.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import mobi.samdroid.objectiaacademydemo.base.interfaces.IClickListener
import mobi.samdroid.objectiaacademydemo.base.models.ObjectiaUser
import mobi.samdroid.objectiaacademydemo.databinding.ViewHolderUserBinding
import mobi.samdroid.objectiaacademydemo.main.viewholders.UserViewHolder

class UsersAdapter(private val users: ArrayList<ObjectiaUser>) :
    RecyclerView.Adapter<UserViewHolder>() {
    var listener: IClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ViewHolderUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(users[position])
        holder.itemView.setOnClickListener {
            listener?.onItemClick(users[position])
        }

        holder.binding.imageButtonCall.setOnClickListener {
            listener?.onCallClick(users[position])
        }
    }

    override fun getItemCount(): Int = users.size
}