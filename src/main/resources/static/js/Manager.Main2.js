
    $(document).ready(function () {
    var b = ['말미잘', '바보', '쓰레기', '전민정'];
    $('tr').each(function () {
    var content = $(this).find('td:nth-child(9)').text();
    var count = 0;
    for (var i = 0; i < b.length; i++) {
    var idx = content.indexOf(b[i]);
    while (idx !== -1) {
    count++;
    idx = content.indexOf(b[i], idx + 1);
}
}
    $(this).find('td:nth-child(9)').text(count);
});
});

    const excelDownload = document.querySelector('#excelDownload');
    document.addEventListener('DOMContentLoaded', () => {
    excelDownload.addEventListener('click', exportExcel);
});

    function exportExcel() {
// step 1. workbook 생성
    var wb = XLSX.utils.book_new();

// step 2. 시트 만들기
    var newWorksheet = excelHandler.getWorksheet();

// step 3. workbook에 새로만든 워크시트에 이름을 주고 붙인다.
    XLSX.utils.book_append_sheet(wb, newWorksheet, excelHandler.getSheetName());

// step 4. 엑셀 파일 만들기
    var wbout = XLSX.write(wb, {bookType: 'xlsx', type: 'binary'});

// step 5. 엑셀 파일 내보내기
    saveAs(new Blob([s2ab(wbout)], {type: "application/octet-stream"}), excelHandler.getExcelFileName());
}

    var excelHandler = {

    getExcelFileName : function(){
    return '그룹 게시물.xlsx';	//파일명

},
    getSheetName: function () {
    return 'Table Test Sheet';	//시트명
},
    getExcelData: function () {
    return document.getElementById('mana'); 	//TABLE id
},
    getWorksheet: function () {
    return XLSX.utils.table_to_sheet(this.getExcelData());
}
}

    function s2ab(s) {
    var buf = new ArrayBuffer(s.length); //convert s to arrayBuffer
    var view = new Uint8Array(buf);  //create uint8array as viewer
    for (var i = 0; i < s.length; i++) view[i] = s.charCodeAt(i) & 0xFF; //convert to octet
    return buf;
}

    document.getElementById('chk').addEventListener('click', function() {
    var checkboxes = document.querySelectorAll('.checkbox:checked');
    var aa = [];
    for (var i = 0; i < checkboxes.length; i++) {
    aa.push(checkboxes[i].value);
}
    location.href="fordelete2?no="+aa
});



    function look2(n,i) {
    var post=document.getElementById("jungtae")
    if(post.style.display==="block"){
    post.display="none";
}else{
    post.style.display="block";
}
    var no = n
    var id = i
    $.ajax({
    url: `/look2?no=${no}&id=${id}`,
    method: 'GET',
    success: function (a) {
    document.getElementById('pp').innerText = a.post.id
    document.getElementById('abc').innerText = a.post.g_content
    document.getElementById('link').href = `/StopAND?no=${no}&id=${id}`;
    document.getElementById('good').innerText = a.post.g_like
    document.getElementById('title').innerText = a.post.g_title
    document.getElementById('vv').innerText = a.post.g_content
    var b = ['말미잘', '바보', '쓰레기', '전민정'];
    const w = document.querySelector('#abc').innerText
    let c = document.querySelector('#vv').innerText
    let ss= document.querySelector("#ss")
    let count = 0;
    let aa={}
    let sum=0;
    for (let i = 0; i < b.length; i++) {
    let idx = w.indexOf(b[i]);
    while (idx !== -1) {
    count++;
    sum++
    idx = w.indexOf(b[i], idx + 1);
}
    if (count > 1) {
    aa[i] = b[i] + "=" + count
    count = 0;
}
}
    ss.innerHTML="총위반횟수="+sum;
    let result = Object.values(aa)
    $('#abc').text(result);

    for (let i = 0; i < b.length; i++) {
    let idx = c.indexOf(b[i]);
    while (idx !== -1) {
    idx = c.indexOf(b[i], idx + 1);
}
    c = c.replaceAll(b[i], '<span style="background-color:red">' + b[i] + "</span>")
}
    document.querySelector('#vv').innerHTML = c;
},
    error: function (error) {
    console.error(error);
}
})
}

