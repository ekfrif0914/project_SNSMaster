function modck() {
    return confirm("수정 하시겠습니까?")
}


$('#m').on('click', () => {
    let openUrl = '/managerPage'
    window.open(openUrl, '_blank', 'width=500,height=600,menubar=yes');
});