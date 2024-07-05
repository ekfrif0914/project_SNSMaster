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

function chkAvail() {
    if (document.querySelector("#search").value == "") {
        alert("검색어를 입력해주세요");
        return false;
    } else {
        return true;
    }
}

$(window).on('load', () => {
    var lCategory = 'all';
    loadCategory(lCategory);
})

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

function go() {
    var send_postArray = [];
    var send_gpostArray = [];
    var send_gjoinArray = [];
    var postchkbox = $(".postCheckbox");
    var gpostchkbox = $(".gpostCheckbox");
    var gjoinchkbox = $('.gjoinCheckbox');


    for (i = 0; i < postchkbox.length; i++) {
        if (postchkbox[i].checked) {
            send_postArray.push(postchkbox[i].value);

        }
    }
    for (i = 0; i < gpostchkbox.length; i++) {
        if (gpostchkbox[i].checked) {
            send_gpostArray.push(gpostchkbox[i].value);

        }
    }
    for (i = 0; i < gjoinchkbox.length; i++) {
        if (gjoinchkbox[i].checked) {
            send_gjoinArray.push(gjoinchkbox[i].value);

        }
    }

    var postArray = $("#postArray")
    var gpostArray = $("#gpostArray")
    var gjoinArray = $("#gjoinArray")
    postArray.val(send_postArray);
    gpostArray.val(send_gpostArray);
    gjoinArray.val(send_gjoinArray);
    if (postArray.val() == '' && gpostArray.val() == '' && gjoinArray.val() == '') {
        return false;
    } else {
        return true;
    }
}

function defaultChk() {
    var myfilename = $('#myfilename').val()
    if (myfilename != "") {
        return true;
    } else {
        return false;
    }
}

$('#files').on('change', function () {
    var fileName = $("#files").val();
    $('#filename').text(fileName)
    $('.photosave').css('display', 'inline-block');
});

$('#postSelector').on('change', (event) => {
    console.log("왜???????????")
    var category = event.target.value;
    loadCategory(category);
})

function loadCategory(category) {
    $.ajax({
        type: "get",
        async: false,
        url: "post-category",
        data: {category: category},
        success: function (data) {
            $('#postCard').empty();
            $('#nothing').empty();
            var postCnt = 0;
            if (data.posts != null) {
                postCnt += data.posts.length;
            }
            if (data.gposts != null) {
                postCnt += data.gposts.length;
            }
            if (data.gjoins != null) {
                postCnt += data.gjoins.length;
            }
            $('#postCnt').text("게시글 검색결과: " + postCnt + "건");
            if (postCnt == 0) {
                $('#nothing').html('<br><br>' +
                    '                작성된 글이 없습니다. snsMaster에 글을 작성해 보세요.' +
                    '                <br><br>')
                return;
            }
            for (let post in data.posts) {
                $('#postCard').html(
                    $('#postCard').html() +
                    '                   <div class="col">' +
                    '                        <div class="card h-100">' +
                    '                            <div class="card-header">' +
                    '                                <input class="form-check-input postCheckbox chk" type="checkbox" value=' + data.posts[post].no + '>\n' +
                    '                                <span>' + data.posts[post].category + '</span>' +
                    '                            </div>' +
                    '                            <div class="card-body">' +
                    '                                <h5 class="card-title">' + data.posts[post].title + '</h5>' +
                    '                                <p class="card-text">' + data.posts[post].content + '</p>' +
                    '                                <a href="postDetail?no=' + data.posts[post].no + '" class="card-link">자세히보기</a>' +
                    '                                <div class="cardButton">' +
                    '                                    <a class="noLine" href="postMod?no=' + data.posts[post].no + '">' +
                    '                                        <button class="btn btnf btn-outline-primary">수정</button>' +
                    '                                    </a>' +
                    '                                    <a class="noLine" href="postDelete?no=' + data.posts[post].no + '">' +
                    '                                        <button class="btn btnf btn-outline-primary">삭제</button>' +
                    '                                    </a>' +
                    '                                </div>' +
                    '                            </div>' +
                    '                        </div>' +
                    '                    </div>'
                );
            }
            for (let gpost in data.gposts) {
                $('#postCard').html(
                    $('#postCard').html() +
                    '                    <div class="col">' +
                    '                        <div class="card h-100">' +
                    '                            <div class="card-header">' +
                    '                                <input type="checkbox" class="form-check-input gpostCheckbox chk"' +
                    '                                       value="' + data.gposts[gpost].g_no + '">' +
                    '                                그룹게시글' +
                    '                            </div>' +
                    '                            <div class="card-body">' +
                    '                                <h5 class="card-title">' + data.gposts[gpost].g_title + '</h5>' +
                    '                                <p class="card-text">' + data.gposts[gpost].g_content + '</p>' +
                    '                                <a href="grouppost?g_no=' + data.gposts[gpost].g_no + '" class="card-link">자세히보기</a>' +
                    '                                <div class="cardButton">' +
                    '                                    <a class="noLine" href="modno?g_no=' + data.gposts[gpost].g_no + '">' +
                    '                                        <button class="btn btnf btn-outline-primary">수정</button>' +
                    '                                    </a>' +
                    '                                    <a class="noLine" href="delgroup?g_no=' + data.gposts[gpost].g_no + '&gno=' + data.gposts[gpost].gno + '">' +
                    '                                        <button class="btn btnf btn-outline-primary">삭제</button>' +
                    '                                    </a>' +
                    '                                </div>' +
                    '                            </div>' +
                    '                        </div>' +
                    '                    </div>'
                );
            }
            for (let gjoin in data.gjoins) {
                $('#postCard').html(
                    $('#postCard').html() +
                    '                    <div class="col">' +
                    '                        <div class="card h-100">' +
                    '                            <div class="card-header">' +
                    '                                <input type="checkbox" class="form-check-input gjoinCheckbox chk"' +
                    '                                       value="' + data.gjoins[gjoin].mo_no + '">' +
                    '                                모임모집글' +
                    '                            </div>' +
                    '                            <div class="card-body">' +
                    '                                <h5 class="card-title">' + data.gjoins[gjoin].m_name + '</h5>' +
                    '                                <p class="card-text">' + data.gjoins[gjoin].m_contents + '</p>' +
                    '                                <div class="cardButton">' +
                    '                                    <a class="noLine" href="gmjoinmod?gno=' + data.gjoins[gjoin].gno + '&mo_no=' + data.gjoins[gjoin].mo_no + '">' +
                    '                                        <button class="btn btnf btn-outline-primary">수정</button>' +
                    '                                    </a>' +
                    '                                    <a class="noLine" href="gjdel?gno=' + data.gjoins[gjoin].gno + '&mo_no=' + data.gjoins[gjoin].mo_no + '">' +
                    '                                        <button class="btn btnf btn-outline-primary">삭제</button>' +
                    '                                    </a>' +
                    '                                </div>' +
                    '                            </div>' +
                    '                        </div>' +
                    '                    </div>'
                );
            }
        },
        error: function (request, status, error) {
            console.log(error);
        }
    })
}

function allChk() {
    const allCheckbox = document.querySelector('#allCheckbox')
    const isChecked = allCheckbox.checked
    if (isChecked) {
        const checkboxes = document.querySelectorAll('.chk');
        for (const checkbox of checkboxes) {
            checkbox.checked = true;
        }
    } else {
        const checkboxes = document.querySelectorAll('.chk');
        for (const checkbox of checkboxes) {
            checkbox.checked = false;
        }
    }
}