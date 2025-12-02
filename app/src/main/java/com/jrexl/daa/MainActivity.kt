package com.jrexl.daa

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jrexl.daa.datastore.StoreLogininfo
import com.jrexl.daa.viewmodels.VmPersonalInfo
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        lifecycleScope.launch {
            val isloggedin = StoreLogininfo.isLoggedIn(this@MainActivity)
            var phone = StoreLogininfo.getMobile(this@MainActivity)?.first()
            if (isloggedin){
                var inte = Intent(this@MainActivity, Homepage::class.java)
                startActivity(inte)
                finish()
        }
            else{
                setContent {
                    loginpage()
                }
            }



    }
}}

@Composable
fun loginpage(){
    var signup by remember { mutableStateOf(false) }
    if (signup){
        signuppage(){
            signup = false
        }
    }else{
        loginp(){
            signup = true
        }
    }

}

@Composable
 fun signuppage(vm: VmPersonalInfo = viewModel() , onlogin: () -> Unit) {
    var phone by remember { mutableStateOf("") }
    var context = LocalContext.current
    var otp by remember { mutableStateOf("") }
    var showotp by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    var isLoading by remember { mutableStateOf(false) }
    var password by remember { mutableStateOf(false) }
    var pass by remember { mutableStateOf("") }
    var conpass by remember { mutableStateOf("") }



    Column(Modifier.fillMaxWidth().fillMaxHeight().background(Brush.linearGradient(colors =
        listOf(
            Color(0xFF86CFC2),
            Color(0xFFFBF7E7),
            Color(0xFFC1DFE3),
            Color(0xFFECF8F9),
            Color(0xFFFAFCFD)
        ))).navigationBarsPadding())
    {
        Row(modifier = Modifier.statusBarsPadding().padding(start = 20.dp)){
        }
        Spacer(Modifier.height(8.dp))
        Row(modifier = Modifier.padding(start = 20.dp, top = 1.dp)){
            titlesingle("Get Started - Sign Up", color = Color(0xFF006400))
        }

        Spacer(Modifier.height(5.dp))
            OutlinedTextField(
                value = phone,
                onValueChange = { phone = it },

                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                label = { Text("Phone") },
                singleLine = true,
                leadingIcon = {Text("+91")},
                modifier = Modifier.fillMaxWidth().padding(start = 10.dp, end = 10.dp, bottom = 5.dp)


            )



        Spacer(Modifier.height(16.dp))
        Button(onClick = {
            Log.d("phone", phone)
            isLoading = true
            showotp = true
            Log.d("phone", phone)
            vm.sendotp(phone){

                res ->
                Log.d("phone", "phone received")

                isLoading = false
                if (res == "s"){
                    isLoading = false

                }
                else{
                    isLoading = false
                    Toast.makeText(context, res, Toast.LENGTH_SHORT).show()
                }

            }

        },

            Modifier.fillMaxWidth().height(40.dp).clip(RoundedCornerShape(20.dp)).padding(end = 10.dp, start = 10.dp),
            colors = ButtonDefaults.buttonColors(contentColor = Color.Blue.copy(0.5f),
                containerColor = Color.Red.copy(0.6f))) {
            Text("Send otp")
        }
        Row(Modifier.padding(10.dp).clickable(onClick = {onlogin()})) {
            Spacer(Modifier.weight(1f))
            Text("Already have an account")
            Text("Login", color = Color.Blue)
        }
        if (showotp){
            Text(text = "Enter Otp",
                style = TextStyle(
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    brush = Brush.linearGradient(listOf(Color(0xFF6A11CB), Color(0xFF2575FC)))
                ),
                modifier = Modifier.padding(start = 10.dp, top = 5.dp, bottom = 5.dp))

            OutlinedTextField(
                value = otp,
                onValueChange = { otp = it },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                label = { Text("Enter Otp") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth().padding(start = 10.dp, end = 10.dp, bottom = 5.dp)

            )

            Spacer(Modifier.height(16.dp))
            Button(onClick = {
                isLoading = true
                password = true
                vm.verfiyotp(phone, otp){
                    res->
                    showotp = false
                    if (res == "s"){
                        isLoading = false
                        password = true

                    }
                    else{
                        Toast.makeText(context, res.toString(), Toast.LENGTH_LONG).show()
                    }

                }
                val phonen = "+91" + phone.trim()

            },
                Modifier.fillMaxWidth().height(40.dp).clip(RoundedCornerShape(20.dp)).padding(end = 10.dp, start = 10.dp),
                colors = ButtonDefaults.buttonColors(contentColor = Color.Blue.copy(0.5f),
                    containerColor = Color.Red.copy(0.6f))
            ) {
                Text("verify")
            }
        }
            Spacer(Modifier.height(16.dp))

        if(password) {


            OutlinedTextField(
                value = pass,
                onValueChange = { pass = it },

                label = { Text("Enter Password") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp, bottom = 5.dp)

            )

            Spacer(Modifier.height(16.dp))

            OutlinedTextField(
                value = conpass,
                onValueChange = { conpass = it },

                label = { Text("Enter Confirm password") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp, bottom = 5.dp)

            )

            Spacer(Modifier.height(16.dp))
            val ctx = context
            val scope = rememberCoroutineScope()
            Button(
                onClick = {
                    isLoading = true
                    if(pass == conpass){
                        vm.passwordSet(phone, pass){

                            res ->
                            isLoading = false

                            if (res == "s"){
                                isLoading = false

                                scope.launch {
                                    StoreLogininfo.isLoggedIn(ctx)
                                    StoreLogininfo.saveLogin(context, phone)

                                }
                                isLoading = false

                                var inte = Intent(context, Homepage::class.java)
                                context.startActivity(inte)
                                (context as? Activity)?.finish()
                            }
                            else{
                                isLoading =false
                                Toast.makeText(context, res.toString(), Toast.LENGTH_LONG).show()
                            }

                        }






                    }
                    else{
                        isLoading = false
                        Toast.makeText(context, "Password mismatch try again", Toast.LENGTH_LONG).show()
                    }





                },
                Modifier.fillMaxWidth().height(40.dp).clip(RoundedCornerShape(20.dp))
                    .padding(end = 10.dp, start = 10.dp),
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.Blue.copy(0.5f),
                    containerColor = Color.Red.copy(0.6f)
                )
            ) {
                Text("Login")
            }

        }

            LoadingOverlay(isLoading)


    }}

