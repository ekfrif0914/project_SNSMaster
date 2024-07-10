function delck() {
    return confirm("선택하신 게시글을 삭제 하시겠습니까?")

}

function modelck() {
    return confirm("선택하신 동행글을 삭제 하시겠습니까?")
}

function memberck(mo_no, gno) {
    let popOption = "width=450px, height=400px, top=100px, right=30%, scrollbars=yes";
    let openUrl = '/joinpopup?mo_no=' + mo_no;
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
    let openUrl = '/managerPage'
    window.open(openUrl, '_blank', 'width=500,height=600,menubar=yes');
});


$(document).on('click', '.glck', function (event) {//좋아요
    event.preventDefault();
    var g_no = $(this).data('g_no')
    const aa = event.target.parentElement;
    const bb = aa.parentNode;
    $.ajax({
        type: "get",
        async: false,
        url: "grouplike",
        data: {g_no: g_no},
        success: function (data) {
            if (data == 0) {
                bb.previousElementSibling.innerText = (Number(bb.previousElementSibling.innerText) + 1);
            } else if (data == 1) {
                bb.previousElementSibling.innerText = (Number(bb.previousElementSibling.innerText) - 1);
            } else {
            }
        },
        error: function (request, status, error) {
        }
    });
});
$(document).on('click', '#report', function (event) {//신고
    event.preventDefault();
    var g_no = $(this).data('g_no')
    const aa = event.target.parentElement;
    const bb = aa.parentNode;
    $.ajax({
        type: "get",
        async: false,
        url: "greport",
        data: {g_no: g_no},
        success: function (data) {

            if (data == 0) {
                bb.previousElementSibling.innerText = (Number(bb.previousElementSibling.innerText) + 1);
                alert("신고 되었습니다")
            } else if (data == 1) {
                alert("이미 신고 하셨습니다")
                bb.previousElementSibling.innerText = (Number(bb.previousElementSibling.innerText));
            }
        },
        error: function (request, status, error) {
        }
    });
});

$(document).on('click', '.gjoin', function (event) {
    event.preventDefault();
    var mo_no = $(this).data('mo_no')
    var gno = $(this).data('gno')
    var cnt = $(this).data('cnt')
    var cont = $(this).data('cont')
    const aa = event.target.parentElement;
    const bb = aa.parentNode;
    const cc = bb.previousElementSibling.previousElementSibling;

    $.ajax({
        type: "get",
        async: false,
        url: "g_memberjoin",
        data: {mo_no: mo_no, gno: gno, cnt: cnt, cont: cont},
        success: function (data) {
            if (bb.previousElementSibling.innerText == cc.previousElementSibling.innerText) {
                alert("신청인원이 초과 되었습니다.")
            } else if (bb.previousElementSibling.innerText != cc.previousElementSibling.innerText) {
                if (data == 0) {
                    bb.previousElementSibling.innerText = (Number(bb.previousElementSibling.innerText) + 1);
                    alert("신청되었습니다")
                } else if (data == 1) {
                    alert("이미 신청 하셨습니다")
                    bb.previousElementSibling.innerText = (Number(bb.previousElementSibling.innerText));
                }
            }
        },
        error: function (request, status, error) {
        }
    });
});

