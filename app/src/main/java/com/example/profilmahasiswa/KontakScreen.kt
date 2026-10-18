
package com.example.profilmahasiswa

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
fun KontakScreen(
    onBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F7FB))
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "📱",
            fontSize = 70.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "KONTAK MAHASISWA",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF173B75)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Informasi Kontak Mahasiswa",
            fontSize = 15.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(24.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 3.dp
            )
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "Informasi Mahasiswa",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF173B75)
                )

                HorizontalDivider()

                Text("👤 Nama: Wisnu Wardhana")
                Text("🎓 NIM: 2411037")
                Text("🏫 Program Studi: S1 Informatika")
                Text("🏛 Universitas: Universitas Mulia")
            }
        }

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
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = "Kontak",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF173B75)
                )

                HorizontalDivider()

                Text("Email: Belum diisi")
                Text("Telepon: Belum diisi")
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = onBack,
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("Kembali ke Beranda")
        }
    }
}
