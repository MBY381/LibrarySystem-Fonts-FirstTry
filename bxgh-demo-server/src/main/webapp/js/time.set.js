let wait = 60;

function time(obj) {
    if (wait === 0) {
        obj.removeAttribute("disabled");
        obj.innerHTML = "获取验证码";
        wait = 60;
    } else {
        obj.setAttribute("disabled", true);
        obj.innerHTML = wait + "秒后重新发送";
        wait--;
        setTimeout(function () {
                time(obj)
            },
            1000)
    }
}