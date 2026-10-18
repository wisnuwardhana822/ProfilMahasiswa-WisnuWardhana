
package com.example.profilmahasiswa

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

// ============================
// WARNA APLIKASI
// ============================

private val Navy = Color(0xFF183568)
private val Blue = Color(0xFF4169AD)
private val LightBlue = Color(0xFFEAF1FC)
private val PageBg = Color(0xFFF5F7FC)
private val DarkText = Color(0xFF172B4D)
private val MutedText = Color(0xFF64748B)
private val Green = Color(0xFF15976A)

// ============================
// VIEWMODEL
// ============================

class MahasiswaViewModel : ViewModel() {

    var nama by mutableStateOf("Wisnu Wardhana")
        private set

    var nim by mutableStateOf("2411037")
        private set

    var prodi by mutableStateOf("S1 Informatika")
        private set

    var email by mutableStateOf("")
        private set

    var telepon by mutableStateOf("")
        private set

    val materi = listOf(
        "Konsep Navigation",
        "Destination dan Route",
        "NavController dan Back Stack",
        "Android Lifecycle",
        "ViewModel sebagai State Holder",
        "UI State, Event, dan Logic"
    )

    private val statusMateri = mutableStateListOf(
        false, false, false, false, false, false
    )

    val jumlahSelesai: Int
        get() = statusMateri.count { it }

    val totalMateri: Int
        get() = materi.size

    fun sudahSelesai(index: Int): Boolean {
        return statusMateri[index]
    }

    fun ubahStatusMateri(index: Int, selesai: Boolean) {
        statusMateri[index] = selesai
    }

    fun simpanProfil(
        namaBaru: String,
        nimBaru: String,
        prodiBaru: String
    ): Boolean {
        if (
            namaBaru.isBlank() ||
            nimBaru.isBlank() ||
            prodiBaru.isBlank()
        ) return false

        nama = namaBaru.trim()
        nim = nimBaru.trim()
        prodi = prodiBaru.trim()
        return true
    }

    fun simpanKontak(
        emailBaru: String,
        teleponBaru: String
    ): Boolean {
        val e = emailBaru.trim()
        val t = teleponBaru.trim()

        if (
            e.isNotBlank() &&
            !android.util.Patterns.EMAIL_ADDRESS.matcher(e).matches()
        ) return false

        if (
            t.isNotBlank() &&
            !t.matches(Regex("[+0-9 ()-]{7,20}"))
        ) return false

        email = e
        telepon = t
        return true
    }
}

// ============================
// MAIN ACTIVITY
// ============================

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            ProfilMahasiswaThemeWrapper()
        }
    }
}

@Composable
fun ProfilMahasiswaThemeWrapper() {
    val colors = lightColorScheme(
        primary = Navy,
        secondary = Blue,
        background = PageBg,
        surface = Color.White,
        onPrimary = Color.White,
        onBackground = DarkText
    )

    MaterialTheme(colorScheme = colors) {
        AplikasiMahasiswa()
    }
}

// ============================
// NAVIGATION
// ============================

@Composable
fun AplikasiMahasiswa(
    vm: MahasiswaViewModel = viewModel()
) {
    val navController = rememberNavController()

    val backStack by
    navController.currentBackStackEntryAsState()

    val route = backStack?.destination?.route ?: "home"

    val menu = listOf(
        Triple("home", "Beranda", "⌂"),
        Triple("profil", "Profil", "👤"),
        Triple("matakuliah", "Kuliah", "📚"),
        Triple("kontak", "Kontak", "✉")
    )

    Scaffold(
        containerColor = PageBg,

        topBar = {
            Surface(
                color = Color.White,
                shadowElevation = 2.dp
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .statusBarsPadding()
                        .height(64.dp)
                        .padding(horizontal = 20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(42.dp)
                            .background(
                                LightBlue,
                                RoundedCornerShape(12.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("🎓", fontSize = 23.sp)
                    }

                    Spacer(Modifier.width(12.dp))

                    Column {
                        Text(
                            "MY STUDENT",
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Bold,
                            color = Navy
                        )
                        Text(
                            "Universitas Mulia",
                            fontSize = 12.sp,
                            color = MutedText
                        )
                    }
                }
            }
        },

        bottomBar = {
            NavigationBar(
                containerColor = Color.White,
                tonalElevation = 4.dp
            ) {
                menu.forEach { item ->
                    NavigationBarItem(
                        selected = route == item.first,
                        onClick = {
                            if (route != item.first) {
                                navController.navigate(item.first) {
                                    popUpTo("home") {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        },
                        icon = {
                            Text(
                                item.third,
                                fontSize = 21.sp
                            )
                        },
                        label = {
                            Text(
                                item.second,
                                fontSize = 11.sp
                            )
                        },
                        colors =
                            NavigationBarItemDefaults.colors(
                                selectedIconColor = Navy,
                                selectedTextColor = Navy,
                                indicatorColor = LightBlue
                            )
                    )
                }
            }
        }
    ) { padding ->

        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(padding)
        ) {

            composable("home") {
                BerandaFinal(
                    vm = vm,
                    bukaProfil = {
                        navController.navigate("profil")
                    },
                    bukaMataKuliah = {
                        navController.navigate("matakuliah")
                    },
                    bukaKontak = {
                        navController.navigate("kontak")
                    }
                )
            }

            composable("profil") {
                ProfilFinal(vm)
            }

            composable("matakuliah") {
                MataKuliahFinal(vm)
            }

            composable("kontak") {
                KontakFinal(vm)
            }
        }
    }
}

// ============================
// KOMPONEN UMUM
// ============================

@Composable
fun Halaman(
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(PageBg)
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        content = content
    )
}

@Composable
fun JudulHalaman(
    judul: String,
    deskripsi: String
) {
    Text(
        judul,
        fontSize = 25.sp,
        fontWeight = FontWeight.Bold,
        color = Navy
    )

    Text(
        deskripsi,
        fontSize = 14.sp,
        color = MutedText
    )
}

@Composable
fun Kartu(
    content: @Composable ColumnScope.() -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            content = content
        )
    }
}