@Composable
fun LoadingOverlay(isLoading: Boolean, message: String = "Please wait...") {
    if (isLoading) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(Color(0x80000000)),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                CircularProgressIndicator(color = Color.White)
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = message,
                    color = Color.White
                )
            }
        }

    }
}

@Composable
fun loginp(vm: VmPersonalInfo = viewModel() ,onsignin: () -> Unit) {
    val scope = rememberCoroutineScope()
    var isLoading by remember { mutableStateOf(false) }
    var phone by remember { mutableStateOf("") }
    var pass by remember { mutableStateOf("") }
    var context = LocalContext.current


    Column(Modifier.fillMaxWidth().fillMaxHeight().background(Brush.linearGradient(colors =
        listOf(
            Color(0xFF86CFC2),
            Color(0xFFFBF7E7),
            Color(0xFFC1DFE3),
            Color(0xFFECF8F9),
            Color(0xFFFAFCFD)
        ))).navigationBarsPadding())
    {
        Row(modifier = Modifier.statusBarsPadding().padding(start = 20.dp)){
        }
            OutlinedTextField(
                value = phone,
                onValueChange = { phone = it },

                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                label = { Text("Phone") },
                singleLine = true,
                leadingIcon = {Text("+91")},
                modifier = Modifier.fillMaxWidth().padding(start = 10.dp, end = 10.dp, bottom = 5.dp)


            )


        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = pass,
            onValueChange = { pass = it },

            label = { Text("Enter Password") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth().padding(start = 10.dp, end = 10.dp, bottom = 5.dp)

        )
        Spacer(Modifier.height(16.dp))


        Button(onClick = {
            isLoading = true


            vm.passwordSet(phone, pass){

                    res ->
                isLoading = false

                if (res == "s"){
                    isLoading = false

                    scope.launch {
                        StoreLogininfo.isLoggedIn(context)
                        StoreLogininfo.saveLogin(context, phone)

                    }
                    isLoading = false

                    var inte = Intent(context, Homepage::class.java)
                    context.startActivity(inte)
                    (context as? Activity)?.finish()
                }
                else{
                    isLoading =false
                    Toast.makeText(context, res.toString(), Toast.LENGTH_LONG).show()
                }

            }


        },
            Modifier.fillMaxWidth().height(40.dp).clip(RoundedCornerShape(20.dp)).padding(end = 10.dp, start = 10.dp),
            colors = ButtonDefaults.buttonColors(contentColor = Color.Blue.copy(0.5f),
                containerColor = Color.Red.copy(0.6f))
        ) {
            Text("Login")
        }

        Row(Modifier.padding(10.dp).clickable(onClick = {onsignin()})) {
            Spacer(Modifier.weight(1f))
            Text("New User")
            Spacer(Modifier.width(5.dp))
            Text("SignUp/Forget password", color = Color.Blue)
        }

        LoadingOverlay(isLoading)

    }


}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {

        loginpage()

}


@Composable
fun titlesingle(heading: String, color: Color = Color.Black){
    Text(heading, color = color, fontSize = 22.sp, fontWeight = FontWeight.Medium)

}


@Composable
fun requiretf(title: String, color: Color = Color(0xFF8A8A8A)){
    Row {
        Text(title, color = color, fontSize = 20.sp, fontWeight = FontWeight.Normal)
        Text("*", color = Color.Red.copy(alpha = .6f), fontSize = 20.sp, fontWeight = FontWeight.Normal)

    }
}


@Composable
fun SimpleUnderlinedTextField(
    place: String,
    onInputChange: (String) -> Unit,
) {
    var text by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxWidth()) {
        BasicTextField(
            value = text,
            onValueChange = {
                text = it
                onInputChange(it)
            },
            singleLine = true,
            textStyle = TextStyle(
                color = Color(0xFF4F4F4F),
                fontSize = 16.sp,
                lineHeight = 18.sp
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
        ) { innerTextField ->
            if (text.isEmpty()) {
                Text(
                    text = place,
                    color = Color(0xFF4F4F4F),
                    fontSize = 16.sp,
                    lineHeight = 18.sp
                )
            }
            innerTextField()
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Color.Gray)
        )
    }
}