
# 🎓 PROFIL MAHASISWA

### Aplikasi Android — Navigation, Lifecycle & ViewModel

Aplikasi Profil Mahasiswa merupakan project praktikum mata kuliah **Pemrograman Aplikasi Bergerak** yang dikembangkan menggunakan Kotlin dan Jetpack Compose.

Aplikasi ini menerapkan navigasi antarhalaman, pengelolaan UI State menggunakan ViewModel, dan pengujian Lifecycle Android.

---

## 👨‍🎓 Identitas Pengembang

| Informasi | Keterangan |
|---|---|
| Nama | Wisnu Wardhana |
| NIM | 2411037 |
| Program Studi | S1 Informatika |
| Universitas | Universitas Mulia |
| Mata Kuliah | Pemrograman Aplikasi Bergerak |

## ✨ Fitur Aplikasi

- Dashboard profil dan progres pembelajaran
- Navigasi empat halaman menggunakan Navigation Compose
- Tampilan profil mahasiswa dan fitur edit biodata
- Halaman detail mata kuliah
- Checklist progres materi pembelajaran
- Halaman kontak dan fitur edit informasi
- Tombol email dan telepon
- Bottom Navigation
- Pengelolaan state menggunakan ViewModel
- Pengujian rotasi layar dan tombol kembali

## 🛠️ Teknologi

- Kotlin
- Android Studio
- Jetpack Compose
- Material Design 3
- Navigation Compose
- ViewModel
- Android Emulator

## 📱 Dokumentasi Tampilan

Screenshot berikut diambil dari aplikasi yang dijalankan melalui Android Emulator.

### Galeri Aplikasi

Lihat seluruh screenshot pada folder:

[📸 Buka Galeri Screenshot](screenshots/)

## 🎬 Video Demonstrasi

Video demo akan ditambahkan setelah proses perekaman selesai.

## 🔄 Alur Navigasi

```text
Beranda
  |
  +-- Profil Mahasiswa
  |     +-- Edit Profil
  |
  +-- Detail Mata Kuliah
  |     +-- Checklist Materi
  |
  +-- Kontak Mahasiswa
        +-- Edit Kontak
```

## ✅ Hasil Pengujian

| Pengujian | Hasil |
|---|---|
| Dashboard tampil | Berhasil |
| Navigasi Profil | Berhasil |
| Edit Profil | Berhasil |
| Navigasi Mata Kuliah | Berhasil |
| Checklist materi | Berhasil |
| Update progres Dashboard | Berhasil |
| Navigasi Kontak | Berhasil |
| Edit Kontak | Berhasil |
| Bottom Navigation | Berhasil |
| Tombol kembali Android | Berhasil |
| State bertahan saat rotasi | Berhasil |

Seluruh 11 skenario pengujian manual berhasil.

## 🚀 Cara Menjalankan

1. Clone repository:

   `git clone https://github.com/wisnuwardhana822/ProfilMahasiswa-WisnuWardhana.git`

2. Buka folder project melalui Android Studio.
3. Tunggu Gradle Sync selesai.
4. Jalankan Android Emulator atau hubungkan perangkat Android.
5. Klik **Run App**.

## 📌 Catatan

State dikelola menggunakan ViewModel dan dipertahankan selama perubahan konfigurasi seperti rotasi layar. Penyimpanan permanen setelah proses aplikasi dihentikan belum diimplementasikan.

---

**Dikembangkan oleh Wisnu Wardhana — Universitas Mulia**
