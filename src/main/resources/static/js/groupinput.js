

    $('#m').on('click', () => {
    let popOption = "fullscreen=yes, menubar=yes, toolbar=yes"
    let openUrl = '/managerPage'
    window.open(openUrl, '_blank', 'width=500,height=600,menubar=yes');
});


    function check() {
    if (document.querySelector("#g_title").value == "") {
    alert("제목을 적어주세요")
    return false
} else if (document.querySelector("#g_title").value.length > 40) {
    alert("제목은 최대 40자 입니다.")
    return false
} else if (document.querySelector("#g_content").value == "") {
    alert("내용을 적어주세요")
    return false
} else if (document.querySelector("#g_content").value.length > 100) {
    alert("내용은 최대 100자 입니다.")
    return false
} else {
    return true;
}
}

    function mochek() {
    if (document.querySelector("#m_name").value === "") {
    alert("모임 이름을 작성해 주세요")
    return false
} else if (document.querySelector("#m_name").value.length > 40) {
    alert("제목은 최대 40자 입니다.")
    return false
} else if (document.querySelector("#cnt").value === "") {
    alert("모집 인원을 적어주세요")
    return false
} else if (document.querySelector("#cnt").value > 50) {
    alert("모집 인원은 최대 50명 입니다.")
    return false
} else if (document.querySelector("#m_contents").value === "") {
    alert("모집 내용을 작성해 주세요")
    return false
} else if (document.querySelector("#m_contents").value.length > 100) {
    alert("내용은 최대 100자 입니다.")
    return false
} else {
    return true;
}
}
