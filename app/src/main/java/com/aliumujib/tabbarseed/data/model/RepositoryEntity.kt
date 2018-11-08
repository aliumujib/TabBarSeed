package com.aliumujib.tabbarseed.data.model

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

)
