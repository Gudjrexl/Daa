package com.jrexl.daa.Othersourcescreen

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.CachePolicy
import coil.request.ImageRequest
import java.io.File
import java.io.FileOutputStream

@Composable
fun profile(navController: NavHostController) {
    var name by remember { mutableStateOf("Guddu") }
    var phone by remember { mutableStateOf("9876543210") }
var gender by remember { mutableStateOf("Male") }
var aga by remember{mutableStateOf("20")}
    var orientation by remember { mutableStateOf("Single") }
var hobby by remember { mutableStateOf("Reading Books") }
    var preferences by remember { mutableStateOf("Casual") }
var religion by remember { mutableStateOf("Hindu") }
    var status by remember { mutableStateOf("Married") }
    var movies by remember { mutableStateOf("Action") }
    var music by remember { mutableStateOf("Pop") }
    var generic by remember { mutableStateOf("Football") }
    var sports by remember { mutableStateOf("Cricket") }



    Column(Modifier.fillMaxWidth().fillMaxHeight().background(Brush.linearGradient(colors =
        listOf(
            Color(0xFF86CFC2),
            Color(0xFFFBF7E7),
            Color(0xFFC1DFE3),
            Color(0xFFECF8F9),
            Color(0xFFFAFCFD)
        ))).verticalScroll(rememberScrollState()).padding(bottom = 8.dp))
    {
        Row(modifier = Modifier.fillMaxWidth().background(Color.Magenta).statusBarsPadding().padding(start = 20.dp, end = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Text("Profile", fontSize = 20.sp)
        }
        ProfileImagePicker()
        Spacer(Modifier.height(10.dp))
        details(navController,"Name", name)
        Spacer(Modifier.height(13.dp))

        details(navController, "Phone", phone)
        Spacer(Modifier.height(13.dp))

        details(navController, "Gender", gender)
        Spacer(Modifier.height(13.dp))

        details(navController, "Age", aga)
        Spacer(Modifier.height(13.dp))

        details(navController, "Orientation", orientation)
        Spacer(Modifier.height(13.dp))

        details(navController, "Hobby", hobby)
        Spacer(Modifier.height(13.dp))

        details(navController, "Preferences", preferences)
        Spacer(Modifier.height(13.dp))

        details(navController, "Religion", religion)
        Spacer(Modifier.height(13.dp))

        details(navController, "Status", status)
        Spacer(Modifier.height(13.dp))

        details(navController, "Movies", movies)
        Spacer(Modifier.height(13.dp))

        details(navController, "Music", music)
        Spacer(Modifier.height(13.dp))

        details(navController, "Generic", generic)
        Spacer(Modifier.height(13.dp))

        details(navController, "Sports", sports)
        Spacer(Modifier.height(13.dp))


    }
}

@Composable
private fun details(
    navController: NavHostController,
    title: String,
    titfiled: String
) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(start = 20.dp, end = 20.dp).clickable(onClick = {navController.navigate("editprofile/${Uri.encode(title)}/${Uri.encode(titfiled)}")})) {
        Column() {
            Text(title, fontSize = 15.sp,color = Color.Black.copy(alpha = 0.5f))
            Spacer(Modifier.height(3.dp))
            Text(titfiled, fontSize = 13.sp,color = Color.Black)
        }
        Spacer(Modifier.weight(1f))
        Icon(
            imageVector = Icons.Default.ChevronRight,
            contentDescription = "arrow",
        )
    }

}


@Composable
fun ProfileImagePicker() {
    val context = LocalContext.current
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }

//    LaunchedEffect(Unit) {
//        contactno.value = DataStoreManager.getUserPhone(context)
//    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        selectedImageUri = uri

//        if (uri != null && contactno.value != null) {
//            val file = uriToFile(context, uri)
//            vm.Updateprofilepic(
//                context = context,
//                contactno = contactno.value!!,
//                imageFile = file
//            )
//        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Box(
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
                .background(Color(0xFFF5F5F5))
                .clickable { launcher.launch("image/*") },
            contentAlignment = Alignment.Center
        ) {
//            val imageUrl = "${Constf.BASE_URL.trimEnd('/')}/profilepic/${vm.profilevm?.ProfilePic.orEmpty()}"
            val imageUrl = ""
            if (imageUrl.isNotEmpty()){
                Log.d("ProfilePicURL", "Image URL = $imageUrl")
                AsyncImage(
                    ImageRequest.Builder(context)
                        .data(imageUrl)
                        .crossfade(true)
                        .diskCachePolicy(CachePolicy.ENABLED)
                        .memoryCachePolicy(CachePolicy.ENABLED)
                        .build(),
                    contentDescription = null,

                    modifier = Modifier.padding(8.dp).clip(CircleShape).size(50.dp)
                )
            }


            if (selectedImageUri != null) {
                Image(
                    painter = rememberAsyncImagePainter(model = selectedImageUri),
                    contentDescription = "Selected Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            } else {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add",
                    modifier = Modifier.size(40.dp),
                    tint = Color.DarkGray
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Profile pic",
            fontSize = 14.sp,
            color = Color.Gray,
            textAlign = TextAlign.Center
        )
    }
}

fun uriToFile(context: Context, uri: Uri): File {
    val inputStream = context.contentResolver.openInputStream(uri)
    val file = File(context.cacheDir, "profile_${System.currentTimeMillis()}.jpg")
    inputStream?.use { input ->
        FileOutputStream(file).use { output -> input.copyTo(output) }
    }
    return file
}



