// script.js
function confirmDelete(vehicleId) {
    if (confirm("Are you sure you want to delete this vehicle?")) {
        window.location.href = "/vehicles/delete/" + vehicleId;
    }
}

// script.js
function validateForm() {
    var make = document.getElementById("make").value;
    var model = document.getElementById("model").value;
    var year = document.getElementById("year").value;
    var vin = document.getElementById("vin").value;
    var price = document.getElementById("price").value;

    if (make == "" || model == "" || year == "" || vin == "" || price == "") {
        alert("All fields are required");
        return false;
    }
    return true;
}