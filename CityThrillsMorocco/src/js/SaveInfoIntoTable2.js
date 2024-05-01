const sendBtn = document.getElementById("save-information-btn")
const namePlace = document.getElementById("basic-icon-default-fullname")
const textPlace = document.getElementById("exampleFormControlTextarea1")
//just for the memes , do not get offended bro
const tableXd = document.getElementById("niggawatt")


function getInputsValue() {
  let a = namePlace.value;
  let b = textPlace.value;
  
  if (a !== "" && b !== "") {
    tableXd.innerHTML += '<tr><td><span class="fw-medium">' + a + '</span></td><td>' + b + '</td><td>' ;
  } else {
    console.log("bro if you are reading this, there is no error you are just dumb!");
  }
}

sendBtn.addEventListener("click",getInputsValue)
