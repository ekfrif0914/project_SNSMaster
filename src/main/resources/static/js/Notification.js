
    if (id != null) {//로그인 되어있으면
    console.log(id)
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
    console.log(id)
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

    $(".likeBtn").on("click", (event) => {
    var postElement = $(event.currentTarget).closest('.post');
    var userid = postElement.find('b:eq(0)').text()
    var no = event.target.parentElement.parentElement.parentElement.id
    console.log(userid);

    $.ajax({
    type: "post",
    async: false,
    url: "postLike",
    data: {no: no, id: id},
    success: function (data){
    if(data.isLike){
    event.target.src="img/filledHearts.png"
    var content=id+"님이 좋아요를 눌렀습니다!"
    console.log(content);
    $.ajax({
    type: "get",
    url: "like Notification",
    data: {userid: userid, content: content},
    success: function(){

}, error: function (error) {

}
});
} else {
    event.target.src="img/heart.png"
}
    event.target.nextElementSibling.innerText = data.likeCnt;
},
    error: function (request, status, error) { // 결과 에러 콜백함수
    console.log(error)
}
})
})

    $(".report").on("click", (event) => {
    var no = event.target.parentElement.parentElement.id
    $.ajax({
    type: "post",
    async: false,
    url: "postReport",
    data: {no: no, id: id},
    success: function (data){
    if(data){
    alert("이미 신고한 게시글입니다.")
} else {
    alert("신고하였습니다.")
}
},
    error: function (request, status, error) { // 결과 에러 콜백함수
    console.log(error)
}
})
})