@Composable
fun BarisInfo(label: String, nilai: String) {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            label,
            fontSize = 12.sp,
            color = MutedText
        )

        Text(
            nilai,
            fontSize = 15.sp,
            fontWeight = FontWeight.SemiBold,
            color = DarkText
        )
    }
}

@Composable
fun TombolMenu(
    emoji: String,
    judul: String,
    deskripsi: String,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(17.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(
                        LightBlue,
                        RoundedCornerShape(13.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(emoji, fontSize = 24.sp)
            }

            Spacer(Modifier.width(14.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    judul,
                    fontWeight = FontWeight.Bold,
                    color = Navy,
                    fontSize = 16.sp
                )

                Text(
                    deskripsi,
                    color = MutedText,
                    fontSize = 12.sp
                )
            }

            Text(
                "›",
                fontSize = 28.sp,
                color = Blue
            )
        }
    }
}

// ============================
// HALAMAN BERANDA
// ============================

@Composable
fun BerandaFinal(
    vm: MahasiswaViewModel,
    bukaProfil: () -> Unit,
    bukaMataKuliah: () -> Unit,
    bukaKontak: () -> Unit
) {
    Halaman {

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(
                containerColor = Navy
            )
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    "SELAMAT DATANG 👋",
                    color = Color(0xFFC9D8F4),
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium
                )

                Text(
                    vm.nama,
                    color = Color.White,
                    fontSize = 27.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    "${vm.nim} • ${vm.prodi}",
                    color = Color(0xFFE4ECFC),
                    fontSize = 14.sp
                )

                HorizontalDivider(
                    color = Color.White.copy(alpha = 0.25f)
                )

                Text(
                    "Dashboard Akademik",
                    color = Color.White,
                    fontSize = 16.sp
                )

                Text(
                    "Kelola profil dan pantau progres " +
                            "materi pembelajaran Anda.",
                    color = Color(0xFFE4ECFC),
                    fontSize = 13.sp
                )
            }
        }

        Kartu {
            Text(
                "Progres Pembelajaran",
                fontSize = 17.sp,
                color = Navy,
                fontWeight = FontWeight.Bold
            )

            val progress =
                vm.jumlahSelesai.toFloat() / vm.totalMateri

            LinearProgressIndicator(
                progress = { progress },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(9.dp),
                color = Green,
                trackColor = LightBlue
            )

            Text(
                "${vm.jumlahSelesai} dari " +
                        "${vm.totalMateri} materi selesai",
                color = MutedText,
                fontSize = 13.sp
            )
        }

        Text(
            "Menu Utama",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Navy
        )

        TombolMenu(
            emoji = "👤",
            judul = "Profil Mahasiswa",
            deskripsi = "Lihat dan edit biodata mahasiswa",
            onClick = bukaProfil
        )

        TombolMenu(
            emoji = "📚",
            judul = "Detail Mata Kuliah",
            deskripsi = "Lihat materi dan progres belajar",
            onClick = bukaMataKuliah
        )

        TombolMenu(
            emoji = "✉",
            judul = "Kontak Mahasiswa",
            deskripsi = "Kelola email dan nomor telepon",
            onClick = bukaKontak
        )

        Text(
            "Profil Mahasiswa • Versi 1.0",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            color = MutedText,
            fontSize = 12.sp
        )
    }
}

