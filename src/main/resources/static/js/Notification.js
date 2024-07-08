

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

