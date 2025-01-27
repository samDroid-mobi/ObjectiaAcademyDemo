package mobi.samdroid.objectiaacademydemo.base.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import mobi.samdroid.objectiaacademydemo.base.models.ObjectiaUser

@Dao
interface ObjectiaUserDao {
    @Insert
    suspend fun insert(user: ObjectiaUser)

    @Query("SELECT * FROM OBJECTIA_USER WHERE username = :username")
    suspend fun getUserByUsername(username: String): ObjectiaUser?

    @Query("SELECT * FROM OBJECTIA_USER WHERE username = :username AND password = :password")
    suspend fun getUserByCredentials(username: String, password: String): ObjectiaUser?

    @Query("SELECT * FROM OBJECTIA_USER")
    suspend fun getAllUsers(): List<ObjectiaUser>
}