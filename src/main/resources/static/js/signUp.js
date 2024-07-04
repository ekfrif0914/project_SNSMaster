var isIdChecked = false;
var emailChecked = false;
var plusrow = false;

function allchk() {
    var id = $('#id').val()
    var emailid = $('#emailid').val()
    var domain = $('#domain').val()
    var pw = $('#pw').val()
    var pw1 = $('#pw1').val()
    var region = $('#region').val()
    var sido = $('#sido').val()
    var name = $('#name').val()

    if (id == "" || emailid == "" || domain == "" || pw == "" || pw1 == "" || region == "" || sido == "" || name == "") {
        alert("공백이 있습니다")
        return false;

    } else {
        if (isIdChecked && emailChecked && pwChk() && eachChk() && idChk()) {
            sum()
            $("#jb").attr("type", "submit");
            $("form").submit();
        } else if (!emailChecked) {
            alert("이메일 인증이 필요합니다")
        } else if(!pwChk()){
            alert("비밀번호는 영문+숫자+특수문자(!@#$%^&*) (4~10자리)로 작성하세요")
        }else if(!eachChk()){
            alert("비밀번호가 일치하지 않습니다")
        }else if(!idChk()){
            alert("아이디는 영문 or 한글(자음/모음만 사용불가)+숫자(2~20자리)로 작성하세요")
        }else if(!isIdChecked){
            alert("아이디 중복확인을 진행하세요")
        }
    }
}

function setDomain(e) {
    const text = e.options[e.selectedIndex].text;

    if (text == "직접 입력") {
        $('#domain').val("")
        $('#domain').attr("readonly", false)
    } else if (text == "선택") {
        $('#domain').val("")
        $('#domain').attr("readonly", true)
    } else {
        $('#domain').val(text)
        $('#domain').attr("readonly", true)
    }
}

function eachChk() {
    var pw1 = $('#pw1').val()
    var pw = $('#pw').val()
    var chkPw = $('#chkPw')
    if (pw1 != pw) {
        chkPw.text("비밀번호가 맞지 않습니다")
        chkPw.css("color", "red")
        return false;
    } else if (pw1 == "" || pw == "") {
        chkPw.text("")
        return false;
    } else {
        chkPw.text("비밀번호 일치")
        chkPw.css("color", "blue")
        return true;
    }
}

function sum() {
    var emailid = $('#emailid').val()
    var domain = $('#domain').val()
    var hiddn = $('#hid')
    hiddn.val(emailid + "@" + domain)
}

