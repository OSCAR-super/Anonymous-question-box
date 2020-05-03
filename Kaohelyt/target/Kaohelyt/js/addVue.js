var fir=new Vue({
    el:'#fir',
    data:{
        fpass:"",
        isZhengque:true
    },
    watch:{
        fpass:function(val){
            localStorage.setItem("fir",val);
            if(val==""){
            }
            if(val.length<6||val.length>16||!isNaN(val)){
                this.isZhengque=false;
                localStorage.setItem("a","false");
            }
            else{
                this.isZhengque=true;
                localStorage.setItem("a","true");
            }
        }
    }
})

var sec=new Vue({
    el:'#sec',
    data:{
        spass:"",
        isPassword:true
    },
    watch:{
        spass:function(val){
            let fir=localStorage.getItem("fir");
            if(val==fir){
                this.isPassword=true;
                localStorage.setItem("b","true");
            }else{
                this.isPassword=false;
                localStorage.setItem("b","false");
            }
        }
    }
})

var name=new Vue({
    el:'#mingzi',
    data(){
        return{
            isValid:true,
            username:""
        }
    },
    watch:{
        username:function(val){
            axios.get('http://111.230.173.74/fN.action?UserName='+val)
                .then(res=>{
                    if(res.data==1){
                        this.isValid=true;
                        localStorage.setItem("c","true");
                    }else{
                        this.isValid=false;
                        localStorage.setItem("c","false");
                    }
                })
                .catch(error=>{
                    console.log(error);
                })
        }
    }
})

var btn=document.getElementById("tijiao");

setInterval(function(){
    let a=localStorage.getItem('a');
    let c=localStorage.getItem('c');
    let b=localStorage.getItem('b');
    if(a=="true"&&b=="true"&&c=="true"){
        btn.disabled=false;
    }
    else{
        btn.disabled=true;
    }

},1000);
