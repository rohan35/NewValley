package com.raydevelopers.newvalley.models

data class SingleHeaderItem(
    var title: String? = null,
    var startIndex:Int = 0,
    var endIndex:Int = 0
):ComponentViewType(HEADER_TYPE)