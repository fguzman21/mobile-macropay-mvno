package com.grupomacro.mvno.network.network.api.shared.model.response

import com.google.gson.annotations.SerializedName

data class ErrorItemResponse(

    @SerializedName("status")
    val status: String?,

    @SerializedName("code")
    val code: String?,

    @SerializedName("title")
    val title: String?,

    @SerializedName("detail")
    val detail: String?,

    @SerializedName("source")
    val source: Source?,

    @SerializedName("sugestion")
    val suggestion: String?,
) {

    data class Source(

        @SerializedName("pointer")
        val pointer: String?
    )
}
