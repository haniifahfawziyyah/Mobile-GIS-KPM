<?php
include_once "ckoneksi.php";

// Pastikan elemen 'keyword' dan 'id' ada dalam data POST
if (isset($_POST['keyword']) && isset($_POST['id'])) {
    $nama = $_POST['keyword'];
    $id_kelurahan = $_POST['id'];

    $query = mysqli_query($db, "SELECT * FROM hasil 
                                INNER JOIN alternatif ON hasil.id_alternatif = alternatif.id_alternatif 
                                WHERE alternatif.id_kelurahan = '$id_kelurahan' 
                                AND hasil.kategori IN ('Sangat miskin', 'Miskin', 'Rentan miskin')
                                AND (alternatif.nik LIKE '%" . $nama . "%' OR alternatif.nama LIKE '%" . $nama . "%')");

    $num_rows = mysqli_num_rows($query);

    if ($num_rows > 0) {
        $data = array();
        while ($row = mysqli_fetch_array($query)) {
            $dataayat = mysqli_fetch_array(mysqli_query($db, "SELECT * from tb_kelurahan where id_kelurahan='$row[id_kelurahan]'"));
            if ($row['latitude'] == "") {
                $latitude = "kosong";
            } else {
                $latitude = $row['latitude'];
            }
            if ($row['longitude'] == "") {
                $longitude = "kosong";
            } else {
                $longitude = $row['longitude'];
            }

            if ($row['telp'] == "" or $row['telp'] == "-") {
                $telp = "kosong";
            } else {
                $telp = $row['telp'];
            }

            $char = '"';
            $data[] = array(
                "id_hasil" => str_replace($char, '`', strip_tags($row['id_hasil'])),
                "nama_kelurahan" => str_replace($char, '`', strip_tags($dataayat['nama_kelurahan'])),
                "nik" => str_replace($char, '`', strip_tags($row['nik'])),
                "nama" => str_replace($char, '`', strip_tags($row['nama'])),
                "alamat" => str_replace($char, '`', strip_tags($row['alamat'])),
                "telp" => str_replace($char, '`', strip_tags($telp)),
                "latitude" => str_replace($char, '`', strip_tags($latitude)),
                "longitude" => str_replace($char, '`', strip_tags($longitude))
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
        "message" => "Parameter tidak lengkap."
    );
}

header('Content-Type: application/json');
echo json_encode($response, JSON_PRETTY_PRINT);

mysqli_close($db);