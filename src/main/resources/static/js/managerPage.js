if (wrongmsg != null) {
    let text = document.getElementById("text");
    var span = document.createElement('span')
    span.style = "color:red"
    span.innerHTML = wrongmsg
    text.appendChild(span)
}