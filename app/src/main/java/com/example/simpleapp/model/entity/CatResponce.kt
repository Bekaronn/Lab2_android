package com.example.simpleapp.model.entity

import com.google.gson.annotations.SerializedName

data class CatResponse(
    val length: String,
    val origin: String,
    @SerializedName("image_link") val imageLink: String,
    @SerializedName("family_friendly") val familyFriendly: Int,
    val shedding: Int,
    @SerializedName("general_health") val generalHealth: Int,
    val playfulness: Int,
    @SerializedName("children_friendly") val childrenFriendly: Int,
    val grooming: Int,
    val intelligence: Int,
    @SerializedName("other_pets_friendly") val otherPetsFriendly: Int,
    @SerializedName("min_weight") val minWeight: Float,
    @SerializedName("max_weight") val maxWeight: Float,
    @SerializedName("min_life_expectancy") val minLifeExpectancy: Int,
    @SerializedName("max_life_expectancy") val maxLifeExpectancy: Int,
    val name: String
)
