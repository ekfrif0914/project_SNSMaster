if (resultmsg != null) {
    alert(resultmsg)
} else if (resultid != null) {
    alert("아이디: " + resultid)
} else if (resultpwMsg != null) {
    alert(resultpwMsg)
}

function idemptyChk() {
    var name = $('#name').val()
    var email = $('#email').val()
    if (id == "" || email == "") {
        alert("정보를 입력하세요")
        return false;
    } else {
        return true;
    }
}

function pwemptyChk() {
    var pw = $('#id').val()
    var email1 = $('#email1').val()
    if (pw == "" || email1 == "") {
        alert("정보를 입력하세요")
        return false;
    } else {
        return true;
    }
}