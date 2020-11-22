<?php

include_once ('koneksi.php');

if(!empty($_POST['nama_pengunjung']) && !empty($_POST['jenis_kelamin']) && !empty($_POST['alamat'])){

    $nama_pengunjung = $_POST['nama_pengunjung'];
    $jenis_kelamin = $_POST['jenis_kelamin'];
    $alamat = $_POST['alamat'];

    $query = "INSERT INTO pengunjung(nama_pengunjung, jenis_kelamin, alamat) VALUES ('$nama_pengunjung', '$jenis_kelamin', '$alamat')";

    $insert = mysqli_query($connect, $query);
    if ($insert) {
        set_response(true, "Berhasil menyimpan data");
    } else {
        set_response(false, "Gagal menyimpan data");
    }
} else {
    set_response(false, "nama pengunjung, jenis kelamin, dan alamat harus di isi");
}

function set_response($isSuccess, $message) {
    $result = array(
        'isSucess'  => $isSuccess,
        'message'   => $message
    );
    echo json_encode($result);
}