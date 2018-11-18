function checkform() {
    var fname = document.forms["AddOrUpdateForm"]["fname"];
    var lname = document.forms["AddOrUpdateForm"]["lname"];
    var login = document.forms["AddOrUpdateForm"]["login"];
    var password = document.forms["AddOrUpdateForm"]["password"];
    var country = document.forms["AddOrUpdateForm"]["country"];
    var city = document.forms["AddOrUpdateForm"]["city"];
    var street = document.forms["AddOrUpdateForm"]["street"];
//	var house = document.forms["AddOrUpdateForm"]["house"]; 
//	var flat = document.forms["AddOrUpdateForm"]["flat"]; 
    var phone = document.forms["AddOrUpdateForm"]["phone"];
    var email = document.forms["AddOrUpdateForm"]["email"];
    var billNumber = document.forms["AddOrUpdateForm"]["bill"];
//	var billBalance = document.forms["AddOrUpdateForm"]["balance"]; 

    var regexFNAME = /^[a-zA-Zа-яА-Я]{3,15}/;
    if (!regexFNAME.test(fname.value)) {
        alert("First name must be 3-15 number of characters: " + fname.value);
        fname.focus();
        return false;
    }
    if (!regexFNAME.test(lname.value)) {
        alert("Last name must be 3-15 number of characters: " + lname.value);
        lname.focus();
        return false;
    }

    var regexLogin = /^[+a-z0-9]{4,10}/;
    if (!regexLogin.test(login.value)) {
        alert("Login must contains english 4-10 number of characters in lover case or numbers : " + login.value);
        login.focus();
        return false;
    }

    var regexPassword = /^[0-9a-zA-Z!@#$%^&*]{4,20}/;
    if (!regexPassword.test(password.value)) {
        alert("Password must contain 4-20 characters and can contains english letters, numbers, and" +
            "special characters  !@#$%^&*: " + password.value);
        password.focus();
        return false;
    }

    var regexAdress = /^[a-zA-Zа-яА-Я]{2,20}/
    if (!regexAdress.test(country.value)) {
        alert("Country name 2-20 characters long: " + country.value);
        country.focus();
        return false;
    }

    if (!regexAdress.test(city.value)) {
        alert("City name 2-20 characters long: " + city.value);
        city.focus();
        return false;
    }

    if (!regexAdress.test(street.value)) {
        alert("Street name 2-20 characters long: " + street.value);
        street.focus();
        return false;
    }

    var regexTelephone = /^380[0-9]{9}/;
    if (!regexTelephone.test(phone.value)) {
        alert("Phone must be in format 380XXXXXXXXX (12 numbers long): " + phone.value);
        phone.focus();
        return false;
    }

    var regexEmail = /^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$/;
    if (!regexEmail.test(email.value)) {
        alert("Email must be in format xxxx@xxxx.xxx or xxx.xxxx@xxx.xxx: " + email.value);
        email.focus();
        return false;
    }

    var regexBillNumber = /^[0-9]{5,20}/;
    if (!regexBillNumber.test(billNumber.value)) {
        alert("Bill must be 5-20 numbers long: " + billNumber.value);
        billNumber.focus();
        return false;
    }
    return true;
}