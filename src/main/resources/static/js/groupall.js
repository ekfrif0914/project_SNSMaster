function joinck(gno) {
    let popOption = "width=450px, height=400px, top=100px, right=30%, scrollbars=yes";
    let openUrl = '/grouppopup?gno=' + gno;
    window.open(openUrl, 'pop', popOption);
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
    let popOption = "fullscreen=yes, menubar=yes, toolbar=yes"
    let openUrl = '/managerPage'
    window.open(openUrl, '_blank', 'width=500,height=600,menubar=yes');
});


    $(document).on('click', '#gjoinck', function (event) {
    event.preventDefault();
    var gno = $(this).closest('div').attr('id');
    var id = event.target.parentElement.previousElementSibling.previousElementSibling.previousElementSibling.previousElementSibling.previousElementSibling.innerText
    $.ajax({
    type: "get",
    async: false,
    url: "http://localhost:8080/groupjoin",
    data: {gno: gno},
    success: function (data) {
    var urll = "groupmy?gno=" + gno
    var content = userid + "님이 가입 신청을 넣었습니다"
    $.ajax({
    type: "get",
    url: "groupinput Notification",
    data: {gno:gno, userid: id, content: content, urll: urll},
    success: function () {
}, error: function (error) {
}
});
    if (data == 0) {
    alert("가입 승인 대기 상태 입니다.");
} else if (data == 1) {
    alert("이미 가입 되었습니다.");
}
},
    error: function (request, status, error) {
}
});
});

