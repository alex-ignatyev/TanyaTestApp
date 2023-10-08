package com.tapp.data.response

data class ProductsResponse(
    val products: List<ProductResponse>,
    val total: Int,
    val skip: Int,
    val limit: Int
)

data class ProductResponse(
    val id: Int,
    val title: String,
    val description: String,
    val price: Int,
    val discountPercentage: Float,
    val rating: Float,
    val stock: Float,
    val brand: String,
    val category: String,
    val thumbnail: String,
    val images: List<String>
)