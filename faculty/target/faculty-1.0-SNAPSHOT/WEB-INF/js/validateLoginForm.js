function validate(form) {
    let emailPatter = /[admin@gmail.com]/;
    let emailI = form.email.value;
    if (emailPatter.test(emailI)) {
        document.getElementById("emailErr").innerHTML = "<span>Incorrect Email</span>"
    }

}
console.log("connect to login form")