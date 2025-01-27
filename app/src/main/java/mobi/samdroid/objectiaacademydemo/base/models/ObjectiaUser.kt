package mobi.samdroid.objectiaacademydemo.base.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "OBJECTIA_USER")
open class ObjectiaUser(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var username: String = "",
    var password: String = "",
    var phone: String = ""
): Serializable