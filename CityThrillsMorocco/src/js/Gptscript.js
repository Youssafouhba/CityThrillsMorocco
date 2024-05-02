const token = "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";

const gptPrompt = document.getElementById("gptBtn");
const textArea = document.getElementById("exampleFormControlTextarea1");
const answerArea = document.getElementById("blankh6")

const sendBtn = document.getElementById("inputGroupFileAddon04")

gptPrompt.addEventListener("click", generateGptResponse);

function generateGptResponse() {
    console.log(textArea.value)
    
    fetch("https://api.openai.com/v1/chat/completions", {
        method: "POST",
        headers: {
            "Content-type": "application/json",
            "Authorization": "Bearer " + token,
        },
        body: JSON.stringify({
            "model": "gpt-3.5-turbo",
            "messages": [{"role": "user", "content": textArea.value}]
        })  
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        return response.json();
    })
    .then(data => {
        answerArea.innerText = data.choices[0].message.content;
    })
    .catch(error => {
        console.error('Error:', error);
    });
}
// temporary solution
function refreshPage() {
    location.reload()
}

sendBtn.addEventListener("click", refreshPage)

