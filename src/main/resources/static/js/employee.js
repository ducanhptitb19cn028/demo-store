function validateForm() {
    let fname = document.forms["employeeform"]["fname"].value;
    if (fname == "") {
        alert("Điền thông tin họ!");
        return false;
    }
    let lname = document.forms["employeeform"]["lname"].value;
    if (lname == "") {
        alert("Điền thông tin tên!");
        return false;
    }
    let gender = document.forms["employeeform"]["gender"].value;
    if (gender == "") {
        alert("Chưa chọn giới tính!");
        return false;
    }
    let email = document.forms["employeeform"]["email"].value;
    if (email.match(/(?:[a-z0-9+!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|"(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21\x23-\x5b\x5d-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])*")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21-\x5a\x53-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])+)\])/gi)) {
        display.innerHTML = email + ' is valid';
    } else {
        display.innerHTML = email + ' is not a valid email';
    }
    let identity = document.forms["employeeform"]["identity"].value;
    if (identity.match(/^\d{12}$/)) {
        alert("Điền thông tin họ!");
        return false;
    }
}