package com.example.composebottomsheet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composebottomsheet.ui.theme.ComposeBottomSheetTheme
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeBottomSheetTheme {
                MainApplication()
            }
        }
    }
}

@Composable
@ExperimentalMaterialApi
fun MainApplication(){
    Surface{
        //place the app screens here!
        HomeScreen()
    }
}


/**--------------------------Composable---------------------------**/
@Composable
@ExperimentalMaterialApi
fun HomeScreen(){
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
    )
    val  coroutineScope = rememberCoroutineScope()
    val text = remember {
        mutableStateOf("Open details sheet")
    }

   BottomSheetScaffold(
       scaffoldState = bottomSheetScaffoldState,
       sheetContent = {
           BottomSheet(
               title = R.string.text_title,
               body = R.string.text,
               img = R.drawable.devgrouphero
           )
       },
       sheetPeekHeight = 0.dp,
       sheetShape = RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp),
       sheetGesturesEnabled = true,

   ){

    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
            ){
        Text(
            text = "Home screen",
            Modifier.padding(top = 20.dp)
        )
        Button(
            onClick = {
                coroutineScope.launch {
                    if(bottomSheetScaffoldState.bottomSheetState.isCollapsed){
                        bottomSheetScaffoldState.bottomSheetState.expand()
                        text.value = "Close details sheet"
                    }else {
                        bottomSheetScaffoldState.bottomSheetState.collapse()
                        text.value = "Open details sheet"
                    }
                }
            }
        ) {
            Text(text = text.value)
        }
    }
   }




}

@Composable
fun BottomSheet(
    @StringRes title: Int,
    @StringRes body: Int,
    @DrawableRes img: Int
){
    Surface(
        shape = MaterialTheme.shapes.large,
        modifier = Modifier
            .shadow(20.dp)
            .fillMaxWidth()
            .height(600.dp),
        color = Color(4,12,0)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                stringResource(id = title),
                style = MaterialTheme.typography.h5,
                color = Color(9,206,102),
                modifier = Modifier.padding(top = 15.dp, bottom = 10.dp)
            )

            Image(painter = painterResource(id = img) , contentDescription = null )

            Text(
                stringResource(id = body),
                style = MaterialTheme.typography.body1,
                color = Color.White,
                modifier = Modifier.padding(vertical = 16.dp, horizontal = 10.dp)
            )
        }
    }
}
/**--------------------------End Composable---------------------------**/




/**--------------------------Previews---------------------------**/
@Preview(showBackground = true)
@Composable
@ExperimentalMaterialApi
fun MainApplicationPreview(){
    ComposeBottomSheetTheme {
        MainApplication()
    }
}

@Preview(showBackground = true)
@Composable
fun BottomSheetPreview(){
    ComposeBottomSheetTheme {
        BottomSheet(R.string.text_title, R.string.text,R.drawable.devgrouphero)
    }
}
/**--------------------------End Previews---------------------------**/


