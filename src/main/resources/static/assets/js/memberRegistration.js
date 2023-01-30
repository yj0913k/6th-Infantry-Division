document.addEventListener('keydown', function(event) {
    if (event.keyCode === 13) {
        event.preventDefault();
    };
}, true);


function gimgChanged(el) {
    var fileData = $(el)[0].files[0];
    console.log(fileData);

    var formData = new FormData()
    formData.append('gimg', fileData);

    $.ajax({
        url: "/admin/temp-upload",
        type: "post",
        contentType: false,
        processData: false,
        data: formData,
        success: function (map) { //서버에 업로드이후 url을 리턴-> 파라미터에 전달
            console.log(map);

            $(el).siblings(".img").css("background-image", "url(" + map.url + ")");
            $(el).siblings(".newName").val(map.newName);
            $(el).siblings(".orgName").val(map.orgName);

        }
    });
}


// 비밀번호 일치여부 확인


function check_pw() {

    var pass = document.getElementById('pass').value;
    var SC = ["!", "@", "#", "$", "%"];
    var check_SC = 0;

    if (pass.length < 6 || pass.length > 16) {
        window.alert('비밀번호는 6글자 이상, 16글자 이하만 이용 가능합니다.');
        document.getElementById('pass').value = '';
    }
    for (var i = 0; i < SC.length; i++) {
        if (pass.indexOf(SC[i]) != -1) {
            check_SC = 1;
        }
    }
    if (check_SC == 0) {
        window.alert('!,@,#,$,% 의 특수문자가 들어가 있지 않습니다.')
        document.getElementById('pass').value = '';
    }
    if (document.getElementById('pass').value != '' && document.getElementById('pass-confirm').value != '') {
        if (document.getElementById('pass').value == document.getElementById('pass-confirm').value) {
            document.getElementById('check').innerHTML = '비밀번호 일치'
            document.getElementById('check').style.color = 'blue'

        } else {
            document.getElementById('check').innerHTML = '비밀번호 불일치';
            document.getElementById('check').style.color = 'red';
        }
    }
}
