package mobi.samdroid.objectiaacademydemo.base.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import mobi.samdroid.objectiaacademydemo.base.database.dao.ObjectiaUserDao
import mobi.samdroid.objectiaacademydemo.base.models.ObjectiaUser

@Database(entities = [ObjectiaUser::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun objectiaUserDao(): ObjectiaUserDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "objectia_academy_demo_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}