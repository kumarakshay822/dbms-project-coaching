function deleteUser(url){
    if (confirm('Do you want to delete this User? \nWarning! This action is destructible!')){
        $.ajax({
            url: url,
            type: "get",
            success: function (data, status, xhr){
                location.reload();
            },
            error:function (error){
                alert(error);
            }
        });
    }
}

function activateUser(url) {
    $.ajax({
        url: url,
        type: "get",
        success: function (data, status, xhr){
            location.reload();
        },
        error:function (error){
            alert(error);
        }
    });
}

function passwordToggleClick() {
    $(".toggle-password").toggleClass("fa-eye fa-eye-slash");
    var input = $('input[name = "password"]');
    if (input.attr("type") == "password") {
        input.attr("type", "text");
    } else {
        input.attr("type", "password");
    }
};

var linkToText = {
    login: "Login",
    register: "Register",
    admin: "Admin",
    users: "All Users",
    students: "Student Portal",
    staff: "Staff Portal",
    teacher: "Teacher Portal"
};

function getBreadcrumb(url, currentLink, isLast) {
    var text = currentLink;
    if (currentLink in linkToText) {
        text = linkToText[currentLink];
    }
    if (!isLast) {
        return '\n<li class="breadcrumb-item active"><a href="' + url + '">' + text + '</a></li>'
    }
    return '\n<li class="breadcrumb-item active" aria-current="page">' + text + '</li>'
}

$(".breadcrumb").html($('<li class="breadcrumb-item active"><a href="/">Home</a></li>'));
var pathArray = window.location.pathname.split('/');
var url = "";
for (i = 1; i < pathArray.length; i++) {
    var currentLink = pathArray[i];
    url += "/" + currentLink;
    var isLast = 0;
    if(i == pathArray.length - 1) {
        isLast = 1;
    }
    $(".breadcrumb").append(getBreadcrumb(url, currentLink, isLast));
};
