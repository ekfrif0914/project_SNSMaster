function chk(){
    a=document.querySelector('#time').value
    if(a==""){
        document.querySelector('#dd').innerHTML="정지 기간을 입력해주세요"
        return false
    }
    else{
        return true
    }
}