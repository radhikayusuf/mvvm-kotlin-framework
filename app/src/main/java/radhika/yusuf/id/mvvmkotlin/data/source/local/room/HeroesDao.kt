package radhika.yusuf.id.mvvmkotlin.data.source.local.room

import android.arch.persistence.room.*
import radhika.yusuf.id.mvvmkotlin.data.model.HeroesModel

@Dao
interface HeroesDao {

    @Query("SELECT * FROM heroes")
    fun retrievedHeroes(): List<HeroesModel>?

    @Query("DELETE FROM heroes")
    fun deleteAllHeroes()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHeroes(data: HeroesModel)
}