function pwChk() {
    var pw = $('#pw1').val()
    var num = pw.search(/[0-9]/g);
    var eng = pw.search(/[a-z]/ig);
    var spe = pw.search(/[!@#$%^&*]/gi);
    var noticePw = $('#noticePw')

    if (pw == "") {
        noticePw.text("영문+숫자+특수문자(!@#$%^&*) (4~10자리)로 작성하세요")
        noticePw.css("color", "grey")
        return false;
    } else if (pw.length < 4 || pw.length > 10) {
        noticePw.text("4자리 ~ 10자리 이내로 입력해주세요.");
        noticePw.css("color", "red");
        return false;
    } else if (pw.search(/\s/) != -1) {
        noticePw.text("비밀번호는 공백 없이 입력해주세요.");
        noticePw.css("color", "red");
        return false;
    } else if (num < 0 || eng < 0 || spe < 0) {
        noticePw.text("영문, 숫자, 특수문자를 혼합하여 입력해주세요.");
        noticePw.css("color", "red");
        return false;
    } else {
        noticePw.text("영문+숫자+특수문자(!@#$%^&*)(4~10자리)")
        noticePw.css("color", "green")
        return true;
    }
}

function idChk() {
    isIdChecked = false;
    var id = $('#id').val()
    var num = id.search(/[0-9]/g);
    var koreng = id.search(/[가-힣a-zA-Z]/g);
    var noticeId = $('#noticeId')
    var spe = id.search(/[\{\}\[\]\/?.,;:|\)*~`!^\-+<>@\#$☆★○●◎Ŋž※◀◁◆◇◈◎◌♥♧♨♣♂」ぉ】☏☎☜◌♂♀☞♨%&\\\=\(\'\"]/gi);

    if (id == "") {
        noticeId.text("영문 or 한글(자음/모음만 사용불가)+숫자(2~20자리)로 작성하세요");
        noticeId.css("color", "gray");

        return false;
    } else if (id.length < 2 || id.length > 20) {
        noticeId.text("2자리~20자리 이내로 입력해주세요")
        noticeId.css("color", "red")

        return false;
    } else if (koreng < 0 || num < 0 || spe > 0) {
        noticeId.text("영문 또는 한글, 숫자를 포함하세요. 단, 한글 자음/모음만 사용 불가")
        noticeId.css("color", "red")

        return false;
    } else {
        noticeId.text("중복 확인을 진행하세요")
        noticeId.css("color", "green")
        return true;
    }
}

$("#overlappedID").click(function () {
    $('#jb').attr("type", "button");
    var id = $("#id").val();
    if (idChk()) {
        $.ajax({
            type: "get",
            async: false,
            url: "idCheck",
            data: {id: id},
            success: function (data) {
                if (data == 1) {
                    $('#noticeId').text("이미 사용중인 ID 입니다.");
                    $("#noticeId").css("color", "red");
                    isIdChecked = false;
                } else {
                    $('#noticeId').text("사용 가능한 ID 입니다.");
                    $("#noticeId").css("color", "blue");
                    isIdChecked = true;
                }
            },
            error: function (request, status, error) { // 결과 에러 콜백함수
                console.log(error)
            }

        })
    } else {
        alert("형식을 지키세요")
    }
});

$('#authentication').click(function () {
    var emailid = $('#emailid').val()
    var domain = $('#domain').val()
    if (emailid != "" && domain != "") {
        sum();
        var email = $('#hid').val()

        $.ajax({
            type: "get",
            async: false,
            url: "emailCheck",
            data: {email: email},
            success: function (data) {
                if (data == 1) {
                    alert("이미 인증된 이메일입니다")
                } else {
                    if (!plusrow) {
                        let area = document.getElementById("area");
                        let newP = document.createElement('p');
                        newP.innerHTML =
                            "<input class='form-control' type='text' placeholder='인증번호를 입력하세요' aria-label='default input example' id='number'> " +
                            "<input type='button' value='확인' onclick='confirmNumber()'>"

                        area.appendChild(newP);
                        $.ajax({
                            type: "get",
                            async: false,
                            url: "emailConfirm",
                            data: {email: email},
                            success: function (data) {
                                $("#confirm").attr("value", data);
                            },
                            error: function (request, status, error) { // 결과 에러 콜백함수
                                console.log(error)
                            }
                        })
                    }
                    plusrow = true;
                }
            },

            error: function (request, status, error) { // 결과 에러 콜백함수
                console.log(error)
            }

        })
    } else {
        alert("이메일을 입력하세요")
    }
});

function confirmNumber() {
    var number1 = $("#number").val();
    var number2 = $("#confirm").val();
    if (number1 != "") {
        if (number1 == number2) {
            alert("인증되었습니다.");
            emailChecked = true;
            $("#number").attr("readonly", true)
        } else {
            alert("번호가 다릅니다.");
        }
    } else {
        alert("인증번호를 입력하세요")

    }
}

function categoryChange(e) {
    var metrocity = ["서울특별시", "인천광역시", "대전광역시", "광주광역시", "대구광역시", "울산광역시", "부산광역시"];
    var gyeonggido = ["수원시", "고양시", "과천시", "광명시", "광주시", "구리시", "군포시", "김포시",
        "남양주시", "동두천시", "부천시", "성남시", "시흥시", "안산시", "안성시", "안양시", "양주시",
        "오산시", "용인시", "의왕시", "의정부시", "이천시", "파주시",
        "평택시", "포천시", "하남시", "화성시"]

    var Gangwondo = ["강릉시", "동해시", "삼척시", "속초시", "원주시", "춘천시", "태백시"];
    var Chungcheongbukdo = ["제천시", "청주시", "충주시"];
    var Chungcheongnamdo = ["계룡시", "공주시", "논산시", "보령시", "서산시", "아산시", "천안시"];
    var Jeollabukdo = ["군산시", "김제시", "남원시", "익산시", "전주시", "정읍시"]
    var Jeollanamdo = ["광양시", "나주시", "목포시", "순천시", "여수시"];
    var Gyeongsangbukdo = ["경산시", "경주시", "구미시", "김천시", "문경시", "상주시", "안동시", "영주시", "영천시", "포항시"];
    var Gyeongsangnamdo = ["거제시", "김해시", "마산시", "밀양시", "사천시", "양산시", "진주시", "진해시", "창원시", "통영시"]
    var jejudo = ["서귀포시", "제주시"];
    var choice = ["* 선택"];

    var target = $('#region')

    if (e.value == "광역시/특별시") var sidetailOption = metrocity;
    else if (e.value == "경기도") var sidetailOption = gyeonggido;
    else if (e.value == "강원도") var sidetailOption = Gangwondo;
    else if (e.value == "충청북도") var sidetailOption = Chungcheongbukdo;
    else if (e.value == "충청남도") var sidetailOption = Chungcheongnamdo;
    else if (e.value == "전라북도") var sidetailOption = Jeollabukdo;
    else if (e.value == "전라남도") var sidetailOption = Jeollanamdo;
    else if (e.value == "경상북도") var sidetailOption = Gyeongsangbukdo;
    else if (e.value == "경상남도") var sidetailOption = Gyeongsangnamdo;
    else if (e.value == "제주도") var sidetailOption = jejudo;
    else if (e.value == "시/도 선택") var sidetailOption = choice;

    target.find("option").remove()

    for (x in sidetailOption) {
        var opt = document.createElement("option");
        if (sidetailOption[0] != "* 선택") {
            opt.value = sidetailOption[x];
        } else {
            opt.value = ""
        }
        opt.innerHTML = sidetailOption[x];
        target.append(opt)
    }
}
