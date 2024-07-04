$('#sido').val(region1).attr("selected", true);

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

var target = $('#region')

if (region1 == "광역시/특별시") var sidetailOption = metrocity;
else if (region1 == "경기도") var sidetailOption = gyeonggido;
else if (region1 == "강원도") var sidetailOption = Gangwondo;
else if (region1 == "충청북도") var sidetailOption = Chungcheongbukdo;
else if (region1 == "충청남도") var sidetailOption = Chungcheongnamdo;
else if (region1 == "전라북도") var sidetailOption = Jeollabukdo;
else if (region1 == "전라남도") var sidetailOption = Jeollanamdo;
else if (region1 == "경상북도") var sidetailOption = Gyeongsangbukdo;
else if (region1 == "경상남도") var sidetailOption = Gyeongsangnamdo;
else if (region1 == "제주도") var sidetailOption = jejudo;


for (x in sidetailOption) {
    var opt = document.createElement("option");
    opt.value = sidetailOption[x];
    opt.innerHTML = sidetailOption[x];
    target.append(opt)
}


$('#region').val(region).attr("selected", true);

function updateSave() {

    var name = $('#name').val()
    var newpw = $('#pw').val()
    var newpwchk = $('#pwchk').val()

    if (name == "" || newpw != "" && newpwchk == "" || newpw == "" && newpwchk != "") {
        alert("수정 항목을 입력하세요")
        return false;
    } else if (newpw == "" && newpwchk == "" && name != "") {
        if (oldpwChk() && newchk()) {
            return true;
        }
    } else {

        if (pwChk() && newchk() && oldpwChk()) {
            return true;
        } else {
            //console.log(pwChk() + "1")//비밀번호유효성
            //console.log(newchk() + "2")//새 비밀번호와 확인
            //console.log(oldpwChk() + "3")//현재 비밀번호와 일치하는지 확인
            return false;
        }
    }

}

function newchk() {
    var pw = $('#pw').val()
    var pwchk = $('#pwchk').val()
    if (pw == "" && pwchk == "") {
        var oldpw = $('#oldpw').val()
        $('#pw').val(oldpw)
        return true;
    } else {
        if (pw != pwchk) {
            alert("새 비밀번호와 비밀번호 확인이 일치하지 않습니다")
            return false;
        } else {
            return true;
        }
    }
}

function oldpwChk() {
    var oldpw = $('#oldpw').val()
    var userpw = $('#userpw').val()
    if (oldpw != userpw) {
        alert("비밀번호를 정확하게 입력해주세요")
        return false;
    } else {
        return true;
    }
}

function pwChk() {
    var pw1 = $('#pw').val()
    var num = pw1.search(/[0-9]/g);
    var eng = pw1.search(/[a-z]/ig);
    var spe = pw1.search(/[!@#$%^&*]/gi);
    var noticePw = $('#noticePw')

    if (pw1 == "") {
        noticePw.text("영문+숫자+특수문자(!@#$%^&*) (4~10자리)로 작성하세요")
        noticePw.css("color", "grey")
        return false;
    } else if (pw1.length < 4 || pw1.length > 10) {
        noticePw.text("4자리 ~ 10자리 이내로 입력해주세요.");
        noticePw.css("color", "red");
        return false;
    } else if (pw1.search(/\s/) != -1) {
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
        // sum()
        return true;
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


    target.find("option").remove()

    for (x in sidetailOption) {
        var opt = document.createElement("option");
        opt.value = sidetailOption[x];
        opt.innerHTML = sidetailOption[x];
        target.append(opt)
    }
}
