package com.example.example

import com.google.gson.annotations.SerializedName


data class Items (

  @SerializedName("id"                ) var id               : Int?              = null,
  @SerializedName("name"              ) var name             : String?           = null,
  @SerializedName("full_name"         ) var fullName         : String?           = null,
  @SerializedName("owner"             ) var owner            : Owner?            = Owner(),
  @SerializedName("html_url"          ) var htmlUrl          : String?           = null,
  @SerializedName("description"       ) var description      : String?           = null,
  @SerializedName("created_at"        ) var createdAt        : String?           = null,
  @SerializedName("updated_at"        ) var updatedAt        : String?           = null,
  @SerializedName("language"          ) var language         : String?           = null,

)