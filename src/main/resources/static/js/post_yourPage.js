
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

$(window).on('load', () => {
    var lcategory = 'all';
    console.log("window on load 실행")
    loadCategory(lcategory);
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

$('#postSelector').on('change', (event) => {
    console.log("postSelector on change 실행")
    var lcategory = event.target.value;
    loadCategory(lcategory);
})

function loadCategory(category) {
    $.ajax({
        type: "get",
        async: false,
        url: "post-yourcategory",
        data: {category: category, id: memberid},
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
                    '                작성된 글이 없습니다. ' +
                    '                <br><br>')
                return;
            }
            for (let post in data.posts) {
                $('#postCard').html(
                    $('#postCard').html() +
                    '                   <div class="col">' +
                    '                        <div class="card h-100">' +
                    '                            <div class="card-header">' +
                    '                                <span>' + data.posts[post].category + '</span>' +
                    '                            </div>' +
                    '                            <div class="card-body">' +
                    '                                <h5 class="card-title">' + data.posts[post].title + '</h5>' +
                    '                                <p class="card-text">' + data.posts[post].content + '</p>' +
                    '                                <a href="postDetail?no=' + data.posts[post].no + '" class="card-link">자세히보기</a>' +
                    '                                <div class="cardButton">' +
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
                    '                                </div>' +
                    '                            </div>' +
                    '                        </div>' +
                    '                    </div>'
                );
            }
        },
        error: function (request, status, error) {
        }
    })
}

$(".follow").on("click", (event) => {
    var userid = $("#idText").text()
    var followid = event.target.previousElementSibling.innerText
    console.log("userid")
    $.ajax({
        type: "post",
        async: false,
        url: "following",
        data: {userid: id, followid: followid},
        success: function (data) {
            if (data) {//true이면 팔로우 함
                var content = id + "님이 당신을 팔로우합니다!"
                var urll = "yourPage?id=" + id
                $.ajax({
                    type: "get",
                    url: "follow Notification",
                    data: {id:id,userid:userid, content: content, urll: urll},
                    success: function () {
                    }, error: function (error) {
                    }
                });
                event.target.innerHTML = "팔로우 취소"
                var myfollowCount = $('#myfollowCount').text()
                var number = Number(myfollowCount) + 1
                $('#myfollowCount').text(number)
                var newli = document.createElement("li")
                newli.setAttribute("class", "me")
                newli.innerHTML = "<a class='dropdown-item me' href='myPage'>" + "<span id='idme' class='me'>" + "</span>" + "</a>"

                var ul = event.target.nextElementSibling.nextElementSibling
                ul.appendChild(newli)
                $('#idme').text(id)

            } else {//false 이면 팔로우 취소
                event.target.innerHTML = "팔로우"
                var myfollowCount = $('#myfollowCount').text()
                var number = Number(myfollowCount) - 1
                $('#myfollowCount').text(number)

                $('.me').remove()

            }
        },
        error: function (request, status, error) { // 결과 에러 콜백함수
            console.log(error)
        }
    })
})