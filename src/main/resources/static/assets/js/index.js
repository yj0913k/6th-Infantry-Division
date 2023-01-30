/**
 * 
 */
const remainTime = document.querySelector("#remain-time");

function diffDay() {
    const masTime = new Date("2020-12-25");
    const todayTime = new Date();
    const diff = todayTime -masTime;

    const diffDay = String(Math.floor(diff / (1000*60*60*24)));
    
//    const diffHour =String(Math.floor((diff / (1000*60*60)) % 24)).padStart(2,"0");
//    const diffMin = String(Math.floor((diff / (1000*60)) % 60)).padStart(2,"0");
//    const diffSec = String(Math.floor(diff / 1000 % 60)).padStart(2,"0");
    
    remainTime.innerText = `${diffDay}일`;
}

diffDay();
setInterval(diffDay, 1000);