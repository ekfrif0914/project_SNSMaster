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

$(window).on('load', function () {
    var apiKey = 'fbb0870cd3a95e3a4ae807939c8d2841'; // 여기에 자신의 OpenWeatherMap API 키를 입력하세요
    for (let i = 0; i < $('.weathers').length; i++) {
        var city = $('.city').eq(i).val();
        var apiUrl = `https://api.openweathermap.org/data/2.5/weather?q=${city}&appid=${apiKey}&units=metric&lang=kr`;
        $.ajax({
            url: apiUrl,
            method: 'GET',
            success: function (data) {
                var iconCode = data.weather[0].icon;
                var iconUrl = "http://openweathermap.org/img/wn/" + iconCode + ".png";
                var weatherData = `
                <span>지역: ${data.name}</span>
               <img src="${iconUrl}">
                <span>온도: ${data.main.temp} °C</span>
                <span>날씨: ${data.weather[0].description}</span>
                <span>습도: ${data.main.humidity}%</span>
                <span>풍속: ${data.wind.speed} m/s</span>
            `;
                $('.weatherResult').eq(i).html(weatherData);
            },
            error: function (error) {
                $('.weatherResult').eq(i).html('<p>An error occurred</p>');
            }
        });
    }
});
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


setInterval(() => {
    var target = $('.show');
    if (target.next('.noticeList').length == 0) {
        target.attr("class", "noticeList");
        $('.noticeList:eq(0)').attr("class", "noticeList show");
    } else {
        target.attr("class", "noticeList");
        target.next('.noticeList').attr("class", "noticeList show");
    }
    $('.noticeList').css({
        "display": "none"
    })
    $('.show').css({
        "display": "block"
    })
}, 11000)

setInterval(() => {
    var target = $('.showFestival');
    if (target.next('.festivalList').length == 0) {
        target.attr("class", "festivalList");
        $('.festivalList:eq(0)').attr("class", "festivalList showFestival");
    } else {
        target.attr("class", "festivalList");
        target.next('.festivalList').attr("class", "festivalList showFestival");
    }
    $('.festivalList').css({
        "display": "none"
    })
    $('.showFestival').css({
        "display": "block"
    })
}, 11000)

setInterval(() => {
    var target = $('.showWeather');
    if (target.next('.weathers').length == 0) {
        target.attr("class", "weathers");
        $('.weathers:eq(0)').attr("class", "weathers showWeather");
    } else {
        target.attr("class", "weathers");
        target.next('.weathers').attr("class", "weathers showWeather");
    }
    $('.weathers').css({
        "display": "none"
    })
    $('.showWeather').css({
        "display": "block"
    })
}, 5000)


$(document).ready(function () {
    $.ajax({
        type: "GET",
        url: "/notification state",
        data: {id: id},
        success: function (state) {
            if (state > 0) {
                $('#Notification').attr('src', 'img/notification-bell.png');
            }
        },
        error: function (error) {
            console.log("Error: ", error);
        }
    });
});


function notification() {
    location.href = "notification?id=" + id;
}


