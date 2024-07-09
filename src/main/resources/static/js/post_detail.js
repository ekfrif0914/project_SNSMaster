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

function chkLogin() {
    if (id == null) {
        document.querySelector("#cmt").setAttribute("placeholder", "로그인 후 이용해주세요");
        document.querySelector("#cmtbtn").disabled = true;
    }
}

chkLogin();

function chkCommentAvail() {
    if (document.querySelector("#cmt").value == "") {
        alert("댓글을 입력해주세요");
        return false;
    } else {
        var userid = $(".idddd").text()
        console.log(userid);
        let content = id + "님이 댓글을 달았습니다"
        var no = $("#cmtContent").val()
        var urll = "postDetail?no=" + no

        $.ajax({
            type: "get",
            url: "comment Notification",
            data: {no: no, userid: userid, content: content, urll: urll},
            success: function () {
            }, error: function (error) {
                console.log(error)
            }
        });
        return true;
    }
}

$(".likeBtn").on("click", (event) => {
    if (id != null) {
        var no = event.target.parentElement.parentElement.parentElement.id
        $.ajax({
            type: "post",
            async: false,
            url: "postLike",
            data: {no: no, id: id},
            success: function (data) {
                var userid = $('.idddd').text()
                var no = $("#cmtContent").val()
                var urll = "postDetail?no=" + no
                let content = id + "님이 좋아요를 눌렀습니다"
                $.ajax({
                    type: "get",
                    url: "like Notification",
                    data: {no: no, userid: userid, content: content, urll: urll},
                    success: function () {
                    }, error: function (error) {
                        console.log(error)
                    }
                });
                if (data.isLike) {
                    event.target.src = "img/filledHearts.png"
                } else {
                    event.target.src = "img/heart.png"
                }
                event.target.nextElementSibling.innerText = data.likeCnt;
            },
            error: function (request, status, error) { // 결과 에러 콜백함수
                console.log(error)
            }
        })
    }
})

$(".report").on("click", (event) => {
    if (id != null) {
        var no = event.target.parentElement.parentElement.id
        $.ajax({
            type: "post",
            async: false,
            url: "postReport",
            data: {no: no, id: id},
            success: function (data) {
                if (data) {
                    alert("이미 신고한 게시글입니다.")
                } else {
                    alert("신고하였습니다.")
                }
            },
            error: function (request, status, error) { // 결과 에러 콜백함수
                console.log(error)
            }
        })
    }
})

$(".follow").on("click", (event) => {
    var followid = $('.idddd').text()
    $.ajax({
        type: "post",
        async: false,
        url: "following",
        data: {userid: id, followid: followid},
        success: function (data) {
            if (data) {//true이면 팔로우 함
                var content = id + "님이 당신을 팔로우 합니다!"
                var urll = "yourPage?id=" + id
                $.ajax({
                    type: "get",
                    url: "follow Notification",
                    data: {id: id, userid: followid, content: content, urll: urll},
                    success: function () {
                    }, error: function (error) {
                        console.log(error)
                    }
                });
                event.target.innerHTML = "팔로우 취소"
            } else {//false 이면 팔로우 취소
                event.target.innerHTML = "팔로우"


            }

        },
        error: function (request, status, error) { // 결과 에러 콜백함수
            console.log(error)
        }
    })
})

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