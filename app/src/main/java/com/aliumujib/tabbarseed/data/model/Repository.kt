package com.aliumujib.tabbarseed.data.model

import com.google.gson.annotations.SerializedName


class Repository(
        @SerializedName("id") var id: Int,

        @SerializedName("full_name") var repoFullName: String,

        @SerializedName("name") var repoName: String,

        @SerializedName("description") var repoDescription: String,

        @SerializedName("stargazers_count") var starsCount: Int,

        @SerializedName("language") var language: String?)