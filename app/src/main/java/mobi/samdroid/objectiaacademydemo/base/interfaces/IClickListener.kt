package mobi.samdroid.objectiaacademydemo.base.interfaces

import mobi.samdroid.objectiaacademydemo.base.models.ObjectiaUser

interface IClickListener {
    fun onItemClick(user: ObjectiaUser)
    fun onCallClick(user: ObjectiaUser)
}