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
        document.getElementById('captcha').innerHTML="You can't leave Captcha Code empty";
        return false;
    }
    if(v.length != 0)
    {
        document.getElementById('captcha').innerHTML="Captcha completed";
        return true;
    }
}