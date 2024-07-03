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

    function check(id, gno) {

    var userid = id;
    var gn = gno;
    var content = iid + "님이 그룹 가입 신청을 승인하였습니다!";
    var urll = "http://localhost:8080/gpList?gno=" + gn;

    $.ajax({
    type: "GET",
    url: "comment Notification",
    data: {
    userid: userid,
    content: content,
    urll: urll
},
    success: function (response) {
},
    error: function (error) {
}
});
    return false;
}