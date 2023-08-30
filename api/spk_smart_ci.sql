-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 27 Agu 2023 pada 08.44
-- Versi server: 10.4.17-MariaDB
-- Versi PHP: 7.4.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `spk_smart_ci`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `alternatif`
--

CREATE TABLE `alternatif` (
  `id_alternatif` int(11) NOT NULL,
  `nama` varchar(100) NOT NULL,
  `nik` varchar(16) NOT NULL,
  `alamat` text NOT NULL,
  `telp` varchar(15) NOT NULL,
  `latitude` varchar(100) NOT NULL,
  `longitude` varchar(100) NOT NULL,
  `id_kelurahan` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `alternatif`
--

INSERT INTO `alternatif` (`id_alternatif`, `nama`, `nik`, `alamat`, `telp`, `latitude`, `longitude`, `id_kelurahan`) VALUES
(1, 'Adi Sunardi', '3276010010203001', 'Jl. Indah Damai No.12', '089692731129', '-6.390379056743405', '106.79591238498688', 1),
(38, 'Tono Ramadhan', '3276010020304002', 'Jl. Anggrek No.22', '081283027825', '-6.454089349386086', '106.78204338405214', 56),
(40, 'Ilham Pangestu', '3276010030405003', 'Jl. Raya Permai No.9', '089629102854', '-6.390249143605125', '106.79971893231493', 2),
(41, 'Yoga Pratomo', '3276010040506004', 'Jl. Jeruk No.48', '081263048894', '-6.396183530950901', '106.8008136849072', 1),
(42, 'Bayu Iskandar', '3276010050607005', 'Jl. Tomat No. 50', '089682030037', '-6.4439795915433145', '106.81383171423188', 1),
(43, 'Dedi Saputra', '3276010060708006', 'Jl. Mangga No.36', '081293507621', '-6.421952088421396', '106.78748654467793', 2),
(44, 'Hilman Arrafi', '3276010070809007', 'Jl. Ceremai N0.7', '089610289458', '-6.41760398376085', '106.81504003787062', 56),
(45, 'Joko Hidayat', '3276010080901008', 'Jl. Daisy No.17', '081283017601', '-6.409104366875268', '106.79348256701265', 2),
(46, 'Farel Zidane', '3276010090101109', 'Jl. Teratai No. 79', '089601283781', '-6.402466346942883', '106.79484788776023', 1),
(47, 'Teguh Susilo', '3276010010111210', 'Jl. Bayam N0. 47', '081290371925', '-6.40344222011804', '106.79208640663246', 2),
(48, 'Ahmad Riyadi', '3276010011121311', 'Jl. Mawar No.61', '089620167391', '-6.398063182164777', '106.79423162252122', 22),
(49, 'Sultan Rifandi', '3276010012131412', 'Jl. Daun No.11', '081203817497', '-6.407368740057477', '106.79598562502882', 23),
(50, 'Afriansyah Putra', '3276010013141513', 'Jl. Bawang No.69', '089612783650', '-6.399862764157292', '106.78568594241163', 1),
(51, 'Wira Alfatih', '3276010014151614', 'Jl. Tulip No.30', '081253097629', '-6.404298147716162', '106.79512731814405', 2),
(52, 'Galuh Firmansyah', '3276010015161715', 'Jl. Melati No.26', '089612937743', '-6.4028711583330375', '106.79566637860304', 56),
(53, 'Aji Prayoga', '3276010016171816', 'Jl. Kemiri No.31', '081237940076', '-6.406725433096333', '106.79524528794687', 22),
(54, 'Rifqi Pratama', '3276010017181917', 'Jl. Lily No.79', '089629103392', '-6.400464865896533', '106.78960620607913', 1),
(55, 'Khafi Arrahman', '3276010018192018', 'Jl. Kangkung No.94', '081293721054', '-6.403956946511232', '106.79375402712843', 2),
(56, 'Salman Rivadi', '3276010019202119', 'Jl. Sakura No.58', '089629164420', '-6.404508669892394', '106.78759738547458', 56),
(57, 'Vicky Kusumah', '3276010020212220', 'Jl. Dandelion No.83', '081248306719', '-6.4025922227073275', '106.78482763552687', 6),
(58, 'Rafi Irawan', '3276010021222321', 'Jl. Cerry No.25', '089681203603', '-6.458770355761226', '106.59256689333937', 2);

-- --------------------------------------------------------

--
-- Struktur dari tabel `hasil`
--

CREATE TABLE `hasil` (
  `id_hasil` int(11) NOT NULL,
  `id_alternatif` int(11) NOT NULL,
  `nilai` float NOT NULL,
  `kategori` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `hasil`
--

INSERT INTO `hasil` (`id_hasil`, `id_alternatif`, `nilai`, `kategori`) VALUES
(1, 1, 0.717, 'Sangat miskin'),
(2, 38, 0.833, 'Sangat miskin'),
(3, 40, 0.779, 'Sangat miskin'),
(4, 41, 0.742, 'Sangat miskin'),
(5, 42, 0.729, 'Sangat miskin'),
(6, 43, 0.47, 'Miskin'),
(7, 44, 0.476, 'Miskin'),
(8, 45, 0.685, 'Miskin'),
(9, 46, 0.615, 'Miskin'),
(10, 47, 0.545, 'Miskin'),
(11, 48, 0.298, 'Rentan miskin'),
(12, 49, 0.309, 'Rentan miskin'),
(13, 50, 0.379, 'Rentan miskin'),
(14, 51, 0.289, 'Rentan miskin'),
(15, 52, 0.404, 'Rentan miskin'),
(16, 53, 0.618, 'Miskin'),
(17, 54, 0.167, 'Tidak miskin'),
(18, 55, 0.158, 'Tidak miskin'),
(19, 56, 0.535, 'Miskin'),
(20, 57, 0.068, 'Tidak miskin'),
(21, 58, 0.779, 'Sangat miskin');

-- --------------------------------------------------------

--
-- Struktur dari tabel `history`
--

CREATE TABLE `history` (
  `tahun` int(11) NOT NULL,
  `data_KPM` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `history`
--

INSERT INTO `history` (`tahun`, `data_KPM`) VALUES
(2020, 16),
(2021, 11),
(2022, 18);

-- --------------------------------------------------------

--
-- Struktur dari tabel `kriteria`
--

CREATE TABLE `kriteria` (
  `id_kriteria` int(11) NOT NULL,
  `keterangan` varchar(100) NOT NULL,
  `kode_kriteria` varchar(100) NOT NULL,
  `bobot` float NOT NULL,
  `jenis` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `kriteria`
--

INSERT INTO `kriteria` (`id_kriteria`, `keterangan`, `kode_kriteria`, `bobot`, `jenis`) VALUES
(29, 'Penghasilan rata-rata / bulan', 'C1', 0.5, 'Cost'),
(30, 'Jumlah tanggungan keluarga', 'C2', 0.5, 'Cost'),
(31, 'Status tempat tinggal', 'C3', 0.5, 'Cost'),
(32, 'Kemampuan akses pendidikan', 'C4', 0.5, 'Cost'),
(33, 'Kepemilikan kendaraan', 'C5', 0.5, 'Cost'),
(34, 'Jenis lantai', 'C6', 0.3, 'Cost'),
(35, 'Jenis dan kondisi dinding', 'C7', 0.3, 'Cost'),
(36, 'Jenis dan kondisi atap', 'C8', 0.3, 'Cost'),
(37, 'Sumber air minum', 'C9', 0.3, 'Cost'),
(38, 'Sumber dan daya listrik terpasang', 'C10', 0.3, 'Cost'),
(39, 'Kepemilikan dan penggunaan kamar mandi cuci kakus (MCK)', 'C11', 0.3, 'Cost'),
(40, 'Fasilitas tempat pembuangan akhir tinja', 'C12', 0.3, 'Cost'),
(41, 'Memiliki anggota keluarga lansia / disabilitas / keterbelakangan mental /berkebutuhan khusus lainnya', 'C13', 0.2, 'Cost'),
(42, 'Kesanggupan biaya pengobatan', 'C14', 0.2, 'Cost');

-- --------------------------------------------------------

--
-- Struktur dari tabel `penilaian`
--

CREATE TABLE `penilaian` (
  `id_penilaian` int(11) NOT NULL,
  `id_alternatif` int(11) NOT NULL,
  `id_kriteria` int(11) NOT NULL,
  `nilai` int(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `penilaian`
--

INSERT INTO `penilaian` (`id_penilaian`, `id_alternatif`, `id_kriteria`, `nilai`) VALUES
(226, 1, 29, 130),
(227, 1, 30, 137),
(228, 1, 31, 139),
(229, 1, 32, 144),
(230, 1, 33, 148),
(231, 1, 34, 154),
(232, 1, 35, 160),
(233, 1, 36, 164),
(234, 1, 37, 169),
(235, 1, 38, 175),
(236, 1, 39, 177),
(237, 1, 40, 180),
(238, 1, 41, 185),
(239, 1, 42, 188),
(240, 38, 29, 131),
(241, 38, 30, 136),
(242, 38, 31, 141),
(243, 38, 32, 143),
(244, 38, 33, 149),
(245, 38, 34, 156),
(246, 38, 35, 159),
(247, 38, 36, 165),
(248, 38, 37, 170),
(249, 38, 38, 174),
(250, 38, 39, 178),
(251, 38, 40, 182),
(252, 38, 41, 185),
(253, 38, 42, 187),
(254, 40, 29, 129),
(255, 40, 30, 137),
(256, 40, 31, 141),
(257, 40, 32, 144),
(258, 40, 33, 147),
(259, 40, 34, 155),
(260, 40, 35, 160),
(261, 40, 36, 164),
(262, 40, 37, 169),
(263, 40, 38, 173),
(264, 40, 39, 178),
(265, 40, 40, 183),
(266, 40, 41, 184),
(267, 40, 42, 188),
(268, 41, 29, 131),
(269, 41, 30, 135),
(270, 41, 31, 140),
(271, 41, 32, 144),
(272, 41, 33, 148),
(273, 41, 34, 157),
(274, 41, 35, 159),
(275, 41, 36, 163),
(276, 41, 37, 171),
(277, 41, 38, 175),
(278, 41, 39, 178),
(279, 41, 40, 181),
(280, 41, 41, 184),
(281, 41, 42, 187),
(282, 42, 29, 130),
(283, 42, 30, 138),
(284, 42, 31, 139),
(285, 42, 32, 143),
(286, 42, 33, 149),
(287, 42, 34, 155),
(288, 42, 35, 159),
(289, 42, 36, 164),
(290, 42, 37, 170),
(291, 42, 38, 174),
(292, 42, 39, 177),
(293, 42, 40, 183),
(294, 42, 41, 185),
(295, 42, 42, 188),
(296, 43, 29, 129),
(297, 43, 30, 135),
(298, 43, 31, 139),
(299, 43, 32, 143),
(300, 43, 33, 148),
(301, 43, 34, 152),
(302, 43, 35, 159),
(303, 43, 36, 164),
(304, 43, 37, 169),
(305, 43, 38, 174),
(306, 43, 39, 176),
(307, 43, 40, 180),
(308, 43, 41, 184),
(309, 43, 42, 187),
(310, 44, 29, 128),
(311, 44, 30, 135),
(312, 44, 31, 140),
(313, 44, 32, 142),
(314, 44, 33, 146),
(315, 44, 34, 156),
(316, 44, 35, 159),
(317, 44, 36, 163),
(318, 44, 37, 169),
(319, 44, 38, 175),
(320, 44, 39, 176),
(321, 44, 40, 180),
(322, 44, 41, 185),
(323, 44, 42, 187),
(324, 45, 29, 129),
(325, 45, 30, 137),
(326, 45, 31, 140),
(327, 45, 32, 144),
(328, 45, 33, 147),
(329, 45, 34, 151),
(330, 45, 35, 160),
(331, 45, 36, 164),
(332, 45, 37, 170),
(333, 45, 38, 173),
(334, 45, 39, 177),
(335, 45, 40, 180),
(336, 45, 41, 185),
(337, 45, 42, 188),
(338, 46, 29, 127),
(339, 46, 30, 138),
(340, 46, 31, 141),
(341, 46, 32, 143),
(342, 46, 33, 147),
(343, 46, 34, 152),
(344, 46, 35, 158),
(345, 46, 36, 165),
(346, 46, 37, 168),
(347, 46, 38, 174),
(348, 46, 39, 177),
(349, 46, 40, 182),
(350, 46, 41, 185),
(351, 46, 42, 187),
(352, 47, 29, 128),
(353, 47, 30, 136),
(354, 47, 31, 140),
(355, 47, 32, 142),
(356, 47, 33, 148),
(357, 47, 34, 154),
(358, 47, 35, 159),
(359, 47, 36, 164),
(360, 47, 37, 169),
(361, 47, 38, 173),
(362, 47, 39, 178),
(363, 47, 40, 181),
(364, 47, 41, 184),
(365, 47, 42, 187),
(366, 48, 29, 128),
(367, 48, 30, 135),
(368, 48, 31, 139),
(369, 48, 32, 142),
(370, 48, 33, 148),
(371, 48, 34, 152),
(372, 48, 35, 159),
(373, 48, 36, 163),
(374, 48, 37, 167),
(375, 48, 38, 173),
(376, 48, 39, 176),
(377, 48, 40, 179),
(378, 48, 41, 184),
(379, 48, 42, 186),
(380, 49, 29, 127),
(381, 49, 30, 134),
(382, 49, 31, 140),
(383, 49, 32, 142),
(384, 49, 33, 146),
(385, 49, 34, 153),
(386, 49, 35, 159),
(387, 49, 36, 163),
(388, 49, 37, 167),
(389, 49, 38, 173),
(390, 49, 39, 176),
(391, 49, 40, 180),
(392, 49, 41, 184),
(393, 49, 42, 187),
(394, 50, 29, 128),
(395, 50, 30, 133),
(396, 50, 31, 139),
(397, 50, 32, 142),
(398, 50, 33, 148),
(399, 50, 34, 153),
(400, 50, 35, 158),
(401, 50, 36, 164),
(402, 50, 37, 170),
(403, 50, 38, 172),
(404, 50, 39, 177),
(405, 50, 40, 179),
(406, 50, 41, 185),
(407, 50, 42, 188),
(408, 51, 29, 129),
(409, 51, 30, 132),
(410, 51, 31, 140),
(411, 51, 32, 142),
(412, 51, 33, 146),
(413, 51, 34, 152),
(414, 51, 35, 158),
(415, 51, 36, 163),
(416, 51, 37, 167),
(417, 51, 38, 173),
(418, 51, 39, 176),
(419, 51, 40, 181),
(420, 51, 41, 184),
(421, 51, 42, 187),
(422, 52, 29, 128),
(423, 52, 30, 134),
(424, 52, 31, 139),
(425, 52, 32, 144),
(426, 52, 33, 146),
(427, 52, 34, 157),
(428, 52, 35, 160),
(429, 52, 36, 162),
(430, 52, 37, 167),
(431, 52, 38, 172),
(432, 52, 39, 176),
(433, 52, 40, 179),
(434, 52, 41, 185),
(435, 52, 42, 186),
(436, 53, 29, 128),
(437, 53, 30, 137),
(438, 53, 31, 140),
(439, 53, 32, 144),
(440, 53, 33, 148),
(441, 53, 34, 152),
(442, 53, 35, 159),
(443, 53, 36, 164),
(444, 53, 37, 168),
(445, 53, 38, 174),
(446, 53, 39, 177),
(447, 53, 40, 180),
(448, 53, 41, 185),
(449, 53, 42, 186),
(450, 54, 29, 125),
(451, 54, 30, 133),
(452, 54, 31, 140),
(453, 54, 32, 142),
(454, 54, 33, 146),
(455, 54, 34, 150),
(456, 54, 35, 158),
(457, 54, 36, 161),
(458, 54, 37, 166),
(459, 54, 38, 173),
(460, 54, 39, 176),
(461, 54, 40, 180),
(462, 54, 41, 185),
(463, 54, 42, 186),
(464, 55, 29, 127),
(465, 55, 30, 132),
(466, 55, 31, 139),
(467, 55, 32, 142),
(468, 55, 33, 147),
(469, 55, 34, 151),
(470, 55, 35, 159),
(471, 55, 36, 161),
(472, 55, 37, 167),
(473, 55, 38, 173),
(474, 55, 39, 176),
(475, 55, 40, 179),
(476, 55, 41, 184),
(477, 55, 42, 186),
(478, 56, 29, 129),
(479, 56, 30, 134),
(480, 56, 31, 140),
(481, 56, 32, 144),
(482, 56, 33, 147),
(483, 56, 34, 153),
(484, 56, 35, 159),
(485, 56, 36, 163),
(486, 56, 37, 169),
(487, 56, 38, 173),
(488, 56, 39, 177),
(489, 56, 40, 180),
(490, 56, 41, 184),
(491, 56, 42, 187),
(492, 57, 29, 125),
(493, 57, 30, 134),
(494, 57, 31, 139),
(495, 57, 32, 142),
(496, 57, 33, 145),
(497, 57, 34, 150),
(498, 57, 35, 158),
(499, 57, 36, 161),
(500, 57, 37, 166),
(501, 57, 38, 173),
(502, 57, 39, 176),
(503, 57, 40, 180),
(504, 57, 41, 184),
(505, 57, 42, 186),
(506, 58, 29, 129),
(507, 58, 30, 137),
(508, 58, 31, 141),
(509, 58, 32, 144),
(510, 58, 33, 147),
(511, 58, 34, 155),
(512, 58, 35, 160),
(513, 58, 36, 164),
(514, 58, 37, 169),
(515, 58, 38, 173),
(516, 58, 39, 178),
(517, 58, 40, 183),
(518, 58, 41, 184),
(519, 58, 42, 188);

-- --------------------------------------------------------

--
-- Struktur dari tabel `sub_kriteria`
--

CREATE TABLE `sub_kriteria` (
  `id_sub_kriteria` int(11) NOT NULL,
  `id_kriteria` int(11) NOT NULL,
  `deskripsi` varchar(200) NOT NULL,
  `nilai` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `sub_kriteria`
--

INSERT INTO `sub_kriteria` (`id_sub_kriteria`, `id_kriteria`, `deskripsi`, `nilai`) VALUES
(125, 29, 'Lebih dari Rp. 6.000.000,00 ', 1),
(126, 29, 'Kurang dari dan sama dengan Rp. 6.000.000,00', 0.9),
(127, 29, 'Kurang dari Rp. 5.000.000,00', 0.7),
(128, 29, 'Kurang dari Rp. 4.000.000,00', 0.5),
(129, 29, 'Kurang dari Rp. 3.000.000,00', 0.3),
(130, 29, 'Kurang dari Rp. 2.000.000,00', 0.2),
(131, 29, 'Kurang dari Rp. 1.000.000,00', 0.1),
(132, 30, 'Tidak ada', 1),
(133, 30, '1 jiwa', 0.9),
(134, 30, '2 jiwa', 0.7),
(135, 30, '3 jiwa', 0.5),
(136, 30, '4 jiwa', 0.3),
(137, 30, '5 jiwa', 0.2),
(138, 30, 'Lebih dari 5 jiwa', 0.1),
(139, 31, 'Milik sendiri', 1),
(140, 31, 'Kontrak / sewa', 0.5),
(141, 31, 'Milik pihak lain', 0.1),
(142, 32, 'Tidak ada anggota keluarga putus sekolah', 1),
(143, 32, 'Bersekolah tetapi tidak mampu membayar biaya pendidikan', 0.5),
(144, 32, 'Ada anggota keluarga putus sekolah (SD,SMP dan SMA)', 0.1),
(145, 33, 'Memiliki kendaraan roda 4', 1),
(146, 33, 'Memiliki lebih dari 1 kendaraan roda 2', 0.8),
(147, 33, 'Memiliki 1 kendaraan roda 2 lebih dari dan sama dengan 150cc', 0.6),
(148, 33, 'Memiliki 1 kendaraan roda 2 dibawah 150 cc', 0.4),
(149, 33, 'Tidak memiliki kendaraan roda 2', 0.2),
(150, 34, 'Marmer / granit', 1),
(151, 34, 'Keramik', 0.9),
(152, 34, 'Ubin / tegel / teraso', 0.8),
(153, 34, 'Kayu / papan kualitas tinggi', 0.6),
(154, 34, 'Semen', 0.5),
(155, 34, 'Bambu ', 0.4),
(156, 34, 'Kayu / papan kualitas rendah', 0.3),
(157, 34, 'Tanah', 0.1),
(158, 35, 'Tembok bagus / kualitas tinggi', 1),
(159, 35, 'Tembok jelek / kualitas rendah', 0.5),
(160, 35, 'Kayu / anyaman bambu / batang kayu / triplek / seng', 0.1),
(161, 36, 'Beton / genteng keramik / genteng beton', 1),
(162, 36, 'Genteng metal / spandek', 0.8),
(163, 36, 'Genteng tanah liat', 0.6),
(164, 36, 'Asbes / seng', 0.4),
(165, 36, 'Bambu / jerami / ijuk / rumbia', 0.2),
(166, 37, 'Air kemasan bermerek', 1),
(167, 37, 'Air isi ulang ', 0.8),
(168, 37, 'Ledeng meteran / eceran', 0.7),
(169, 37, 'Sumur bor / pompa / sumur terlindungi', 0.5),
(170, 37, 'Sumur tak terlindungi', 0.3),
(171, 37, 'Air sungai / danau / waduk / hujan', 0.2),
(172, 38, '1300 watt atau lebih', 1),
(173, 38, '900 watt', 0.7),
(174, 38, '450 watt', 0.4),
(175, 38, 'Tanpa meteran / bukan listrik', 0.1),
(176, 39, 'Sendiri', 1),
(177, 39, 'Bersama', 0.5),
(178, 39, 'Tidak ada', 0.1),
(179, 40, 'Septic tank', 1),
(180, 40, 'Septic tank komunal', 0.8),
(181, 40, 'Lubang tanah', 0.6),
(182, 40, 'Kolam / sawah / sungai / danau ', 0.4),
(183, 40, 'Tanah / kebun', 0.2),
(184, 41, 'Tidak ada', 1),
(185, 41, 'Ada', 0.5),
(186, 42, 'Sanggup membayar iuran jaminan kesehatan nasional terendah secara mandiri', 1),
(187, 42, 'Jaminan kesehatan nasional dibayarkan oleh pihak lain', 0.5),
(188, 42, 'Tidak sanggup membayar iuran jaminan kesehatan nasional terendah / tidak memiliki JKN', 0.1);

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_kelurahan`
--

CREATE TABLE `tb_kelurahan` (
  `id_kelurahan` int(11) NOT NULL,
  `nama_kelurahan` varchar(100) NOT NULL,
  `latitude_kelurahan` text NOT NULL,
  `longitude_kelurahan` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `tb_kelurahan`
--

INSERT INTO `tb_kelurahan` (`id_kelurahan`, `nama_kelurahan`, `latitude_kelurahan`, `longitude_kelurahan`) VALUES
(1, 'Beji', '-6.3811535277603815', '106.81406557559967'),
(2, 'Beji Timur', '-6.380524447786625', '106.82344123721123'),
(3, 'Kemiri Muka', '-6.3781843834564045', '106.83262277394533'),
(6, 'Pondok Cina', '-6.370940878697045', '106.83695822954178'),
(7, 'Kukusan', '-6.367555498933154', '106.81917518377304'),
(8, 'Tanah Baru', '-6.374380215070432', '106.80353924632072'),
(9, 'Pancoran Mas', '-6.39614692702556', '106.80159330368042'),
(10, 'Depok', '-6.403181501575309', '106.81923687458038'),
(11, 'Depok Jaya', '-6.389073271985217', '106.8139499053359'),
(12, 'Rangkapan Jaya', '-6.394772853035552', '106.78504977375269'),
(13, 'Rangkapan Jaya Baru', '-6.397061530482874', '106.77765190601349'),
(14, 'Mampang', '-6.390379056743405', '106.79591238498688'),
(15, 'Cipayung', '-6.419259070272897', '106.79564516991377'),
(16, 'Cipayung Jaya', '-6.441332373517249', '106.7968524992466'),
(17, 'Ratu Jaya', '-6.424744418739374', '106.81249983608723'),
(18, 'Bojong Pondok Terong', '-6.432709463217441', '106.80392917245626'),
(19, 'Pondok Jaya', '-6.441637214470646', '106.81142494082451'),
(20, 'Sukmajaya', '-6.411357785747897', '106.84454318135977'),
(21, 'Mekar Jaya', '-6.3866812682471314', '106.84270486235619'),
(22, 'Bakti Jaya', '-6.385211541503739', '106.854557543993'),
(23, 'Abadi Jaya', '-6.389482433783662', '106.84974633157253'),
(24, 'Tirta Jaya', '-6.410994287063197', '106.8222838640213'),
(25, 'Cisalak', '-6.37563705651708', '106.86307992786169'),
(26, 'Sukamaju', '-6.414852490859261', '106.85927219688892'),
(27, 'Cilodong', '-6.425863537176371', '106.84654042124748'),
(28, 'Kalibaru', '-6.445333604680441', '106.84300258755684'),
(29, 'Kalimulya', '-6.438335927122492', '106.82158917188644'),
(30, 'Jatimulya', '-6.446864791869478', '106.83356557041407'),
(31, 'Limo', '-6.35946548381497', '106.77635975182056'),
(32, 'Meruyung', '-6.37558840894681', '106.7714486271143'),
(33, 'Grogol', '-6.37675894817545', '106.78960282355547'),
(34, 'Krukut', '-6.349498652747246', '106.79091811180115'),
(35, 'Cinere', '-6.334552209859165', '106.78291708230972'),
(36, 'Gandul', '-6.340051794141539', '106.7935848981142'),
(37, 'Pangkalan Jati', '-6.323005358128384', '106.79381255060434'),
(38, 'Pangkalan Jati Baru', '-6.323511544245822', '106.80220786482096'),
(39, 'Cisalak Pasar', '-6.373524548187544', '106.87643397599459'),
(40, 'Mekarsari', '-6.366648173378118', '106.86534136533737'),
(41, 'Tugu', '-6.366127035243016', '106.85018889605999'),
(42, 'Pasir Gunung Selatan', '-6.343095467601294', '106.84936579316854'),
(43, 'Harjamukti', '-6.382749548168411', '106.88585992902517'),
(44, 'Curug', '-6.386463025254565', '106.87382016330957'),
(45, 'Tapos', '-6.4314097838415245', '106.88592597842216'),
(46, 'Leuwinanggung', '-6.407600842755198', '106.90498877316713'),
(47, 'Sukatani', '-6.386327747872004', '106.88867893069983'),
(48, 'Sukamaju Baru', '-6.415408896078311', '106.87560249119997'),
(49, 'Jatijajar', '-6.418011998172831', '106.86549928039312'),
(50, 'Cilangkap', '-6.445146703577713', '106.8568367511034'),
(51, 'Cimpaeun', '-6.444932483512533', '106.8755317479372'),
(52, 'Sawangan', '-6.40078789791938', '106.7589783668518'),
(53, 'Kedaung', '-6.366701153504174', '106.74664590507746'),
(54, 'Cinangka', '-6.367835393382313', '106.75716787576675'),
(55, 'Sawangan Baru', '-6.404984693354911', '106.77389614284039'),
(56, 'Bedahan', '-6.4214603466469296', '106.76681142300367'),
(57, 'Pengasinan', '-6.422195991298805', '106.75246395170689'),
(58, 'Pasir Putih', '-6.422305604926296', '106.78263645619154'),
(59, 'Bojong Sari', '-6.397362399677711', '106.74316372722387'),
(60, 'Bojongsari Baru', '-6.384941319237965', '106.7443660274148'),
(61, 'Serua', '-6.369310168042932', '106.74008689820766'),
(62, 'Pondok Petir', '-6.3666271812511805', '106.7258732020855'),
(63, 'Curug', '-6.3935640380430145', '106.73291601240635'),
(64, 'Duren Mekar', '-6.4167099490245265', '106.74034237861633'),
(65, 'Duren Seribu', '-6.438837001889467', '106.74601960927248');

-- --------------------------------------------------------

--
-- Struktur dari tabel `user`
--

CREATE TABLE `user` (
  `id_user` int(11) NOT NULL,
  `id_user_level` int(11) NOT NULL,
  `nama` varchar(200) NOT NULL,
  `email` varchar(100) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `user`
--

INSERT INTO `user` (`id_user`, `id_user_level`, `nama`, `email`, `username`, `password`) VALUES
(1, 1, 'Admin', 'admin@gmail.com', 'admin', '$2y$10$4KtWmy7Vo3XgE03PdrHhPORetXzqxkQlkKbyPor8.FprjhDw/oXme'),
(7, 2, 'Alifah Fadiyah', 'alifah@gmail.com', 'alifah', '$2y$10$XMF86PmVyOmVnaDgFs4te.IaWT9ZfRYJdg6a6Rar.61Ws/itv7T5q'),
(9, 2, 'Haniifah Fawziyyah', 'haniifah@gmail.com', 'haniifah', '$2y$10$ELwYAKIxqp1Tw8rh543SVu9pDRwgfniPeKI1FLskX7HLO82AA/Ikm');

-- --------------------------------------------------------

--
-- Struktur dari tabel `user_level`
--

CREATE TABLE `user_level` (
  `id_user_level` int(11) NOT NULL,
  `user_level` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `user_level`
--

INSERT INTO `user_level` (`id_user_level`, `user_level`) VALUES
(1, 'Administrator'),
(2, 'User');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `alternatif`
--
ALTER TABLE `alternatif`
  ADD PRIMARY KEY (`id_alternatif`),
  ADD KEY `id_kelurahan` (`id_kelurahan`);

--
-- Indeks untuk tabel `hasil`
--
ALTER TABLE `hasil`
  ADD PRIMARY KEY (`id_hasil`),
  ADD KEY `id_alternatif` (`id_alternatif`);

--
-- Indeks untuk tabel `kriteria`
--
ALTER TABLE `kriteria`
  ADD PRIMARY KEY (`id_kriteria`);

--
-- Indeks untuk tabel `penilaian`
--
ALTER TABLE `penilaian`
  ADD PRIMARY KEY (`id_penilaian`),
  ADD KEY `id_alternatif` (`id_alternatif`),
  ADD KEY `id_kriteria` (`id_kriteria`),
  ADD KEY `nilai` (`nilai`);

--
-- Indeks untuk tabel `sub_kriteria`
--
ALTER TABLE `sub_kriteria`
  ADD PRIMARY KEY (`id_sub_kriteria`),
  ADD KEY `id_kriteria` (`id_kriteria`);

--
-- Indeks untuk tabel `tb_kelurahan`
--
ALTER TABLE `tb_kelurahan`
  ADD PRIMARY KEY (`id_kelurahan`);

--
-- Indeks untuk tabel `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id_user`),
  ADD KEY `id_user_level` (`id_user_level`);

--
-- Indeks untuk tabel `user_level`
--
ALTER TABLE `user_level`
  ADD PRIMARY KEY (`id_user_level`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `alternatif`
--
ALTER TABLE `alternatif`
  MODIFY `id_alternatif` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=59;

--
-- AUTO_INCREMENT untuk tabel `hasil`
--
ALTER TABLE `hasil`
  MODIFY `id_hasil` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT untuk tabel `kriteria`
--
ALTER TABLE `kriteria`
  MODIFY `id_kriteria` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=43;

--
-- AUTO_INCREMENT untuk tabel `penilaian`
--
ALTER TABLE `penilaian`
  MODIFY `id_penilaian` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=520;

--
-- AUTO_INCREMENT untuk tabel `sub_kriteria`
--
ALTER TABLE `sub_kriteria`
  MODIFY `id_sub_kriteria` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=189;

--
-- AUTO_INCREMENT untuk tabel `tb_kelurahan`
--
ALTER TABLE `tb_kelurahan`
  MODIFY `id_kelurahan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=66;

--
-- AUTO_INCREMENT untuk tabel `user`
--
ALTER TABLE `user`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT untuk tabel `user_level`
--
ALTER TABLE `user_level`
  MODIFY `id_user_level` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `alternatif`
--
ALTER TABLE `alternatif`
  ADD CONSTRAINT `alternatif_ibfk_1` FOREIGN KEY (`id_kelurahan`) REFERENCES `tb_kelurahan` (`id_kelurahan`);

--
-- Ketidakleluasaan untuk tabel `hasil`
--
ALTER TABLE `hasil`
  ADD CONSTRAINT `hasil_ibfk_1` FOREIGN KEY (`id_alternatif`) REFERENCES `alternatif` (`id_alternatif`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ketidakleluasaan untuk tabel `penilaian`
--
ALTER TABLE `penilaian`
  ADD CONSTRAINT `penilaian_ibfk_1` FOREIGN KEY (`id_alternatif`) REFERENCES `alternatif` (`id_alternatif`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `penilaian_ibfk_2` FOREIGN KEY (`id_kriteria`) REFERENCES `kriteria` (`id_kriteria`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `penilaian_ibfk_3` FOREIGN KEY (`nilai`) REFERENCES `sub_kriteria` (`id_sub_kriteria`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ketidakleluasaan untuk tabel `sub_kriteria`
--
ALTER TABLE `sub_kriteria`
  ADD CONSTRAINT `sub_kriteria_ibfk_1` FOREIGN KEY (`id_kriteria`) REFERENCES `kriteria` (`id_kriteria`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ketidakleluasaan untuk tabel `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `user_ibfk_1` FOREIGN KEY (`id_user_level`) REFERENCES `user_level` (`id_user_level`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
