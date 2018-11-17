package radhika.yusuf.id.mvvmkotlin.data.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "heroes")
data class HeroesModel(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    @SerializedName("name")
    @ColumnInfo(name = "name")
    var name: String? = "-",
    @SerializedName("realname")
    @ColumnInfo(name = "realname")
    var realname: String? = "-",
    @SerializedName("team")
    @ColumnInfo(name = "team")
    var team: String? = "-",
    @SerializedName("firstappearance")
    @ColumnInfo(name = "firstappearance")
    var firstappearance: String? = "-",
    @SerializedName("createdby")
    @ColumnInfo(name = "createdby")
    var createdby: String? = "-",
    @SerializedName("publisher")
    @ColumnInfo(name = "publisher")
    var publisher: String? = "-",
    @SerializedName("imageurl")
    @ColumnInfo(name = "imageurl")
    var imageurl: String? = "-",
    @SerializedName("bio")
    @ColumnInfo(name = "bio")
    var bio: String? = "-"
)