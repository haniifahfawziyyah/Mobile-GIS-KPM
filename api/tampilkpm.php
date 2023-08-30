<?php
include_once "ckoneksi.php";

if (isset($_POST['id'])) {
    $id_kelurahan = $_POST['id'];

    // Parameter binding untuk mencegah SQL injection
    $query = mysqli_prepare($db, "SELECT * FROM hasil 
                                INNER JOIN alternatif ON hasil.id_alternatif = alternatif.id_alternatif 
                                WHERE alternatif.id_kelurahan = ? 
                                AND hasil.kategori IN (?, ?, ?)");

    // Bind parameter untuk kategori
    $kategori1 = "Sangat miskin";
    $kategori2 = "Miskin";
    $kategori3 = "Rentan miskin";
    mysqli_stmt_bind_param($query, "isss", $id_kelurahan, $kategori1, $kategori2, $kategori3);

    // Eksekusi query
    mysqli_stmt_execute($query);

    // Simpan hasil ke dalam array
    $data = array();
    $result = mysqli_stmt_get_result($query);

    if (mysqli_num_rows($result) > 0) {
        while ($row = mysqli_fetch_assoc($result)) {
            // Mengambil data kelurahan dari tabel tb_kelurahan menggunakan satu kueri saja
            $dataayat = mysqli_fetch_array(mysqli_query($db, "SELECT * from tb_kelurahan where id_kelurahan='" . $row['id_kelurahan'] . "'"));

            // Membersihkan data menggunakan htmlspecialchars()
            $data[] = array(
                "id_hasil" => htmlspecialchars($row['id_hasil']),
                "nama_kelurahan" => htmlspecialchars($dataayat['nama_kelurahan']),
                "nik" => htmlspecialchars($row['nik']),
                "nama" => htmlspecialchars($row['nama']),
                "alamat" => htmlspecialchars($row['alamat']),
                "telp" => htmlspecialchars($row['telp']),
                "latitude" => htmlspecialchars($row['latitude']),
                "longitude" => htmlspecialchars($row['longitude'])
            );
        }

        $response = array(
            "value" => 1,
            "results" => $data
        );
    } else {
        $response = array(
            "value" => 0,
            "message" => "Data tidak ditemukan."
        );
    }
} else {
    $response = array(
        "value" => 0,
        "message" => "ID tidak tersedia."
    );
}

header('Content-Type: application/json');
echo json_encode($response, JSON_PRETTY_PRINT);

mysqli_close($db);
?>
