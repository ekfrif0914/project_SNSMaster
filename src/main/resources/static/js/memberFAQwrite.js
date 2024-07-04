
if (id != null) {//로그인되어있으면
    loginChk = true;
} else {
    loginChk = false;
}

if (id != null) {//로그인 되어있으면
    $("#login").remove()
    let area = document.getElementById("mmode");
    var button = document.createElement("button")
    button.id = "logout"
    button.type = button
    button.className = "btn btn-primary"
    button.innerHTML = "로그아웃"
    button.style = "font-size: 15px"
    $('#id').val(id)
    $('#id').attr("readonly", true)
    area.appendChild(button)

} else {//로그아웃 되어있으면
    $('#logout').remove()
    let area = document.getElementById("mmode");
    var button = document.createElement("button")
    button.id = "login"
    button.type = button
    button.className = "btn btn-primary"
    button.innerHTML = "로그인"
    area.appendChild(button)
}

$('#login').on('click', () => {
    location.href = 'loginForm'
});

$('#logout').on('click', () => {
    location.href = 'logout'
});

$('#m').on('click', () => {
    let openUrl = '/managerPage'
    window.open(openUrl, '_blank', 'width=500,height=600,menubar=yes');

});

function chkAvail() {

    if (document.querySelector("#title").value == "") {
        alert("제목을 입력해주세요");
        return false;
    } else if (document.querySelector("#content").value == "") {
        alert("내용을 입력해주세요");
        return false;
    } else if (document.querySelector("#id").value == "") {
        alert("아이디를 입력해주세요")
        return false;
    } else {
        if (memberChk()) {//true이면 회원
            if (!loginChk) {
                var str = prompt('비밀번호를 입력하세요', '');
                if (str == null) {
                    return false;
                } else if (str == nologinpw) {
                    return true;
                } else if (str != nologinpw) {
                    alert("비밀번호가 맞지 않습니다")
                    return false;
                }
            }
            return true;
        } else {//비회원
            return false;
        }
    }
}

function memberChk() {
    var id = $("#id").val();
    var result_return;
    $.ajax({
        type: "get",
        async: false,
        url: "memberCheck",
        data: {id: id},
        success: function (data) {
            if (data != "회원아님") {//회원임,비밀번호 저장,true 리턴
                result_return = true;
                nologinpw = data;
            } else {
                alert("회원만 작성 가능합니다")
                result_return = false;
            }
        },
        error: function (request, status, error) { // 결과 에러 콜백함수
            console.log(error)
        }
    });
    return result_return;
};