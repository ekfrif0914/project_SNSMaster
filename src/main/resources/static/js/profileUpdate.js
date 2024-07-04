$('#file').on('change', function () {

    var fileName = $("#file").val();
    console.log(fileName)
    $('#filename').text(fileName)

});