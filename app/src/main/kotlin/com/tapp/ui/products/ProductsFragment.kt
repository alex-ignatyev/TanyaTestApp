package com.tapp.ui.products

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.tapp.MainViewModel
import com.tapp.data.response.ProductResponse
import com.tapp.data.response.ProductsResponse
import com.tapp.databinding.FragmentProductsBinding
import com.tapp.di.InternetClient
import com.tapp.domain.Product
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProductsFragment : Fragment() {

    private var _binding: FragmentProductsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by activityViewModels()
    private val adapter = ProductsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductsBinding.inflate(inflater, container, false)
        binding.rc.adapter = adapter

        val internetClient = InternetClient
        val mainApi = internetClient.createMainApi()
        CoroutineScope(Dispatchers.IO).launch {
            val productsResponse: ProductsResponse = mainApi.getAllProducts(viewModel.token.value!!)
            val listProductResponse: List<ProductResponse> = productsResponse.products
            val products: List<Product> = listProductResponse.map { productResponse ->
                Product(
                    title = productResponse.title,
                    description = productResponse.description
                )
            }
            withContext(Dispatchers.Main){
                adapter.submitList(products)
            }
        }
        return binding.root
    }
}
