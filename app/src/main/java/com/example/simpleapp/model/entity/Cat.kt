package com.example.simpleapp.model.entity

import com.google.gson.annotations.SerializedName
import java.util.UUID

data class Cat(
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val origin: String,
    @SerializedName("image_link") val imageUrl: String = "",
    val familyFriendly: Int,
    val shedding: Int,
    val generalHealth: Int,
    val playfulness: Int,
    val childrenFriendly: Int,
    val grooming: Int,
    val intelligence: Int,
    val otherPetsFriendly: Int,
    val minWeight: Float,
    val maxWeight: Float,
    val minLifeExpectancy: Int,
    val maxLifeExpectancy: Int,
    val length: String
)

val catMapper: (CatResponse) -> Cat = { response ->
    Cat(
        name = response.name,
        origin = response.origin ?: "Unknown",
        imageUrl = response.imageLink?.takeIf { it.isNotEmpty() } ?: "",
        familyFriendly = response.familyFriendly ?: 0,
        shedding = response.shedding ?: 0,
        generalHealth = response.generalHealth ?: 0,
        playfulness = response.playfulness ?: 0,
        childrenFriendly = response.childrenFriendly ?: 0,
        grooming = response.grooming ?: 0,
        intelligence = response.intelligence ?: 0,
        otherPetsFriendly = response.otherPetsFriendly ?: 0,
        minWeight = response.minWeight ?: 0f,
        maxWeight = response.maxWeight ?: 0f,
        minLifeExpectancy = response.minLifeExpectancy ?: 0,
        maxLifeExpectancy = response.maxLifeExpectancy ?: 0,
        length = response.length ?: "Unknown"
    )
}