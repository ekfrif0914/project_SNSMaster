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
    let openUrl = '/managerPage'
    window.open(openUrl, '_blank', 'width=500,height=600,menubar=yes');


});


    function check() {
    if (document.querySelector("#cmt").value == "") {
    alert("댓글을 적어주세요")
    return false
} else if (document.querySelector("#cmt").value.length > 40) {
    alert("제목은 최대 40자 입니다.")
    return false
} else {
    return true;
}
}

