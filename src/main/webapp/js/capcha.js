/**
 *Author M.Sydorenko
 */

function validate(f) {
    var flag = get_action(f);
    if (flag){
        f.submit();
    }
}

function get_action(form) {

    var v = grecaptcha.getResponse();
    if(v.length == 0)
    {
        document.getElementById('captcha').innerHTML="Confirm that you are not a robot!";
        return false;
    }
    if(v.length != 0)
    {
        document.getElementById('captcha').innerHTML="Congratulation! You can enter.";
        return true;
    }
}