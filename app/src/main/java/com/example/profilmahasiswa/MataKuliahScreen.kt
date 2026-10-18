
package com.example.profilmahasiswa

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MataKuliahScreen(
    onBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F7FB))
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Text(
            text = "📚 Detail Mata Kuliah",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF173B75)
        )

        Text(
            text = "Universitas Mulia",
            fontSize = 16.sp,
            color = Color.Gray
        )

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
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = "Pemrograman Aplikasi Bergerak",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF173B75)
                )

                HorizontalDivider()

                Text("Program Studi : S1 Informatika")
                Text("Materi : Navigation, Lifecycle & ViewModel")

                Text(
                    text = "Deskripsi Mata Kuliah",
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Mempelajari pengembangan aplikasi Android " +
                            "dengan navigasi antar halaman, pengelolaan " +
                            "lifecycle, dan penggunaan ViewModel untuk " +
                            "menyimpan serta mengelola UI State."
                )
            }
        }

        Text(
            text = "Materi Pembelajaran",
            fontSize = 19.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF173B75)
        )

        val materi = listOf(
            "1. Konsep Navigation",
            "2. Destination dan Route",
            "3. NavController dan Back Stack",
            "4. Android Lifecycle",
            "5. ViewModel sebagai State Holder",
            "6. UI State, Event, dan Logic"
        )

        materi.forEach { item ->
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = item,
                    modifier = Modifier.padding(16.dp),
                    fontSize = 15.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

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
