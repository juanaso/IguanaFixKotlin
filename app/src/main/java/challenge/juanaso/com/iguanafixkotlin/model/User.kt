package challenge.juanaso.com.iguanafixkotlin.model


import android.arch.persistence.room.*
import challenge.juanaso.com.iguanafixkotlin.persistence.DataConverter
import com.google.gson.annotations.SerializedName
import org.jetbrains.annotations.NotNull
import java.text.SimpleDateFormat
import java.util.*

@Entity
class User {

    @PrimaryKey
    @NotNull
    @SerializedName("user_id")
    var id: String? = null

    @SerializedName("first_name")
    var name: String? = null

    @SerializedName("last_name")
    var lastName: String? = null

    @SerializedName("thumb")
    var thumbnailUrl: String? = null

    @SerializedName("photo")
    var photoUrl: String? = null

    @TypeConverters(DataConverter::class)
    @SerializedName("created_at")
    var createdDate: Date? = null

    @TypeConverters(DataConverter::class)
    @SerializedName("birth_date")
    var birthDay: Date? = null

    @Ignore
    @SerializedName("phones")
    var phones: List<UserPhone>? = null

    @Ignore
    @SerializedName("addresses")
    var addresses: List<UserAddressess>? = null

    var index: Int = 0

    val fullName:String
        get() {return name+" "+lastName}

    fun getBirthdayFormated(): String {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy")
        return "birthday: " + dateFormat.format(birthDay)
    }

    fun getIdFormated(): String {
        return "ID: " + id
    }

    fun getCreatedDateFormated(): String {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy")
        return "Member Since: " + dateFormat.format(createdDate)
    }
}