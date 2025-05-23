package com.example.firezon

import android.util.Log
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.firezon.ui.theme.manropeFontFamily
import com.example.firezon.ui.theme.systemText
import com.example.firezon.ui.theme.titleLarge

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
           ContactScreen()
        }
    }
}


@Composable
fun ContactScreen() {
    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
    ) {
        Header(title = "Новый контакт")
        User(content = "Content string")

        // Состояния для каждого поля
        var name by remember { mutableStateOf("") }
        var company by remember { mutableStateOf("") }
        var phone by remember { mutableStateOf("") }

        InputBlock(
            name = name,
            onNameChange = { name = it },
            company = company,
            onCompanyChange = { company = it },
            phone = phone,
            onPhoneChange = { phone = it }
        )

        Spacer(modifier = Modifier.weight(1f))
        SaveButton(
            name = name,
            company = company,
            phone = phone
        )
    }
}



@Composable
fun Header(title: String,
//           onBackClick: () -> Unit)
){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 4.dp,
                end = 4.dp,
                top = 8.dp,
                bottom = 8.dp
            )


    ) {
        Box(
            modifier = Modifier.size(48.dp),
            contentAlignment = Alignment.Center) {

            Image(
                painter = painterResource(id = R.drawable.arrow_left),
                contentDescription = "стрелочка",
                modifier = Modifier
//                    .clickable { onBackClick() }
            )
        }
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = title,
            style = systemText,
        )
    }
}


@Composable
fun User(content: String){
    Column (

        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 16.dp,
                end = 16.dp,
                top = 8.dp,
                bottom = 8.dp
                ),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Box(
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .background(Color(0xFF32BA76).copy(alpha = 0.15f)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text="A",
                color = Color.Black.copy(alpha = 0.7f),
                fontFamily = manropeFontFamily,
                fontSize = 57.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = content,
            style = titleLarge,
        )
    }
}

@Composable
fun InputBlock(
    name: String,
    onNameChange: (String) -> Unit,
    company: String,
    onCompanyChange: (String) -> Unit,
    phone: String,
    onPhoneChange: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        InputField(inform = "Имя", value = name, onValueChange = onNameChange)
        Spacer(modifier = Modifier.height(16.dp))
        InputField(inform = "Компания", value = company, onValueChange = onCompanyChange)
        Spacer(modifier = Modifier.height(16.dp))
        InputField(inform = "Телефон", value = phone, onValueChange = onPhoneChange, keyboardType = KeyboardType.Phone)
    }
}


@Composable
fun InputField(
    inform: String,
    value: String,
    onValueChange: (String) -> Unit,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxSize(),
            textStyle = TextStyle(
                fontSize = 16.sp,
                color = Color.Black
            ),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = keyboardType
            ),

            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Color.Black.copy(alpha = 0.15f) // <- цвет при отсутствии фокуса
            )
        )

        Text(
            modifier = Modifier
                .offset(x = 12.dp, y = (-12).dp)
                .background(Color.White)
                .padding(
                    horizontal = 4.dp
                )
            ,

            text=inform,
            color = Color.Black.copy(alpha = 0.7f),
            fontFamily = manropeFontFamily,
            fontSize = 12.sp,
            letterSpacing = 0.4.sp,

            )


    }

}


@Composable
fun SaveButton(
    name: String,
    company: String,
    phone: String
) {
    Button(
        onClick = {
            Log.d("ContactForm", "Имя: $name, Компания: $company, Телефон: $phone")
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, bottom = (16+8).dp ) // <- загадочные 8px в figma
            .height(56.dp),
        shape = RoundedCornerShape(100.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFFFC107),
            contentColor = Color.Black
        )
    ) {
        Text(
            text = "Добавить контакт",
            fontSize = 16.sp,
            fontFamily = manropeFontFamily
        )
    }
}
