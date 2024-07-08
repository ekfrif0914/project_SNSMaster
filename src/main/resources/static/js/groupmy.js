
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