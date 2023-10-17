package com.ykv17.luxebloom.presentation.screens.home

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.ykv17.luxebloom.presentation.route.RouteScreens
import com.ykv17.luxebloom.presentation.screens.home.state.CategoryListItem
import com.ykv17.luxebloom.presentation.screens.home.state.CategoryListState
import com.ykv17.luxebloom.presentation.screens.home.state.HomeUiState
import com.ykv17.luxebloom.presentation.screens.home.state.ProductListItem
import com.ykv17.luxebloom.presentation.screens.navigation_bar.BottomNavigationBar
import com.ykv17.luxebloom.ui.theme.LuxeBloomTheme
import com.ykv17.luxebloom.util.Screen
import com.ykv17.luxebloom.util.StateUtils.updateState

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeScreenViewModel = hiltViewModel()
) {

    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        Column(modifier = Modifier.fillMaxSize()) {
            TopAppBar(
                navController = navController,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                onCartClick = {

                },
                onNotificationClick = {

                }
            )
            SearchBarPlaceHolder(
                placeholder = "Search for products",
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
                    .height(36.dp)
                    .shadow(2.dp, CircleShape)
                    .clip(CircleShape)
                    .clickable {

                    }
                    .background(MaterialTheme.colorScheme.surface, shape = CircleShape)
                    .border(
                        BorderStroke(
                            width = 0.2.dp,
                            color = Color.LightGray.copy(alpha = 0.2f),

                            ),
                        shape = CircleShape
                    )
                    .padding(4.dp)
            )
            CategoryList(
                viewModel = viewModel,
                modifier = Modifier
                    .padding(start = 16.dp, top = 16.dp)
                    .fillMaxWidth()
            )

            ProductList(
                navController = navController,
                viewModel = viewModel,
                modifier = Modifier.padding(start = 16.dp, end = 16.dp)
            )
        }
    }
}

@Composable
fun ProductList(
    navController: NavController,
    viewModel: HomeScreenViewModel,
    modifier: Modifier = Modifier
) {
    val products by remember {
        viewModel.productListState
    }

    LazyVerticalGrid(columns = GridCells.Adaptive(minSize = 128.dp), modifier = modifier) {
        items(
            items = products.products,
            key = {
                when (it) {
                    is ProductListItem.ProductItem -> {
                        it.id
                    }
                }
            }
        ) {
            when (val item = it) {
                is ProductListItem.ProductItem -> {
                    ProductListItem(
                        item,
                        modifier = Modifier
                            .padding(4.dp)
                            .clickable {
                                navController.navigate(RouteScreens.ProductScreen.route + "/${item.id}")
                            }
                    )
                }
            }

        }
    }
}

@Composable
fun ProductListItem(
    productItem: ProductListItem.ProductItem,
    modifier: Modifier = Modifier,
    imageModifier: Modifier = Modifier
) {


    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Box {
            AsyncImage(
                model = productItem.image,
                contentDescription = productItem.name,
                contentScale = ContentScale.Crop,
                modifier = imageModifier
                    .size((Screen.getWindowWidth() / 2 - 16).dp)
                    .clip(shape = RoundedCornerShape(10.dp))
            )

            /*Row(verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .clip(
                        RoundedCornerShape(2.dp)
                    )
                    .background(MaterialTheme.colorScheme.secondary)
                    .padding(1.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Star,
                    tint = Color.Yellow,
                    contentDescription = "Start",
                    modifier = Modifier.size(10.dp)
                )
                Text(
                    text = "${productItem.rating}",
                    fontSize = 10.sp,
                    color = MaterialTheme.colorScheme.onSecondary
                )
            }*/

        }

        Column(modifier = Modifier.padding(8.dp)) {
            Text(
                text = productItem.name,
                fontSize = 12.sp,
                maxLines = 1,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .fillMaxWidth()
            )
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(2.dp)
            ) {
                Row {
                    Text(
                        text = productItem.price,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier
                            .padding(horizontal = 4.dp)
                    )

                    TextWithLineThrough(text = productItem.actualPrice, fontSize = 12.sp)
                }

                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "${productItem.discountPercentage}%",
                    fontSize = 8.sp,
                    color = MaterialTheme.colorScheme.onSecondary,
                    modifier = Modifier
                        .clip(
                            RoundedCornerShape(2.dp)
                        )
                        .background(MaterialTheme.colorScheme.secondary)
                        .padding(1.dp)
                )
            }

        }
    }


}

@Composable
fun CategoryListItem(
    categoryItem: CategoryListItem.CategoryItem,
    modifier: Modifier = Modifier
) {
    Text(
        text = categoryItem.name,
        color = if (categoryItem.isSelected) MaterialTheme.colorScheme.onPrimary else Color.Gray,
        fontWeight = if (categoryItem.isSelected) FontWeight.SemiBold else FontWeight.Normal,
        modifier = modifier,
        textAlign = TextAlign.Center
    )
}

