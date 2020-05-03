$(".upload1").change(function(){
    var file=this.files[0];
    readFile(file);
});
function readFile(file) {
    console.log(file);
    // 新建阅读器
    var reader = new FileReader();
    // 根据文件类型选择阅读方式
    switch (file.type){
        case 'image/jpg':
        case 'image/png':
        case 'image/jpeg':
        case 'image/gif':
            reader.readAsDataURL(file);

            break;
    }

    var formData = new FormData();
    formData.append("myfile",file);
    $.ajax({
        url:"http://111.230.173.74/touxiang.action",
        type:"POST",
        data:formData,
        contentType:false,
        processData:false,
        success:function(data){
            if(data.status == "true") {
                alert("上传成功！");
            }
            if(data.status == "error") {
                alert(data.msg);
            }
        },
        error: function() {
            alert("上传失败！");
        }
    });
   
}