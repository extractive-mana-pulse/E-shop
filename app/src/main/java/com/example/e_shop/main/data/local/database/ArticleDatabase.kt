package com.example.e_shop.main.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.e_shop.main.domain.model.Product
import com.example.e_shop.profile.data.database.AddressDao
import com.example.e_shop.profile.domain.model.Address

@Database(entities = [Product::class, Address::class], version = 2, exportSchema = false)
@TypeConverters(Converters::class)
abstract class ArticleDatabase : RoomDatabase() {

    abstract fun getProductDao(): ProductDao
    abstract fun getAddressDao(): AddressDao

    companion object {
        @Volatile
        private var instance: ArticleDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also { instance = it }
        }

        private fun createDatabase(context: Context): ArticleDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                ArticleDatabase::class.java,
                "article.db"
            )
                .addMigrations(MIGRATION_1_2) // Add migration here
                .fallbackToDestructiveMigration() // Optional: allows losing data
                .build()
        }

        // Define your migration here
        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // Example migration logic
                database.execSQL("ALTER TABLE Product ADD COLUMN new_column_name INTEGER DEFAULT 0")
            }
        }
    }
}