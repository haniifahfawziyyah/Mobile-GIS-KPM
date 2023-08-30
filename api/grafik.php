<?php
include_once "ckoneksi.php";

// Menghitung jumlah total data KPM keseluruhan berdasarkan kategori
$sqlTotal = "SELECT COUNT(*) AS total FROM hasil WHERE kategori IN ('Sangat miskin', 'Miskin', 'Rentan miskin')";
$queryTotal = mysqli_query($db, $sqlTotal);
$resultTotal = mysqli_fetch_array($queryTotal);
$totalDataKPM = $resultTotal['total'];

$data = array();

// Mengambil data kelurahan dari tabel tb_kelurahan
$query = mysqli_query($db, "SELECT * FROM tb_kelurahan ORDER BY nama_kelurahan ASC");

while ($row = mysqli_fetch_array($query)) {
    $kategori = array("Sangat miskin", "Miskin", "Rentan miskin");
    $kategori = implode("','", $kategori);

    $sqlj = "SELECT COUNT(*) AS jumlah FROM hasil 
             INNER JOIN alternatif ON hasil.id_alternatif = alternatif.id_alternatif 
             WHERE alternatif.id_kelurahan = '" . $row['id_kelurahan'] . "' 
             AND hasil.kategori IN ('$kategori')";

    $queryj = mysqli_query($db, $sqlj);
    $resultj = mysqli_fetch_array($queryj);

    $jumlahDataKPM = intval($resultj['jumlah']);
    $proporsi = $jumlahDataKPM / $totalDataKPM;

    $data[] = array(
        "name" => $row['nama_kelurahan'],
        "total_kpm" => $totalDataKPM,
        "jumlah_kpm" => $jumlahDataKPM,
        "proporsi" => $proporsi
    );
}

// create the JSON string
$json = json_encode($data, JSON_PRETTY_PRINT);

// set the proper Content-Type header
header('Content-Type: application/json');

// print the JSON
echo $json;

mysqli_close($db);
?>