$(document).on("click", ".likeBtn", (event) => {
    console.log("몇번실행?")
    var postElement = $(event.currentTarget).closest('.post');
    var userid = postElement.find('b:eq(0)').text()
    var no = event.target.parentElement.parentElement.parentElement.id
    if (id != null) {
        $.ajax({
            type: "post",
            async: false,
            url: "postLike",
            data: {no: no, id: id},
            success: function (data) {
                if (data.isLike) {
                    event.target.src = "img/filledHearts.png"
                    var content = id + "님이 좋아요를 눌렀습니다!"
                    var urll = "postDetail?no=" + no
                    $.ajax({
                        type: "get",
                        url: "like Notification",
                        data: {no:no,userid: userid, content: content, urll: urll},
                        success: function () {

                        }, error: function (error) {
                            console.log(error)
                        }
                    });
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

$(document).on("click", ".report", (event) => {
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

$(document).on("click", ".follow", (event) => {
    var postElement = $(event.currentTarget).closest('.post');
    var userid = postElement.find('b:eq(0)').text()
    var followid = event.target.previousElementSibling.firstElementChild.innerHTML
    $.ajax({
        type: "post",
        async: false,
        url: "following",
        data: {userid: id, followid: followid},
        success: function (data) {
            if (data) {//true이면 팔로우 함
                var content= id+"님이 당신을 팔로우합니다!"
                var urll="/yourPage?id="+ id
                $.ajax({
                    type: "get",
                    url: "follow Notification",
                    data: {id:id,userid: userid, content: content,urll:urll},
                    success: function(){
                    }, error: function (error) {
                        console.log(error)
                    }
                });

                var postidstag = document.querySelectorAll(".postid")
                for (var i = 0; i < postidstag.length; i++) {
                    if (postidstag.item(i).innerText == followid) {
                        postidstag.item(i).parentElement.nextElementSibling.innerHTML = "팔로우 취소"
                    }
                }
            } else {//false 이면 팔로우 취소
                var postidstag = document.querySelectorAll(".postid")
                for (var i = 0; i < postidstag.length; i++) {
                    if (postidstag.item(i).innerText == followid) {
                        postidstag.item(i).parentElement.nextElementSibling.innerHTML = "팔로우"
                    }
                }
            }
        },
        error: function (request, status, error) { // 결과 에러 콜백함수
            console.log(error)
        }
    })
})

//스크롤 바닥 감지
$('.title').on('scroll', function (event) {
    if ((document.querySelector('#show').getBoundingClientRect().top) <= window.innerHeight) {
        var lastNo = $('.post').last().attr('id')
        $.ajax({
            type: "get",
            async: false,
            url: "post",
            data: {lastNo: lastNo, sword: sword, region: region, category: category},
            success: function (data) {
                var addContent = "";
                $(data).each(function (index, postMap) {
                    addContent += '<div class="post" id="' + postMap.post.no + '">';
                    if (postMap.profileImgName != null){
                        addContent += '<img src="download?filename=' + postMap.profileImgName + '" class="profileImg" alt="...">';
                    } else {
                        addContent += '<img src="img/user.png" class="profileImg" alt="...">';
                    }

                    if (id == postMap.post.id) {
                        addContent += '<a class="noLine" href="myPage"> <b class="postid">' + postMap.post.id + '</b></a>';
                    } else {
                        addContent += '<a class="noLine" href="yourPage?id=' + postMap.post.id + '"> <b class="postid noLine">' + postMap.post.id + '</b></a>';
                    }
                    if (id != null) {
                        if (postMap.post.followstate) {
                            addContent += '<button type="button" class="follow btn btt btn-secondary">팔로우 취소</button>';
                        } else if (id != postMap.post.id) {
                            addContent += '<button class="follow btn btt btn-secondary" type="button">팔로우</button>'
                        }
                    }
                    addContent += '<span class="right">';
                    if (id == postMap.post.id) {
                        addContent +=

                            '<a class="noLine" href="postMod?no=' + postMap.post.no + '">수정</a>' +
                            '<a class="noLine" href="postDelete?no=' + postMap.post.no + '">삭제</a>'

                    }
                    addContent +=
                        '                    <a class="noLine" href="postDetail?no=' + postMap.post.no + '">자세히보기</a>' +
                        '                </span><br>' +
                        '                    <span>' + postMap.post.region + '</span>' +
                        '                    <span>' + postMap.post.category + '</span>' +
                        '                    <br>' +
                        '                    <hr>' +
                        '                    <b>' + postMap.post.title + '</b><br>';
                    if (postMap.post.file_name[0] != null) {
                        addContent +=
                            '                    <div id="carouselExample' + index + '"' +
                            '                         class="carousel slide slideContainer">' +
                            '                        <div class="carousel-inner">';
                        $.each(postMap.post.file_name, function (j, fname) {
                            if (fname != null) {
                                if (j == 0) {
                                    addContent +=
                                        '                            <div class="fileContainer carousel-item active">' +
                                        '                                <img src="download?filename=' + fname + '" class="d-block w-100 postFile" alt="...">' +
                                        '                            </div>';
                                } else {
                                    addContent +=
                                        '                            <div class="fileContainer carousel-item">' +
                                        '                                <img src="download?filename=' + fname + '" class="d-block w-100 postFile" alt="...">' +
                                        '                            </div>';
                                }
                            }
                        })
                        addContent +=
                            '                        </div>' +
                            '                        <button class="carousel-control-prev" type="button"' +
                            '                                data-bs-target="#carouselExample' + index + '" data-bs-slide="prev">' +
                            '                            <span class="carousel-control-prev-icon" aria-hidden="true"></span>' +
                            '                            <span class="visually-hidden">Previous</span>' +
                            '                        </button>' +
                            '                        <button class="carousel-control-next" type="button"' +
                            '                                data-bs-target="#carouselExample' + index + '" data-bs-slide="next">' +
                            '                            <span class="carousel-control-next-icon" aria-hidden="true"></span>' +
                            '                            <span class="visually-hidden">Next</span>' +
                            '                        </button>' +
                            '                    </div>';
                    }
                    addContent +=
                        '                    <br>' +
                        '                    <span>' + postMap.post.content + '</span><br>' +
                        '                    <span class="postFoot">' +
                        '                    <a id="cmtIcon" class="left" href="postDetail?no=' + postMap.post.no + '&cmtFocus=true">' +
                        '                        <img class="postImg" src="img/chat.png">';
                    $.each(comments, function (j, cmt) {
                        addContent += '<span>';
                        if (cmt.NO == postMap.post.no) {
                            addContent += '<span th:text="${cmt.cnt}">' + cmt.cnt + '</span>';
                        }
                        addContent += '</span>';
                    })
                    addContent +=
                        '                    </a>' +
                        '                <img class="postImg report" src="img/siren.png">' +
                        '                <span class="right">'
                    if (postMap.post.like) {
                        addContent += '<img class="likeBtn postImg" src="img/filledHearts.png">';
                    } else {
                        addContent += '<img class="likeBtn postImg" src="img/heart.png">';
                    }
                    addContent +=
                        '                    <span>' + postMap.post.good + '</span>' +
                        '                </span>' +
                        '                </span>' +
                        '                </div>';
                })
                $('#postFrame').append(addContent);
            },
            error: function (request, status, error) { // 결과 에러 콜백함수
                console.log(error)
            }
        })
    }
});