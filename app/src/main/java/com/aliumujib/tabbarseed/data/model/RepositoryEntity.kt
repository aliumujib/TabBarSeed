package com.aliumujib.tabbarseed.data.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

/**
 * Created by aliumujib on 12/05/2018.
 *
 * Represents a repository on the data layer
 */

data class RepositoryEntity(

        var id: Int,

        @SerializedName("full_name") var repoFullName: String,

        @SerializedName("name") var repoName: String,

        @SerializedName("description") var repoDescription: String,

        @SerializedName("owner") var user: UserEntity?,

        @SerializedName("stargazers_count") var starsCount: Int,

        @SerializedName("language") var language: String?

) : Parcelable {
    constructor(source: Parcel) : this(
            source.readInt(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readParcelable<UserEntity>(UserEntity::class.java.classLoader),
            source.readInt(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeInt(id)
        writeString(repoFullName)
        writeString(repoName)
        writeString(repoDescription)
        writeParcelable(user, 0)
        writeInt(starsCount)
        writeString(language)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<RepositoryEntity> = object : Parcelable.Creator<RepositoryEntity> {
            override fun createFromParcel(source: Parcel): RepositoryEntity = RepositoryEntity(source)
            override fun newArray(size: Int): Array<RepositoryEntity?> = arrayOfNulls(size)
        }
    }
}
