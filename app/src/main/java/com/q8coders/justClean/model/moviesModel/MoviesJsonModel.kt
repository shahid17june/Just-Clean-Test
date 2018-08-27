package com.q8coders.justClean.model.moviesModel

import javax.annotation.Generated
import com.google.gson.annotations.SerializedName

@Generated("com.robohorse.robopojogenerator")
data class MoviesJsonModel(

        @field:SerializedName("page")
        val page: Int? = null,

        @field:SerializedName("total_pages")
        val totalPages: Int? = null,

        @field:SerializedName("total_results")
        val totalResults: Int? = null,

        @field:SerializedName("results")
        val results: MutableList<MoviesItem>? = null
)