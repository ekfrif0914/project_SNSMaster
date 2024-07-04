function emptyChk() {
    var id = $('#id').val()
    var pw = $('#pw').val()
    if (id == "" || pw == "") {
        alert("정보를 입력하세요")
        return false;
    } else {
        return true;
    }
}

if (wrong != null) {
    let text = document.getElementById("text");
    var span = document.createElement('span')
    span.style = "color:red"
    span.innerHTML = wrong
    text.appendChild(span)
} else if (s_text != null && today != null && finish != null) {

    let text = document.getElementById("text");
    var span = document.createElement('span')
    span.style = "color:red"
    span.innerHTML = "회원님의 계정은 정지되었습니다." + "<br>" + "정지 사유: " + s_text + "<br>" + "정지일: " + today +
        "<br>" + "정지 종료일: " + finish + "<br>" + "감사합니다."
    text.appendChild(span)
}
