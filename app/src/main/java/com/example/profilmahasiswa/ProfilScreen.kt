
package com.example.profilmahasiswa

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ProfilScreen(
    onBack: () -> Unit,
    nama: String,
    nim: String,
    prodi: String
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F7FB))
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "👨‍🎓",
            fontSize = 70.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "PROFIL MAHASISWA",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF173B75)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text("Nama : $nama", fontSize = 16.sp)
                Text("NIM : $nim", fontSize = 16.sp)
                Text("Program Studi : $prodi", fontSize = 16.sp)
                Text("Universitas : Universitas Mulia", fontSize = 16.sp)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = onBack,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Kembali ke Beranda")
        }
    }
}
