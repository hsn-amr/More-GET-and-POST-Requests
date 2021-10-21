package com.example.moregetandpostrequests

import com.google.gson.annotations.SerializedName

class Name {

    @SerializedName("pk")
    var id: Int? = null

    @SerializedName("location")
    var location: String? = null

    @SerializedName("name")
    var name: String? = null
}