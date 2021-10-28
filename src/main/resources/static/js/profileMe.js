function getProfile() {
    let userName = $('#fname').val();
    let password = $('#fpass').val();
    $.ajax({
        headers: {'Authorization': "Basic " + btoa(userName + ":" + password)},
        url: "http://localhost:8080/profiles/me",
        type: 'GET',
        dataType: 'json',
        success: function (data) {
            console.log(data);
            $('#name').text(data['nickname']);
            $('#email').text(data['email']);
            $('#gender').text(data['gender']);
            $('#attraction').text(data['attraction']);
        },
        error: function() { alert('Failed!'); },

    });
};

