function pwChk() {
    var pw1 = $('#pw1').val()
    var pw2 = $('#pw2').val()

    if(pw1!=pw2){
        alert("비밀번호가 일치하지 않습니다")
        $('.btn').attr("type","button")
    }else{
        if(confirm("탈퇴하시겠습니까? 7일 후에 계정이 삭제됩니다")){
            $('.btn').attr("type","submit")
        }

    }
}
