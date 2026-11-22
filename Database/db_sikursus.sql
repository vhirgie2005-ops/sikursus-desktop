-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 22, 2026 at 10:59 AM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_sikursus`
--

-- --------------------------------------------------------

--
-- Table structure for table `instruktur`
--

CREATE TABLE `instruktur` (
  `id_instruktur` int(11) NOT NULL,
  `nama` varchar(100) NOT NULL,
  `keahlian` varchar(100) DEFAULT NULL,
  `no_hp` varchar(20) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `instruktur`
--

INSERT INTO `instruktur` (`id_instruktur`, `nama`, `keahlian`, `no_hp`, `email`) VALUES
(1, 'Yusuf Pratama', 'Pemrograman Java & OOP', '082170441101', 'yusuf.pratama@sikursus.id'),
(2, 'Maya Anggraini', 'Basis Data & UI/UX Design', '082170441102', 'maya.anggraini@sikursus.id'),
(3, 'Bagus Wibowo', 'Web & Mobile Development', '082170441103', 'bagus.wibowo@sikursus.id');

-- --------------------------------------------------------

--
-- Table structure for table `kelas`
--

CREATE TABLE `kelas` (
  `id_kelas` int(11) NOT NULL,
  `id_kursus` int(11) NOT NULL,
  `id_instruktur` int(11) NOT NULL,
  `nama_kelas` varchar(50) NOT NULL,
  `tanggal_mulai` date DEFAULT NULL,
  `kapasitas` int(11) DEFAULT 20
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `kelas`
--

INSERT INTO `kelas` (`id_kelas`, `id_kursus`, `id_instruktur`, `nama_kelas`, `tanggal_mulai`, `kapasitas`) VALUES
(1, 1, 1, 'PBO-Pagi-A', '2026-11-02', 30),
(2, 2, 2, 'DB-Siang-B', '2026-11-06', 25),
(3, 3, 3, 'ENT-Sore-C', '2026-11-12', 20);

-- --------------------------------------------------------

--
-- Table structure for table `kursus`
--

CREATE TABLE `kursus` (
  `id_kursus` int(11) NOT NULL,
  `kode_kursus` varchar(20) NOT NULL,
  `nama_kursus` varchar(100) NOT NULL,
  `level` varchar(30) NOT NULL,
  `biaya` decimal(12,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `kursus`
--

INSERT INTO `kursus` (`id_kursus`, `kode_kursus`, `nama_kursus`, `level`, `biaya`) VALUES
(1, 'KRS-101', 'Pemrograman Java Swing & JDBC', 'BASIC', '450000.00'),
(2, 'KRS-102', 'Administrasi Database & JasperReport', 'INTERMEDIATE', '600000.00'),
(3, 'KRS-103', 'Arsitektur Aplikasi Enterprise', 'ADVANCED', '850000.00');

-- --------------------------------------------------------

--
-- Table structure for table `pembayaran`
--

CREATE TABLE `pembayaran` (
  `id_pembayaran` int(11) NOT NULL,
  `id_pendaftaran` int(11) NOT NULL,
  `tanggal_bayar` date NOT NULL,
  `nominal` decimal(12,2) NOT NULL,
  `metode` varchar(30) NOT NULL,
  `status` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `pembayaran`
--

INSERT INTO `pembayaran` (`id_pembayaran`, `id_pendaftaran`, `tanggal_bayar`, `nominal`, `metode`, `status`) VALUES
(1, 1, '2026-11-02', '450000.00', 'TRANSFER', 'LUNAS'),
(2, 2, '2026-11-05', '600000.00', 'QRIS', 'LUNAS'),
(3, 3, '2026-11-09', '450000.00', 'TUNAI', 'LUNAS'),
(4, 4, '2026-11-12', '400000.00', 'TRANSFER', 'MENUNGGU'),
(5, 5, '2026-11-15', '600000.00', 'QRIS', 'LUNAS');

-- --------------------------------------------------------

--
-- Table structure for table `pendaftaran`
--

CREATE TABLE `pendaftaran` (
  `id_pendaftaran` int(11) NOT NULL,
  `id_peserta` int(11) NOT NULL,
  `id_kelas` int(11) NOT NULL,
  `tanggal_daftar` date NOT NULL,
  `status` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `pendaftaran`
--

INSERT INTO `pendaftaran` (`id_pendaftaran`, `id_peserta`, `id_kelas`, `tanggal_daftar`, `status`) VALUES
(1, 1, 1, '2026-11-01', 'AKTIF'),
(2, 2, 2, '2026-11-04', 'AKTIF'),
(3, 3, 1, '2026-11-08', 'AKTIF'),
(4, 4, 3, '2026-11-11', 'AKTIF'),
(5, 5, 2, '2026-11-14', 'AKTIF'),
(6, 5, 2, '2026-11-21', 'DAFTAR'),
(7, 1, 2, '2026-11-21', 'DAFTAR'),
(8, 5, 2, '2026-11-21', 'DAFTAR'),
(9, 5, 2, '2026-11-21', 'DAFTAR'),
(10, 5, 2, '2026-11-21', 'DAFTAR');

-- --------------------------------------------------------

--
-- Table structure for table `peserta`
--

CREATE TABLE `peserta` (
  `id_peserta` int(11) NOT NULL,
  `nim` varchar(20) NOT NULL,
  `nama` varchar(100) NOT NULL,
  `jenis_kelamin` varchar(15) NOT NULL,
  `prodi` varchar(100) NOT NULL,
  `no_hp` varchar(20) NOT NULL,
  `email` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `peserta`
--

INSERT INTO `peserta` (`id_peserta`, `nim`, `nama`, `jenis_kelamin`, `prodi`, `no_hp`, `email`) VALUES
(1, '2924031', 'Salma Azzahra', 'P', 'Informatika', '085263119901', 'salma.azzahra@mhs.ac.id'),
(2, '2924032', 'Reza Mahendra', 'L', 'Sistem Informasi', '085263119902', 'reza.mahendra@mhs.ac.id'),
(3, '2924033', 'Kirana Larasati', 'P', 'Informatika', '085263119903', 'kirana.larasati@mhs.ac.id'),
(4, '2924034', 'Galih Nugroho', 'L', 'Teknik Komputer', '085263119904', 'galih.nugroho@mhs.ac.id'),
(5, '2924035', 'Vina Oktaviani', 'P', 'Informatika', '085263119905', 'vina.oktaviani@mhs.ac.id');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id_user` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password_hash` varchar(255) NOT NULL,
  `role` enum('ADMIN','OPERATOR') NOT NULL DEFAULT 'OPERATOR',
  `aktif` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id_user`, `username`, `password_hash`, `role`, `aktif`) VALUES
