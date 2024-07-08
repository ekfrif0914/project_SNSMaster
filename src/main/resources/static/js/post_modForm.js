
function chkAvail() {
    if (document.querySelector("#postRegion").value == "none") {
        alert("지역을 선택해주세요");
        return false;
    } else if (document.querySelector("#title").value == "") {
        alert("제목을 입력해주세요");
        return false;
    } else if (document.querySelector("#content").value == "") {
        alert("내용을 입력해주세요");
        return false;
    } else {
        return true;
    }
}

function selOption() {
    document.querySelector('#' + category).setAttribute('selected', true);
    document.querySelector('#' + region).setAttribute('selected', true);
}

selOption();

function changeTag(target) {
    var fname = target.nextElementSibling.getAttribute('value');
    target.parentNode.innerHTML = "<input type=\"file\" name=\"file\"><br><br><input type='hidden' name='delfname' value=" + fname + ">";
    return false;
}

const fileChange = () => {
    $('.file').on('change', (event) => {
        var cnt = $('.file').length;
        if (cnt < 9 - postFileName.length) {
            var targetId = event.target.id;
            if ("file" + cnt == targetId) {
                var newFileInput = document.createElement("input");
                newFileInput.id = "file" + (cnt + 1);
                newFileInput.className = "file";
                newFileInput.type = "file";
                newFileInput.name = "file";
                var newBr = document.createElement("br");
                event.target.after(newFileInput);
                event.target.after(newBr);
                fileChange();
            }
        }
    })
}
fileChange();


$('#m').on('click', () => {
    let popOption = "fullscreen=yes, menubar=yes, toolbar=yes"
    let openUrl = '/managerPage'
    window.open(openUrl, '_blank', 'width=500,height=600,menubar=yes');
});