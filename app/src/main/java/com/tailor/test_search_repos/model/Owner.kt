package com.example.example

import com.google.gson.annotations.SerializedName


data class Owner (

  @SerializedName("avatar_url"          ) var avatarUrl         : String?  = null,
  @SerializedName("url"                 ) var url               : String?  = null,
  @SerializedName("html_url"            ) var htmlUrl           : String?  = null,

)