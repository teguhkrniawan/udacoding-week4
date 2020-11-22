<?php

include_once ('koneksi.php');

if(!empty($_POST['nama_pengunjung']) && !empty($_POST['jenis_kelamin']) && !empty($_POST['alamat']) && !empty($_POST['id']))
{
    $id = $_POST['id'];
    $nama_pengunjung = $_POST['nama_pengunjung'];
    $jenis_kelamin = $_POST['jenis_kelamin'];
    $alamat = $_POST['alamat'];

    $query = "UPDATE pengunjung SET nama_pengunjung='$nama_pengunjung', jenis_kelamin='$jenis_kelamin', alamat='$alamat' WHERE id='$id'";

    $update = mysqli_query($connect, $query);

    if($update){
        set_response(true, "Berhasil perbarui data");
    } else {
        set_response(false, "Gagal perbarui data");
    }   
} else {
    set_response(false, "Parameter nama pengunjung, alamat, id, dan jenis kelamin harus di isi");
}

function set_response($isSuccess, $message){
    $result = array(
        'isSuccess' =>  $isSuccess,
        'message'   =>  $message
    );
    echo json_encode($result);
}