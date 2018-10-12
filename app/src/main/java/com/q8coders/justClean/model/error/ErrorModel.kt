package com.q8coders.justClean.model.error

import javax.annotation.Generated
import com.google.gson.annotations.SerializedName
/*
 * Created by Shahid Akhtar on 13/10/18.
 * Copyright © 2018 Shahid Akhtar. All rights reserved.
*/
@Generated("com.robohorse.robopojogenerator")
data class ErrorModel(

	@field:SerializedName("status_message")
	val statusMessage: String? = null,

	@field:SerializedName("status_code")
	val statusCode: Int? = null,

	@field:SerializedName("success")
	val success: Boolean? = null
): Throwable()