(1, 'admin_demo', '120000:dFTA8SeqTcD13JGcbo7SVA==:1pU4RkK/sOZrrR+cJ2iwV+suHoxfOoImlqXkLHSbsPA=', 'ADMIN', 1),
(2, 'operator_demo', '120000:i7sHmYAJTXJDp2qE0sktzg==:o88QZ7K9Hw0y2vIcH2KIri8QBom2ZFTsX/FrQSKjtXc=', 'OPERATOR', 1),
(3, 'admin', '120000:ID/GhbtQCcydj6S4tXBcJQ==:b+5n3hE+jLHmEbtoR7VDjusPUpI42Kznz2GGQgedW40=', 'ADMIN', 1),
(4, 'operator', '120000:CmseS7+OTh5AaZ9BHQnEmA==:susv1hpuVSBbbKYQeV5JB/XISX41LDnCKb4Ih+0pXzI=', 'OPERATOR', 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `instruktur`
--
ALTER TABLE `instruktur`
  ADD PRIMARY KEY (`id_instruktur`);

--
-- Indexes for table `kelas`
--
ALTER TABLE `kelas`
  ADD PRIMARY KEY (`id_kelas`),
  ADD KEY `fk_kelas_kursus` (`id_kursus`),
  ADD KEY `fk_kelas_instruktur` (`id_instruktur`);

--
-- Indexes for table `kursus`
--
ALTER TABLE `kursus`
  ADD PRIMARY KEY (`id_kursus`);

--
-- Indexes for table `pembayaran`
--
ALTER TABLE `pembayaran`
  ADD PRIMARY KEY (`id_pembayaran`),
  ADD KEY `fk_pembayaran_pendaftaran` (`id_pendaftaran`);

--
-- Indexes for table `pendaftaran`
--
ALTER TABLE `pendaftaran`
  ADD PRIMARY KEY (`id_pendaftaran`),
  ADD KEY `fk_pendaftaran_peserta` (`id_peserta`),
  ADD KEY `fk_pendaftaran_kelas` (`id_kelas`);

--
-- Indexes for table `peserta`
--
ALTER TABLE `peserta`
  ADD PRIMARY KEY (`id_peserta`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id_user`),
  ADD UNIQUE KEY `username` (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `instruktur`
--
ALTER TABLE `instruktur`
  MODIFY `id_instruktur` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `kelas`
--
ALTER TABLE `kelas`
  MODIFY `id_kelas` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `kursus`
--
ALTER TABLE `kursus`
  MODIFY `id_kursus` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `pembayaran`
--
ALTER TABLE `pembayaran`
  MODIFY `id_pembayaran` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `pendaftaran`
--
ALTER TABLE `pendaftaran`
  MODIFY `id_pendaftaran` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `peserta`
--
ALTER TABLE `peserta`
  MODIFY `id_peserta` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `kelas`
--
ALTER TABLE `kelas`
  ADD CONSTRAINT `fk_kelas_instruktur` FOREIGN KEY (`id_instruktur`) REFERENCES `instruktur` (`id_instruktur`),
  ADD CONSTRAINT `fk_kelas_kursus` FOREIGN KEY (`id_kursus`) REFERENCES `kursus` (`id_kursus`);

--
-- Constraints for table `pembayaran`
--
ALTER TABLE `pembayaran`
  ADD CONSTRAINT `fk_pembayaran_pendaftaran` FOREIGN KEY (`id_pendaftaran`) REFERENCES `pendaftaran` (`id_pendaftaran`);

--
-- Constraints for table `pendaftaran`
--
ALTER TABLE `pendaftaran`
  ADD CONSTRAINT `fk_pendaftaran_kelas` FOREIGN KEY (`id_kelas`) REFERENCES `kelas` (`id_kelas`),
  ADD CONSTRAINT `fk_pendaftaran_peserta` FOREIGN KEY (`id_peserta`) REFERENCES `peserta` (`id_peserta`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
