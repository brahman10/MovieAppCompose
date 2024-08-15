package com.lyscraft.apparel.compose.components

import android.content.res.Resources
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.lyscraft.apparel.R
import com.lyscraft.apparel.enums.ViewState

@Composable
fun StateScreen(modifier: Modifier, type: ViewState) {
    var resourceId: Int? = null
    var message = ""
    if (type == ViewState.EMPTY) {
        resourceId = R.drawable.ic_empty_state
        message = stringResource(R.string.no_data_found)
    } else if (type == ViewState.ERROR) {
        resourceId = R.drawable.ic_error_state
        message = stringResource(R.string.error_message)
    }
    val context = LocalContext.current
    val exists = resourceId?.let { resourceExists(context.resources, it) }
    if (exists == true) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(vertical = 40.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = resourceId!!),
                contentDescription = stringResource(R.string.empty_state),
                contentScale = ContentScale.Crop
            )
            Text(
                text = message,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(vertical = 4.dp)
            )
        }
    } else {
        // Handle the error case, e.g., show a placeholder or log an error
    }
}

fun resourceExists(resources: Resources, resourceId: Int): Boolean {
    return try {
        resources.getDrawable(resourceId, null)
        true
    } catch (e: Resources.NotFoundException) {
        false
    }
}
