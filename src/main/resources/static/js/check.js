//TODO - Marťo plz fix me - click on check box -> show Bet container
let cd = 0
document.getElementsByName("accept").addEventListener("click", check, false)
function check(){
    console.log(cd)
    if (cd % 2 === 0) {
    document.getElementById("show_me").style.display="block";
    cd++;
}}