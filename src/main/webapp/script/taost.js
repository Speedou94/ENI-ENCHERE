const divtoast = document.getElementById("toast");
function toast(){
    setTimeout(()=>{
        divtoast.classList.add("is-hidden")
    },2000)
}