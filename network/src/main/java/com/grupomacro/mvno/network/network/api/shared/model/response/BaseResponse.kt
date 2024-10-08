package com.grupomacro.mvno.network.network.api.shared.model.response

import com.google.gson.annotations.SerializedName


data class BaseResponse<T>(

    @SerializedName("data")
    val data: ResponseData<T>?,

    @SerializedName("errors")
    val errors: List<ErrorItemResponse>?
) {
    data class ResponseData<T>(

        @SerializedName("id")
        val id: String,

        @SerializedName("type")
        val type: String,

        @SerializedName("attributes")
        val attributes: T,
    )
}
