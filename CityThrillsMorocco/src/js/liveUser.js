let userName = document.getElementById("userName")
console.log(userName)
let logOutBtn = document.getElementById("logOutBtn")
// let logInBtn = document.getElementById("login-btn")
let isLoggedIn = true

if (localStorage.currentsigneduser) {
        let parsedInfo = JSON.parse(localStorage.getItem("currentsigneduser"))
        console.log(parsedInfo)
        let liveUserName = parsedInfo[0].name
        console.log(liveUserName)
        userName.textContent = liveUserName
        isLoggedIn = true
} 

logOutBtn.addEventListener("click", function () {
    localStorage.removeItem("currentsigneduser")
    setTimeout(function () {
            window.location.href = "../../page.html";
    }, 500);
})

