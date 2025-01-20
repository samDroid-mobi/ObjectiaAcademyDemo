package mobi.samdroid.objectiaacademydemo.base.models

import java.io.Serializable

open class ObjectiaUser(
    var username: String = "",
    var password: String = "",
    var phone: String = ""
): Serializable