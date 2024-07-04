function checkbokChk() {

    if ($('input:checkbox[id="flexCheckChecked"]').is(":checked")) {
        $('.btn').attr("type", "submit")
    } else {
        alert("탈퇴 안내를 확인하고 동의해 주세요.")
        $('.btn').attr("type", "button")
    }
}
