$(".upload1").change(function () {
    var file = this.files[0];
    readFile(file);
});
function readFile(file) {
     if(file.size>1024*500){
        alert("图片大小不能大于500k！");
        return false;
    }
    // 新建阅读器
    var reader = new FileReader();
    // 根据文件类型选择阅读方式
    switch (file.type) {
        case 'image/jpg':
        case 'image/png':
        case 'image/jpeg':
        case 'image/gif':
            reader.readAsDataURL(file);
            break;
        default: alert("非图片不能上传！");
            return false;
    }
    // 当文件阅读结束后执行的方法
    reader.addEventListener('load', function () {
        // 如果说让读取的文件显示的话 还是需要通过文件的类型创建不同的标签
        switch (file.type) {
            case 'image/jpg':
            case 'image/png':
            case 'image/jpeg':
            case 'image/gif':
                $("#touxiang").attr("src", reader.result);
                break;
        }
    });

    var formData = new FormData();
    formData.append("myfile", file);
    $.ajax({
        url: "http://localhost:8080//touxiang.action",
        type: "POST",
        data: formData,
        contentType: false,
        processData: false,
        error: function (jqXHR, textStatus, errorThrown) {
            /*弹出jqXHR对象的信息*/
            alert(jqXHR.responseText);
            alert(jqXHR.status);
            alert(jqXHR.readyState);
            alert(jqXHR.statusText);
            /*弹出其他两个参数的信息*/
            alert(textStatus);
            alert(errorThrown);
        },
        success: function (data) {
            alert("SUCCESS!");
        }
    });

}

function showFriend() {
    let a = document.getElementById("fff");
    let b = document.getElementById("hhh");
    let c = document.getElementById("alluser");
    a.className = "fff";
    b.className = "hhh";
    c.className = "hhh";
    $('#friendList').show();
    $('#hate').hide();
    $('#allu').hide();
}

function showHate() {
    let a = document.getElementById("fff");
    let b = document.getElementById("hhh");
    let c = document.getElementById("alluser");
    a.className = "hhh";
    b.className = "fff";
    c.className = "hhh";
    $('#friendList').hide();
    $('#hate').show();
    $('#allu').hide();
}

function showAlluser() {
    let a = document.getElementById("fff");
    let b = document.getElementById("hhh");
    let c = document.getElementById("alluser");
    a.className = "hhh";
    b.className = "hhh";
    c.className = "fff";
    $('#friendList').hide();
    $('#hate').hide();
    $('#allu').show();
}

$(function () {
    var $hate = $('#hatetable');
    var currentPage = 0;//当前页默认值为0  
    var pageSize = 7;//每一页显示的数目  
    $hate.bind('paging1', function () {
        $hate.find('tbody tr').hide().slice(currentPage * pageSize, (currentPage + 1) * pageSize).show();
    });
    var sumRows = $hate.find('tbody tr').length;
    var sumPages = Math.ceil(sumRows / pageSize);//总页数  

    var ul2 = $('#ul2');
    for (var pageIndex = 0; pageIndex < sumPages; pageIndex++) {
        ul2.append($('<li><a href="#">' + (pageIndex + 1) + '</a></li>').bind("click", { "newPage": pageIndex }, function (event) {
            currentPage = event.data["newPage"];
            $hate.trigger("paging1");
            //触发分页函数  
        }))
    }
    $hate.trigger("paging1");

})


$(function () {
    var $top = $('#top');
    var currentPage = 0;//当前页默认值为0  
    var pageSize = 7;//每一页显示的数目  
    $top.bind('paging', function () {
        $top.find('li').hide().slice(currentPage * pageSize, (currentPage + 1) * pageSize).show();
    });
    var sumRows = $top.find('li').length;
    var sumPages = Math.ceil(sumRows / pageSize);//总页数  


    var $last1 = $('#fenye');
    for (var pageIndex = 0; pageIndex < sumPages; pageIndex++) {
        $last1.append($('<li><a href="#">' + (pageIndex + 1) + '</a></li>').bind("click", { "newPage": pageIndex }, function (event) {
            currentPage = event.data["newPage"];
            $top.trigger("paging");
            //触发分页函数  
        }))

    }
    $top.trigger("paging");

})

$(function () {
    var $fri = $('#friendList');
    var currentPage = 0;//当前页默认值为0  
    var pageSize = 7;//每一页显示的数目  
    $fri.bind('paging2', function () {
        $fri.find('tbody tr').hide().slice(currentPage * pageSize, (currentPage + 1) * pageSize).show();
    });
    var sumRows = $fri.find('tbody tr').length;
    var sumPages = Math.ceil(sumRows / pageSize);//总页数  


    var ul1 = $('#ul1');
    for (var pageIndex = 0; pageIndex < sumPages; pageIndex++) {
        ul1.append($('<li><a href="#">' + (pageIndex + 1) + '</a></li>').bind("click", { "newPage": pageIndex }, function (event) {
            currentPage = event.data["newPage"];
            $fri.trigger("paging2");
            //触发分页函数  
        }))

    }
    $fri.trigger("paging2");

})

$(function () {
    var $all = $('#alltable');
    var currentPage = 0;//当前页默认值为0  
    var pageSize = 7;//每一页显示的数目  
    $all.bind('paging2', function () {
        $all.find('tbody tr').hide().slice(currentPage * pageSize, (currentPage + 1) * pageSize).show();
    });
    var sumRows = $all.find('tbody tr').length;
    var sumPages = Math.ceil(sumRows / pageSize);//总页数  


    var ul3 = $('#ul3');
    for (var pageIndex = 0; pageIndex < sumPages; pageIndex++) {
        ul3.append($('<li><a href="#">' + (pageIndex + 1) + '</a></li>').bind("click", { "newPage": pageIndex }, function (event) {
            currentPage = event.data["newPage"];
            $all.trigger("paging2");
            //触发分页函数  
        }))

    }
    $all.trigger("paging2");

})
