<?php
include_once "ckoneksi.php";

// Menghitung jumlah total data KPM keseluruhan berdasarkan kategori
$sqlTotal = "SELECT COUNT(*) AS total FROM hasil WHERE kategori IN ('Sangat miskin', 'Miskin', 'Rentan miskin')";
$queryTotal = mysqli_query($db, $sqlTotal);
$resultTotal = mysqli_fetch_array($queryTotal);
$totalDataKPM = $resultTotal['total'];

// Mengambil data kelurahan dari tabel tb_kelurahan
$query = mysqli_query($db, "SELECT * FROM tb_kelurahan ORDER BY nama_kelurahan ASC");
$num_rows = mysqli_num_rows($query);

if ($num_rows > 0) {
    $data = array();

    while ($row = mysqli_fetch_array($query)) {
        $kategori = array("Sangat miskin", "Miskin", "Rentan miskin");

        $kategori = implode("','", $kategori);

        $sqlj = "SELECT COUNT(*) AS jumlah FROM hasil 
             INNER JOIN alternatif ON hasil.id_alternatif = alternatif.id_alternatif 
             WHERE alternatif.id_kelurahan = '" . $row['id_kelurahan'] . "' 
             AND hasil.kategori IN ('$kategori')";

        $queryj = mysqli_query($db, $sqlj);
        $resultj = mysqli_fetch_array($queryj);

        $proporsi = $resultj['jumlah'] / $totalDataKPM;

        $data[] = array(
            "id" => $row['id_kelurahan'],
            "nama" => $row['nama_kelurahan'],
            "lat" => $row['latitude_kelurahan'],
            "lng" => $row['longitude_kelurahan'],
            "jumlah" => $resultj['jumlah'],
            "proporsi" => $proporsi
        );
    }

    $response = array(
        "value" => 1,
        "total_kpm" => $totalDataKPM, // Menambahkan total data KPM ke dalam respons
        "results" => $data
    );
} else {
    $response = array(
        "value" => 0,
        "message" => "Data tidak ditemukan."
    );
}

$json = json_encode($response, JSON_PRETTY_PRINT);
header('Content-Type: application/json');
echo $json;
mysqli_close($db);
?>