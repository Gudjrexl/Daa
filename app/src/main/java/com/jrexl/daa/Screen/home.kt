package com.jrexl.daa.Screen

import android.Manifest
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.jrexl.daa.dataclass.PersonCard
import com.jrexl.daa.dataclass.statuss
import com.jrexl.daa.supporterapi.locationhelper
import kotlinx.coroutines.launch

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun Home(navController: NavHostController) {

    var sm = listOf(
        PersonCard(
            name = "Rohit Sharma",
            age = 28,
            gender = "Male",
            images = listOf(
                "https://randomuser.me/api/portraits/men/11.jpg",
                "https://randomuser.me/api/portraits/men/12.jpg"
            ),
            isRightNow = true
        ),
        PersonCard(
            name = "Sneha Kapoor",
            age = 25,
            gender = "Female",
            images = listOf(
                "https://randomuser.me/api/portraits/women/9.jpg",
                "https://randomuser.me/api/portraits/women/10.jpg"
            ),
            isRightNow = false
        ),
        PersonCard(
            name = "Aman Verma",
            age = 30,
            gender = "Male",
            images = listOf(
                "https://randomuser.me/api/portraits/men/31.jpg",
                "https://randomuser.me/api/portraits/men/32.jpg"
            ),
            isRightNow = true
        ),
        PersonCard(
            name = "Priya Singh",
            age = 27,
            gender = "Female",
            images = listOf(
                "https://randomuser.me/api/portraits/women/14.jpg",
                "https://randomuser.me/api/portraits/women/15.jpg"
            ),
            isRightNow = false
        ),
        PersonCard(
            name = "Rohit Sharma",
            age = 28,
            gender = "Male",
            images = listOf(
                "https://randomuser.me/api/portraits/men/11.jpg",
                "https://randomuser.me/api/portraits/men/12.jpg"
            ),
            isRightNow = true
        ),
        PersonCard(
            name = "Sneha Kapoor",
            age = 25,
            gender = "Female",
            images = listOf(
                "https://randomuser.me/api/portraits/women/9.jpg",
                "https://randomuser.me/api/portraits/women/10.jpg"
            ),
            isRightNow = false
        ),
        PersonCard(
            name = "Aman Verma",
            age = 30,
            gender = "Male",
            images = listOf(
                "https://randomuser.me/api/portraits/men/31.jpg",
                "https://randomuser.me/api/portraits/men/32.jpg"
            ),
            isRightNow = true
        ),
        PersonCard(
            name = "Priya Singh",
            age = 27,
            gender = "Female",
            images = listOf(
                "https://randomuser.me/api/portraits/women/14.jpg",
                "https://randomuser.me/api/portraits/women/15.jpg"
            ),
            isRightNow = false
        ),
        PersonCard(
            name = "Rohit Sharma",
            age = 28,
            gender = "Male",
            images = listOf(
                "https://randomuser.me/api/portraits/men/11.jpg",
                "https://randomuser.me/api/portraits/men/12.jpg"
            ),
            isRightNow = true
        ),
        PersonCard(
            name = "Sneha Kapoor",
            age = 25,
            gender = "Female",
            images = listOf(
                "https://randomuser.me/api/portraits/women/9.jpg",
                "https://randomuser.me/api/portraits/women/10.jpg"
            ),
            isRightNow = false
        ),
        PersonCard(
            name = "Aman Verma",
            age = 30,
            gender = "Male",
            images = listOf(
                "https://randomuser.me/api/portraits/men/31.jpg",
                "https://randomuser.me/api/portraits/men/32.jpg"
            ),
            isRightNow = true
        ),
        PersonCard(
            name = "Priya Singh",
            age = 27,
            gender = "Female",
            images = listOf(
                "https://randomuser.me/api/portraits/women/14.jpg",
                "https://randomuser.me/api/portraits/women/15.jpg"
            ),
            isRightNow = false
        ),
        PersonCard(
            name = "Rohit Sharma",
            age = 28,
            gender = "Male",
            images = listOf(
                "https://randomuser.me/api/portraits/men/11.jpg",
                "https://randomuser.me/api/portraits/men/12.jpg"
            ),
            isRightNow = true
        ),
        PersonCard(
            name = "Sneha Kapoor",
            age = 25,
            gender = "Female",
            images = listOf(
                "https://randomuser.me/api/portraits/women/9.jpg",
                "https://randomuser.me/api/portraits/women/10.jpg"
            ),
            isRightNow = false
        ),
        PersonCard(
            name = "Aman Verma",
            age = 30,
            gender = "Male",
            images = listOf(
                "https://randomuser.me/api/portraits/men/31.jpg",
                "https://randomuser.me/api/portraits/men/32.jpg"
            ),
            isRightNow = true
        ),
        PersonCard(
            name = "Priya Singh",
            age = 27,
            gender = "Female",
            images = listOf(
                "https://randomuser.me/api/portraits/women/14.jpg",
                "https://randomuser.me/api/portraits/women/15.jpg"
            ),
            isRightNow = false
        ),
        PersonCard(
            name = "Rohit Sharma",
            age = 28,
            gender = "Male",
            images = listOf(
                "https://randomuser.me/api/portraits/men/11.jpg",
                "https://randomuser.me/api/portraits/men/12.jpg"
            ),
            isRightNow = true
        ),
        PersonCard(
            name = "Sneha Kapoor",
            age = 25,
            gender = "Female",
            images = listOf(
                "https://randomuser.me/api/portraits/women/9.jpg",
                "https://randomuser.me/api/portraits/women/10.jpg"
            ),
            isRightNow = false
        ),
        PersonCard(
            name = "Aman Verma",
            age = 30,
            gender = "Male",
            images = listOf(
                "https://randomuser.me/api/portraits/men/31.jpg",
                "https://randomuser.me/api/portraits/men/32.jpg"
            ),
            isRightNow = true
        ),
        PersonCard(
            name = "Priya Singh",
            age = 27,
            gender = "Female",
            images = listOf(
                "https://randomuser.me/api/portraits/women/14.jpg",
                "https://randomuser.me/api/portraits/women/15.jpg"
            ),
            isRightNow = false
        ),
        PersonCard(
            name = "Rohit Sharma",
            age = 28,
            gender = "Male",
            images = listOf(
                "https://randomuser.me/api/portraits/men/11.jpg",
                "https://randomuser.me/api/portraits/men/12.jpg"
            ),
            isRightNow = true
        ),
        PersonCard(
            name = "Sneha Kapoor",
            age = 25,
            gender = "Female",
            images = listOf(
                "https://randomuser.me/api/portraits/women/9.jpg",
                "https://randomuser.me/api/portraits/women/10.jpg"
            ),
            isRightNow = false
        ),
        PersonCard(
            name = "Aman Verma",
            age = 30,
            gender = "Male",
            images = listOf(
                "https://randomuser.me/api/portraits/men/31.jpg",
                "https://randomuser.me/api/portraits/men/32.jpg"
            ),
            isRightNow = true
        ),
        PersonCard(
            name = "Priya Singh",
            age = 27,
            gender = "Female",
            images = listOf(
                "https://randomuser.me/api/portraits/women/14.jpg",
                "https://randomuser.me/api/portraits/women/15.jpg"
            ),
            isRightNow = false
        )
    )


        val stories = listOf(
            statuss("Peggy", "https://placekitten.com/400/400", false),
            statuss("Riya", "https://sample-videos.com/video123/mp4/720/big_buck_bunny_720p_1mb.mp4", true),
            statuss("Amit", "https://placekitten.com/401/401", false),
            statuss("Neha", "https://sample-videos.com/video123/mp4/720/sample-5s.mp4", true),
            statuss("Karan", "https://placekitten.com/402/402", false)
        )

    val context = LocalContext.current
    val locationhelper = remember { locationhelper(context) }
    var location by remember {mutableStateOf("Roorkee")}
    val coroutineScope = rememberCoroutineScope()
    val locationPermision = rememberPermissionState(Manifest.permission.ACCESS_FINE_LOCATION)

    var pp by remember { mutableStateOf("") }
    var selectedstory by remember { mutableStateOf<statuss?>(null) }
    Column(Modifier.fillMaxWidth().fillMaxHeight().background(Brush.linearGradient(colors =
        listOf(
            Color(0xFF86CFC2),
            Color(0xFFFBF7E7),
            Color(0xFFC1DFE3),
            Color(0xFFECF8F9),
            Color(0xFFFAFCFD)
        ))))
    {
        Row(modifier = Modifier.background(Color.Magenta).statusBarsPadding().padding(start = 20.dp, end = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            var isClicked by remember { mutableStateOf(false) }

            if (pp.isEmpty()){
    Icon(
        imageVector = Icons.Default.AccountCircle,
        contentDescription = "Default Profile",
        modifier = Modifier.size(32.dp).clickable(onClick = {navController.navigate("profile")}),
        tint = Color.Blue
    )
}
else{
    Image(
        painter = rememberAsyncImagePainter(
            model = pp
        ),
        contentDescription = "Profile Picture",
        modifier = Modifier
            .size(32.dp)
            .clip(CircleShape).clickable(onClick = {navController.navigate("profile")}),
    )
}


            IconButton(onClick = {
             if (locationPermision.status.isGranted){
                 coroutineScope.launch {
                     location = locationhelper.getlocation()
                 }

             }

                else{
                    locationPermision.launchPermissionRequest()
             }


            }) {
                Icon(Icons.Default.LocationOn,
                    "Location",
                    tint = Color.Blue,
                    modifier = Modifier.size(32.dp))
            }
            Text(location, color = Color.Blue, fontSize = 10.sp )
            Spacer(Modifier.weight(1f))
            Button(
                onClick = { isClicked = !isClicked },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isClicked) Color.Red
                    else Color(0xFF2196F3) // blue
                ),
                modifier = Modifier.padding(3.dp)
            ) {
                Text(text = "Right Now", fontSize = 10.sp, color = Color.White)
            }
        }

//        LazyRow (Modifier.fillMaxWidth().padding(4.dp)) {
//items(stories.size){
//    st ->
//    storylist(stories[st]){
//        selectedstory = stories[st]
//    }
//}
//
//
//        }

       



        LazyVerticalGrid(GridCells.Fixed(2),
            Modifier.fillMaxSize()
                .background(Color.Black). padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp)){
            items(sm.size){
                cardview(sm[it])
            }
        }
    }
}

