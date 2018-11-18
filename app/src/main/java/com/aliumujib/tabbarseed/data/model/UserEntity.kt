package com.aliumujib.tabbarseed.data.model


import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

/**
 * Created by aliumujib on 12/05/2018.
 *
 *  Represents a user on the data layer
 */

data class UserEntity(

        @SerializedName("login") var name: String,

        @SerializedName("avatar_url") var imageUrl: String,

        @SerializedName("id") var id: Int?

) : Parcelable {
    constructor(source: Parcel) : this(
            source.readString(),
            source.readString(),
            source.readValue(Int::class.java.classLoader) as Int?
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(name)
        writeString(imageUrl)
        writeValue(id)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<UserEntity> = object : Parcelable.Creator<UserEntity> {
            override fun createFromParcel(source: Parcel): UserEntity = UserEntity(source)
            override fun newArray(size: Int): Array<UserEntity?> = arrayOfNulls(size)
        }
    }
}