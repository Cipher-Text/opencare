import Swal from "sweetalert2";


export async  function DeleteAlert() {
    const result = await Swal.fire({
        title: "Are you sure?",
        text: "You won't be able to revert this!",
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        confirmButtonText: "Yes, delete it!",
        allowOutsideClick: false
    });
    return result.isConfirmed;
}

export async  function CustomHTMLAlert(title,html) {
    const result = await Swal.fire({
        title: title,
        html: html,
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        confirmButtonText: "Confirm",
        allowOutsideClick: false
    });
    return result.isConfirmed;
}

export async  function ConfirmAlert(title, Btn1="Confirm", Btn2="Cancel") {
    const result = await Swal.fire({
        title: title,
        icon: "success",
        showDenyButton: true,
        confirmButtonText: Btn1,
        denyButtonText: Btn2,
        allowOutsideClick: false,
        customClass:"card bg-gray",
        confirmButtonColor: "#212529",
        denyButtonColor: '#d33',
        iconColor: '#198754',
    });
    return result;
}

export async  function SuccessAlert(msg) {
    const result = await Swal.fire({
        text: msg,
        icon: "success",
        confirmButtonColor: "#198754",
        confirmButtonText: "OK",
        allowOutsideClick: false
    });
    return result.isConfirmed;
}

export async  function FailAlert(msg) {
    const result = await Swal.fire({
        text: msg,
        icon: "warning",
        confirmButtonColor: "#fcac3f",
        confirmButtonText: "Try Again",
        allowOutsideClick: false
    });
    return result.isConfirmed;
}

export async  function InfoAlert(msg) {
    const result = await Swal.fire({
        text: msg,
        icon: "info",
        confirmButtonColor: "#198754",
        confirmButtonText: "Go Ahead",
        allowOutsideClick: false
    });
    return result.isConfirmed;
}