@Composable
private fun storylist(
    statuss: statuss,
    onclick: () -> Unit
) {
    Column(Modifier.width(50.dp).clickable(onClick = {onclick()}), horizontalAlignment = Alignment.CenterHorizontally) {
        Box(Modifier.size(40.dp).border(BorderStroke(4.dp, Color.Red), shape = CircleShape).padding(1.dp)){
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(statuss.mediaimg)
                    .crossfade(enable = true)
                    .build(),
                contentDescription = statuss.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(CircleShape)
            )
        }
        Spacer(modifier = Modifier.height(6.dp))

        Text(text = statuss.name.take(3), fontSize = 14.sp)
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun cardview(person: PersonCard) {
    val pagerState = rememberPagerState(pageCount = { person.images.size })

    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        modifier = Modifier
            .aspectRatio(1f)
            .fillMaxWidth()
            .padding(4.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {

            HorizontalPager(state = pagerState, modifier = Modifier.fillMaxSize()) { page ->
                AsyncImage(
                    model = person.images[page],
                    contentDescription = person.name,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(12.dp)),
                    contentScale = ContentScale.Crop
                )
            }

            Box(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .fillMaxWidth()
                    .background(
                        brush = Brush.verticalGradient(
                            listOf(
                                Color.Transparent,
                                Color.Black.copy(alpha = 0.7f)
                            )
                        )
                    )
                    .padding(vertical = 8.dp, horizontal = 10.dp)
            ) {
                Column(
                    modifier = Modifier.align(Alignment.BottomStart),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = person.name,
                        fontSize = 14.sp,
                        color = Color.White
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "${person.age}y • ${person.gender[0]}",
                            fontSize = 12.sp,
                            color = Color.LightGray
                        )
                        Spacer(modifier = Modifier.weight(1f))

                        Box(
                            modifier = Modifier
                                .background(
                                    if (person.isRightNow) Color.Red else Color.Transparent.copy(alpha = 0.0f),

                                    shape = CircleShape
                                )
                                .size(10.dp)
                        )
                    }
                }
            }
        }
    }
}


//@Composable
//@Preview(showBackground = true)
//fun homeshow(){
//    Home(navController)
//}