const sendBtn = document.getElementById("save-information-btn")
const namePlace = document.getElementById("basic-icon-default-fullname")
const emailPlace = document.getElementById("basic-icon-default-email")
const phonenoPlace = document.getElementById("basic-icon-default-phone")
//just for the memes , do not get offended bro
const tableXd = document.getElementById("niggawatt")


function getInputsValue() {
  let a = namePlace.value;
  let b = emailPlace.value;
  let c = phonenoPlace.value; 
  
  if (a !== "" && b !== "" && c !== "") {
    tableXd.innerHTML += '<tr><td><span class="fw-medium">' + a + '</span></td><td>' + b + '</td><td>' + c + '</td></tr>';
  } else {
    console.log("bro if you are reading this, there is no error you are just dumb!");
  }
}

sendBtn.addEventListener("click",getInputsValue)
