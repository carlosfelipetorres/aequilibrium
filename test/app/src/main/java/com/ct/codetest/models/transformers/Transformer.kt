package com.ct.codetest.models.transformers

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Transformer (
    val id : String,
    val name : String,
    val team: String,
    @SerializedName("team_icon") val teamIcon: String,
    val courage: Int,
    val endurance: Int,
    val firepower: Int,
    val intelligence: Int,
    val rank: Int,
    val skill: Int,
    val speed: Int,
    val strength: Int
) : Serializable
