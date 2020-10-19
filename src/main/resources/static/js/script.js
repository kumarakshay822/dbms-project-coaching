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
