package com.lyscraft.apparel.compose.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(searchQuery: String, onValueChange: (String) -> Unit) {
    TextField(value = searchQuery, onValueChange = onValueChange, placeholder = {
        Text(
            text = "Search movies", color = Color.Gray, fontSize = 16.sp
        )
    }, leadingIcon = {
        Image(
            painter = painterResource(id = android.R.drawable.ic_menu_search),
            contentDescription = null,
            colorFilter = ColorFilter.tint(Color.Gray)
        )
    }, colors = TextFieldDefaults.textFieldColors(
        focusedIndicatorColor = Color.Transparent, unfocusedIndicatorColor = Color.Transparent
    ), shape = RoundedCornerShape(8.dp), modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)
    )
}

@Preview
@Composable
fun SearchBarPreview() {
    SearchBar("") {

    }
}