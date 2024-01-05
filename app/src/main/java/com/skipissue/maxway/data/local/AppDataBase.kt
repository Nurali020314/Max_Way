package uz.gita.lesson40.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.skipissue.maxway.data.local.BasketDao
import com.skipissue.maxway.data.local.SuggestDao
import com.skipissue.maxway.data.local.entity.BasketEntity
import com.skipissue.maxway.data.local.entity.SuggestFoodEntity

@Database(entities = [BasketEntity::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun basketDao(): BasketDao
}