// ============================
// HALAMAN PROFIL
// ============================

@Composable
fun ProfilFinal(vm: MahasiswaViewModel) {

    var sedangEdit by rememberSaveable {
        mutableStateOf(false)
    }

    var inputNama by remember(vm.nama, sedangEdit) {
        mutableStateOf(vm.nama)
    }

    var inputNim by remember(vm.nim, sedangEdit) {
        mutableStateOf(vm.nim)
    }

    var inputProdi by remember(vm.prodi, sedangEdit) {
        mutableStateOf(vm.prodi)
    }

    var pesanError by remember {
        mutableStateOf(false)
    }

    Halaman {

        JudulHalaman(
            "Profil Mahasiswa",
            "Informasi identitas akademik"
        )

        Kartu {

            Box(
                modifier = Modifier
                    .size(84.dp)
                    .background(LightBlue, CircleShape)
                    .align(Alignment.CenterHorizontally),
                contentAlignment = Alignment.Center
            ) {
                Text("👨‍🎓", fontSize = 45.sp)
            }

            Text(
                vm.nama,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Navy,
                modifier = Modifier.align(
                    Alignment.CenterHorizontally
                )
            )

            Text(
                vm.prodi,
                fontSize = 14.sp,
                color = MutedText,
                modifier = Modifier.align(
                    Alignment.CenterHorizontally
                )
            )

            HorizontalDivider()

            if (sedangEdit) {

                OutlinedTextField(
                    value = inputNama,
                    onValueChange = { inputNama = it },
                    label = { Text("Nama Lengkap") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                OutlinedTextField(
                    value = inputNim,
                    onValueChange = { inputNim = it },
                    label = { Text("NIM") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                OutlinedTextField(
                    value = inputProdi,
                    onValueChange = { inputProdi = it },
                    label = { Text("Program Studi") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                if (pesanError) {
                    Text(
                        "Semua kolom wajib diisi.",
                        color = MaterialTheme.colorScheme.error
                    )
                }

                Button(
                    onClick = {
                        if (
                            vm.simpanProfil(
                                inputNama,
                                inputNim,
                                inputProdi
                            )
                        ) {
                            sedangEdit = false
                            pesanError = false
                        } else {
                            pesanError = true
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Simpan Perubahan")
                }

                OutlinedButton(
                    onClick = {
                        sedangEdit = false
                        pesanError = false
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Batal")
                }

            } else {

                BarisInfo("Nama Lengkap", vm.nama)

                HorizontalDivider()

                BarisInfo("Nomor Induk Mahasiswa", vm.nim)

                HorizontalDivider()

                BarisInfo("Program Studi", vm.prodi)

                HorizontalDivider()

                BarisInfo("Perguruan Tinggi", "Universitas Mulia")

                Button(
                    onClick = { sedangEdit = true },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("✎  Edit Profil")
                }
            }
        }

        Kartu {
            Text(
                "Informasi Akademik",
                fontWeight = FontWeight.Bold,
                fontSize = 17.sp,
                color = Navy
            )

            BarisInfo(
                "Mata Kuliah",
                "Pemrograman Aplikasi Bergerak"
            )

            BarisInfo(
                "Topik Pembelajaran",
                "Navigation, Lifecycle & ViewModel"
            )
        }
    }
}

// ============================
// HALAMAN MATA KULIAH
// ============================

@Composable
fun MataKuliahFinal(vm: MahasiswaViewModel) {

    Halaman {

        JudulHalaman(
            "Detail Mata Kuliah",
            "Informasi dan progres pembelajaran"
        )

        Kartu {

            Text(
                "📚 Pemrograman Aplikasi Bergerak",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Navy
            )

            HorizontalDivider()

            BarisInfo(
                "Program Studi",
                vm.prodi
            )

            BarisInfo(
                "Topik",
                "Navigation, Lifecycle & ViewModel"
            )

            Text(
                "Mata kuliah ini membahas pengembangan " +
                        "aplikasi Android multi-screen, " +
                        "pengelolaan siklus hidup aplikasi, " +
                        "serta penggunaan ViewModel untuk " +
                        "mengelola UI State.",
                color = MutedText,
                fontSize = 14.sp
            )
        }

        Kartu {
            Text(
                "Progres Belajar",
                fontWeight = FontWeight.Bold,
                fontSize = 17.sp,
                color = Navy
            )

            LinearProgressIndicator(
                progress = {
                    vm.jumlahSelesai.toFloat() /
                            vm.totalMateri
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(10.dp),
                color = Green,
                trackColor = LightBlue
            )

            Text(
                "${vm.jumlahSelesai} / " +
                        "${vm.totalMateri} materi selesai",
                fontSize = 13.sp,
                color = MutedText
            )
        }

        Text(
            "Daftar Materi",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = Navy
        )

        vm.materi.forEachIndexed { index, judul ->

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(15.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Box(
                        modifier = Modifier
                            .size(39.dp)
                            .background(
                                LightBlue,
                                RoundedCornerShape(10.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            "${index + 1}",
                            color = Navy,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(Modifier.width(12.dp))

                    Text(
                        judul,
                        modifier = Modifier.weight(1f),
                        color = DarkText,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )

                    Checkbox(
                        checked = vm.sudahSelesai(index),
                        onCheckedChange = {
                            vm.ubahStatusMateri(index, it)
                        }
                    )
                }
            }
        }

        Kartu {
            Text(
                "Petunjuk",
                fontWeight = FontWeight.Bold,
                color = Navy
            )

            Text(
                "Centang materi yang sudah dipelajari. " +
                        "Progres akan diperbarui secara otomatis " +
                        "dan ditampilkan pada Dashboard.",
                color = MutedText,
                fontSize = 13.sp
            )
        }
    }
}

// ============================
// HALAMAN KONTAK
// ============================

@Composable
fun KontakFinal(vm: MahasiswaViewModel) {

    val context = LocalContext.current

    var sedangEdit by rememberSaveable {
        mutableStateOf(false)
    }

    var inputEmail by remember(vm.email, sedangEdit) {
        mutableStateOf(vm.email)
    }

    var inputTelepon by remember(vm.telepon, sedangEdit) {
        mutableStateOf(vm.telepon)
    }

    var pesanError by remember {
        mutableStateOf(false)
    }

    Halaman {

        JudulHalaman(
            "Kontak Mahasiswa",
            "Kelola informasi dan akses kontak"
        )

        Kartu {
            Text(
                "👤 ${vm.nama}",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Navy
            )

            Text(
                "${vm.nim} • ${vm.prodi}",
                color = MutedText,
                fontSize = 14.sp
            )

            HorizontalDivider()

            BarisInfo("Perguruan Tinggi", "Universitas Mulia")
        }

        Kartu {

            Text(
                "Informasi Kontak",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Navy
            )

            if (sedangEdit) {

                OutlinedTextField(
                    value = inputEmail,
                    onValueChange = { inputEmail = it },
                    label = { Text("Alamat Email") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                OutlinedTextField(
                    value = inputTelepon,
                    onValueChange = { inputTelepon = it },
                    label = { Text("Nomor Telepon") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                if (pesanError) {
                    Text(
                        "Periksa format email atau nomor telepon.",
                        color = MaterialTheme.colorScheme.error,
                        fontSize = 13.sp
                    )
                }

                Button(
                    onClick = {
                        if (
                            vm.simpanKontak(
                                inputEmail,
                                inputTelepon
                            )
                        ) {
                            sedangEdit = false
                            pesanError = false
                        } else {
                            pesanError = true
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Simpan Kontak")
                }

                OutlinedButton(
                    onClick = {
                        sedangEdit = false
                        pesanError = false
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Batal")
                }

            } else {

                BarisInfo(
                    "Email",
                    vm.email.ifBlank { "Belum diisi" }
                )

                HorizontalDivider()

                BarisInfo(
                    "Telepon",
                    vm.telepon.ifBlank { "Belum diisi" }
                )

                Button(
                    onClick = { sedangEdit = true },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("✎  Edit Kontak")
                }
            }
        }

        Kartu {

            Text(
                "Hubungi Mahasiswa",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Navy
            )

            OutlinedButton(
                onClick = {
                    val intent = Intent(
                        Intent.ACTION_SENDTO,
                        Uri.parse("mailto:${vm.email}")
                    )
                    if (
                        intent.resolveActivity(
                            context.packageManager
                        ) != null
                    ) {
                        context.startActivity(intent)
                    }
                },
                enabled = vm.email.isNotBlank(),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("✉  Kirim Email")
            }

            OutlinedButton(
                onClick = {
                    val intent = Intent(
                        Intent.ACTION_DIAL,
                        Uri.fromParts(
                            "tel",
                            vm.telepon,
                            null
                        )
                    )
                    if (
                        intent.resolveActivity(
                            context.packageManager
                        ) != null
                    ) {
                        context.startActivity(intent)
                    }
                },
                enabled = vm.telepon.isNotBlank(),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("☎  Buka Telepon")
            }

            Text(
                "Isi data kontak terlebih dahulu " +
                        "untuk mengaktifkan tombol.",
                color = MutedText,
                fontSize = 12.sp
            )
        }
    }
}
