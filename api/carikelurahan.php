<?php
include_once "ckoneksi.php";

// Memeriksa elemen 'keyword' ada dalam data POST
if (isset($_POST['keyword'])) {
    $nama = $_POST['keyword'];

    // Menggunakan parameter binding untuk mencegah SQL injection
    $query = mysqli_prepare($db, "SELECT * FROM tb_kelurahan WHERE nama_kelurahan LIKE ?");

    // Bind parameter untuk keyword pencarian
    $search_keyword = "%" . $nama . "%";
    mysqli_stmt_bind_param($query, "s", $search_keyword);

    // Eksekusi query
    mysqli_stmt_execute($query);

    $result = mysqli_stmt_get_result($query);

    if (mysqli_num_rows($result) > 0) {
        $data = array();
        while ($row = mysqli_fetch_array($result)) {
            $kategori = array("Sangat miskin", "Miskin", "Rentan miskin"); // Daftar kategori yang ingin difilter
            $kategori = implode("','", $kategori);

            // Menghitung jumlah data hasil berdasarkan id_kelurahan dan kategori
            $sqlj = "SELECT COUNT(*) AS jumlah FROM hasil 
                     INNER JOIN alternatif ON hasil.id_alternatif = alternatif.id_alternatif 
                     WHERE alternatif.id_kelurahan = ? 
                     AND hasil.kategori IN ('$kategori')";

            // Menggunakan parameter binding untuk mencegah SQL injection
            $queryj = mysqli_prepare($db, $sqlj);
            mysqli_stmt_bind_param($queryj, "i", $row['id_kelurahan']);

            // Eksekusi query
            mysqli_stmt_execute($queryj);

            $resultj = mysqli_stmt_get_result($queryj);
            $rowj = mysqli_fetch_array($resultj);

            $data[] = array(
                "id" => $row['id_kelurahan'],
                "nama" => $row['nama_kelurahan'],
                "lat" => $row['latitude_kelurahan'],
                "lng" => $row['longitude_kelurahan'],
                "jumlah" => $rowj['jumlah']
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
        "message" => "Keyword tidak diberikan."
    );
}

header('Content-Type: application/json');
echo json_encode($response, JSON_PRETTY_PRINT);

mysqli_close($db);