@Composable
fun CategoryList(
    viewModel: HomeScreenViewModel,
    modifier: Modifier = Modifier
) {
    val categories by remember {
        viewModel.categoryListState
    }

    LazyRow(
        content = {
            items(
                count = categories.categories.size,
                key = {
                    when (val item = categories.categories[it]) {
                        is CategoryListItem.CategoryItem -> {
                            item.name
                        }
                    }
                }
            ) {
                when (val item = categories.categories[it]) {
                    is CategoryListItem.CategoryItem -> {
                        CategoryListItem(
                            item, modifier = Modifier
                                .padding(8.dp)
                                .clip(CircleShape)
                                .clickable {
                                    if (!item.isSelected) {
                                        updateState(viewModel.categoryListState) {
                                            it.copy(categories = it.categories.map {
                                                when (it) {
                                                    is CategoryListItem.CategoryItem -> {
                                                        it.copy(
                                                            isSelected = it.name == item.name
                                                        )
                                                    }
                                                }
                                            })
                                        }
                                    }
                                }
                                .background(color = if (item.isSelected) MaterialTheme.colorScheme.primary else Color.Transparent)
                                .padding(vertical = 2.dp, horizontal = 8.dp)
                        )

                        if (item.isSelected) {
                            when (item.name) {
                                "All" -> {
                                    viewModel.getAllProducts()
                                }

                                else -> {
                                    viewModel.getProductByCategory(item.searchValue)
                                }
                            }

                        }
                    }
                }

            }
        },
        modifier = modifier
    )
}

@Composable
fun SearchBarPlaceHolder(
    placeholder: String = "",
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Spacer(modifier = Modifier.width(4.dp))
        Icon(
            imageVector = Icons.Outlined.Search,
            contentDescription = "Search",
            tint = Color.Gray
        )
        Text(
            text = placeholder,
            modifier = Modifier.padding(4.dp),
            fontSize = 14.sp,
            color = Color.Gray
        )
    }
}

@Composable
fun TopAppBar(
    navController: NavController,
    modifier: Modifier = Modifier,
    onCartClick: () -> Unit,
    onNotificationClick: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
    ) {
        Text(
            text = "LuxeBloom",
            fontWeight = FontWeight.SemiBold
        )
        Row {
            Icon(
                imageVector = Icons.Outlined.Notifications,
                contentDescription = "Notification",
                modifier = Modifier.clickable { onNotificationClick() })
            Spacer(modifier = Modifier.width(16.dp))
            Icon(
                imageVector = Icons.Outlined.ShoppingCart,
                contentDescription = "Cart",
                modifier = Modifier.clickable { onCartClick() })
        }

    }
}

@Composable
private fun CustomTextField(
    value: String,
    modifier: Modifier = Modifier,
    leadingIcon: (@Composable () -> Unit)? = null,
    trailingIcon: (@Composable () -> Unit)? = null,
    placeholderText: String = "Placeholder",
    fontSize: TextUnit = MaterialTheme.typography.bodySmall.fontSize
) {
    var text by rememberSaveable { mutableStateOf(value) }
    BasicTextField(modifier = modifier
        .background(
            MaterialTheme.colorScheme.surface,
            MaterialTheme.shapes.small,
        )
        .fillMaxWidth(),
        value = text,
        onValueChange = {
            text = it
        },
        singleLine = true,
        cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
        textStyle = LocalTextStyle.current.copy(
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = fontSize
        ),
        decorationBox = { innerTextField ->
            Row(
                modifier,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (leadingIcon != null) leadingIcon()
                Box(Modifier.weight(1f)) {
                    if (text.isEmpty()) Text(
                        placeholderText,
                        style = LocalTextStyle.current.copy(
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f),
                            fontSize = fontSize
                        )
                    )
                    innerTextField()
                }
                if (trailingIcon != null) trailingIcon()
            }
        }
    )
}

@Composable
fun TextWithLineThrough(text: String, fontSize: TextUnit = TextUnit.Unspecified) {
    val annotatedString = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                textDecoration = TextDecoration.LineThrough
            )
        ) {
            append(text)
        }
    }

    Text(
        text = annotatedString,
        fontSize = fontSize,
        color = MaterialTheme.colorScheme.onSurface,
        modifier = Modifier
            .padding(horizontal = 4.dp)
    )

}


@Composable
@Preview
fun PreviewScreen() {
    LuxeBloomTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            Column(modifier = Modifier.fillMaxSize()) {

            }
        }

    }
}