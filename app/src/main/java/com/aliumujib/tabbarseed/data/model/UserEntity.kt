package com.aliumujib.tabbarseed.data.model


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

)