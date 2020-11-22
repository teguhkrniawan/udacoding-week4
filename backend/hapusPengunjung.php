<?php

include_once ('koneksi.php');

if(!empty($_POST['id'])) {
    $id = $_POST['id'];
    $query = "DELETE FROM pengunjung WHERE id='$id'";
    $delete = mysqli_query($connect, $query);
    if($delete){
        set_response(true, "Berhasil menghapus data");
    } else {
        set_response(false, "Gagal menghapus item");
    }  
} else {
    set_response(false, "parameter id wajib di isi");
}

function set_response($isSuccess, $message) {
    $result = array(
        'isSuccess' =>  $isSuccess,
        'message'   =>  $message
    );

    echo json_encode($result);
}