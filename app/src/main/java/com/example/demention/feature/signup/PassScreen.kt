package com.example.demention.feature.signup

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.demention.component.TextField
import com.example.demention.navigation.AppNavigationItem
import com.example.demention.ui.theme.background
import com.example.demention.ui.theme.body1
import com.example.demention.ui.theme.headline3
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun PassScreen(navController: NavController) {
    val passViewModel = remember { PassViewModel() }
    var navState by remember { mutableStateOf(false) }
    var phoneNumberState by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = navState) {
        if (navState) {
            navController.navigate(AppNavigationItem.Location.route) { popUpTo(0) }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(background)
            .padding(horizontal = 12.dp)
    ) {
        Text(
            text = "휴대전화 번호로 인증",
            style = headline3,
            modifier = Modifier.padding(vertical = 16.dp),
            fontWeight = FontWeight.Bold
        )
        if (!phoneNumberState) {
            TextField(
                text = passViewModel.phoneNumber.value,
                onTextChange = {
                    passViewModel.handleUpdatePhoneNumber(it)
                },
                placeholder = "전화번호(-없이 입력)",
            )
            Box(
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .fillMaxWidth()
                    .height(50.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .border(
                        shape = RoundedCornerShape(8.dp),
                        width = 1.dp,
                        color = Color.LightGray,
                    )
                    .background(Color.White)
                    .clickable {
                        if (passViewModel.phoneNumber.value.length > 9) {
                            CoroutineScope(Dispatchers.IO).launch {
                                passViewModel.postCode(passViewModel.phoneNumber.value)
                            }
                        }
                        phoneNumberState = true
                    }
            ) {
                Text(
                    text = "인증문자 받기",
                    style = body1,
                    modifier = Modifier.align(Alignment.Center),
                )
            }
        } else {
            TextField(
                text = passViewModel.code.value,
                onTextChange = {
                    passViewModel.handleUpdateCode(it)
                },
                placeholder = "인증번호",
            )
            Box(
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .fillMaxWidth()
                    .height(50.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .border(
                        shape = RoundedCornerShape(8.dp),
                        width = 1.dp,
                        color = Color.LightGray,
                    )
                    .background(Color.White)
                    .clickable {
                        CoroutineScope(Dispatchers.IO).launch {
                            passViewModel.checkCode(
                                code = passViewModel.code.value,
                                phoneNumber = passViewModel.phoneNumber.value,
                            )
                            Log.d("pass","${passViewModel.code.value},${passViewModel.phoneNumber.value}")
                        }
                        navState = true
                    }
            ) {
                Text(
                    text = "인증 하기",
                    style = body1,
                    modifier = Modifier.align(Alignment.Center),
                )
            }
        }
    }
}