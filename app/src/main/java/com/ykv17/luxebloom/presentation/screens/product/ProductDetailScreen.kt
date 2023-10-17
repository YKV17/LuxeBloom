package com.ykv17.luxebloom.presentation.screens.product

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.ykv17.luxebloom.R
import com.ykv17.luxebloom.presentation.screens.home.TextWithLineThrough
import com.ykv17.luxebloom.presentation.screens.onboarding.HorizontalPagerIndicator
import com.ykv17.luxebloom.presentation.screens.product.state.ProductDetailUiState
import com.ykv17.luxebloom.ui.theme.DarkYellow
import com.ykv17.luxebloom.util.Screen

@Composable
fun ProductDetailScreen(
    navController: NavHostController,
    productId: String,
    viewModel: ProductDetailScreenViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = true) {
        viewModel.getProduct(productId = productId)
    }

    val productDetailState by remember {
        viewModel.productDetailState
    }

    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        Column {
            if (!productDetailState.isError && !productDetailState.isLoading) {
                TopAppBar(
                    navController = navController,
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    onCartClick = {

                    }
                )
                ProductImageViewer(
                    productDetailState.productDetail.images,
                    modifier = Modifier
                        .width(Screen.getWindowWidth().dp)
                        .height(Screen.getWindowWidth().dp)
                )
                ProductDetails(
                    productDetailState, modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                )
            } else {
                Error()
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProductImageViewer(
    images: List<String> = emptyList(),
    modifier: Modifier = Modifier
) {

    val pageCount = images.size
    val pageState = rememberPagerState {
        pageCount
    }

    Box(
        modifier = modifier
    ) {
        HorizontalPager(
            state = pageState,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            AsyncImage(
                model = images[page],
                contentScale = ContentScale.Crop,
                contentDescription = "Image",
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
            )
        }

        HorizontalPagerIndicator(
            pageCount,
            pageState,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth(),
            indicatorModifier = Modifier
                .padding(2.dp)
                .clip(CircleShape)
                .height(8.dp)
                .width(8.dp)
        )
    }
}

@Composable
fun TopAppBar(
    navController: NavController,
    modifier: Modifier = Modifier,
    onCartClick: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
    ) {
        Icon(
            imageVector = Icons.Outlined.ArrowBack,
            contentDescription = "Back",
            modifier = Modifier
                .clip(CircleShape)
                .clickable { navController.popBackStack() })


        Icon(
            imageVector = Icons.Outlined.ShoppingCart,
            contentDescription = "Cart",
            modifier = Modifier.clickable { onCartClick() })


    }
}

@Composable
fun ProductDetails(
    productDetailState: ProductDetailUiState,
    modifier: Modifier = Modifier
) {

    val product = productDetailState.productDetail
    Box(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 34.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = product.brand,
                    fontSize = 12.sp,
                    modifier = Modifier
                        .border(
                            border = BorderStroke(
                                width = 0.5.dp,
                                color = MaterialTheme.colorScheme.onSurface
                            ), shape = CircleShape
                        )
                        .padding(vertical = 2.dp, horizontal = 4.dp)
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = "Rating",
                        tint = DarkYellow,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = product.rating, fontSize = 14.sp)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = product.title,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(8.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "Offer Price: ${product.price}",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier
                            .padding(horizontal = 4.dp)
                    )

                    TextWithLineThrough(text = product.actualPrice, fontSize = 18.sp)
                }

                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "${product.discountPercentage}% OFF",
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSecondary,
                    modifier = Modifier
                        .clip(
                            RoundedCornerShape(2.dp)
                        )
                        .background(MaterialTheme.colorScheme.secondary)
                        .padding(1.dp)
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Text(text = product.description)
        }

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .clip(CircleShape)
                .align(Alignment.BottomCenter),
            onClick = {

            }
        ) {

                Text(text = if(!product.stock.isNullOrEmpty() && product.stock.toInt() > 0) "Add to Cart" else "Notify Me")

        }
    }

}

@Composable
fun Error() {

}