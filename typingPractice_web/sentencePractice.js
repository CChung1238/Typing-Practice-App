let second = 0.0;
let crtWPM = 0.0;
let fastestWPM = 0.0;
let percentPcs = 100;
let timer;
let sampleStc = []; 

// initialize given text when page loaded
window.onload = function() {
    loadSampleText(); 

    const userInput = document.getElementById('userInput');
    let isTextMatched = false; // Track if the text matches

    userInput.addEventListener('input', handleInput);
    userInput.addEventListener('keydown', handleKeydown);
}

function handleInput() {
    const givenText = document.getElementById('sampleText').innerText;
    const typedText = this.value;

    if (typedText.length === 1) {
        stopTimer();
        second = 0.0;
        startTimer();
    }

    if (typedText === givenText) {
        isTextMatched = true; // Mark text as matched
    } else {
        isTextMatched = false; // Mark text as not matched
        speedCalc(second);
        compareText();
    }
}

function handleKeydown(event) {
    if (isTextMatched && (event.key === ' ' || event.key === 'Enter')) {
        event.preventDefault(); // Prevent default behavior (e.g., form submission)
        this.disabled = true;
        stopTimer();
        
        setTimeout(() => {
            giveStc();
            dispSpd();
            this.disabled = false;
            this.value = '';
            this.focus();
            isTextMatched = false; // Reset the text match status
        }, 200);
    }
}


// exit button action
function backToMain() {
    window.location.href = "mainpage.html";
}

// load practice sentences from txt file
async function loadSampleText() {
    try {
        const response = await fetch('src/proverbs.txt');
        const text = await response.text();
        sampleStc = text.split('\n').map(line => line.trim()).filter(line => line.length > 0);
        giveStc();
    } catch (error) {
        console.error('Error loading the text file', error);
    }
}


// set timer
function startTimer() {
    timer = setInterval(() => {
        second += 0.1;
    }, 100);
}

function stopTimer() {
    clearInterval(timer);
}

function compareText() {
    const givenText = document.getElementById('sampleText').innerText;
    const typedText = document.getElementById('userInput').value;
    let typo = 0;
    let typoBuilder = '';

    if (typedText.length === 0) {
        document.getElementById('typo').innerText = '\u00a0';
        return;
    }

    for (let i = 0; i < typedText.length; i++) {
        if (i < givenText.length) {
            if (typedText[i] !== givenText[i]) {
                typoBuilder += '▼';
                typo ++;
            } else {
                typoBuilder += '\u00A0';
            }
        }
    }

    percentPcs = Math.max(0, 100 - (typo / givenText.length) * 100).toFixed(0);
    document.getElementById('precisie').innerText = `${percentPcs} %`;
    document.getElementById('typo').innerText = typoBuilder;
}

// calculate the current speed
function speedCalc() {
    const typedText = document.getElementById('userInput').value;
    crtWPM = ((typedText.length / 5) / (second / 60)).toFixed(0);
    if (crtWPM > 500) {
        crtWPM === 500;
    } 
    document.getElementById('currSpd').innerText = `${crtWPM} wpm`;
}

// display the typing result
function dispSpd() {
    if (crtWPM > fastestWPM) {
        fastestWPM = crtWPM;
    }

    document.getElementById('prevSpd').innerText = `${crtWPM} wpm`;
    document.getElementById('fastestSpd').innerText = `${fastestWPM} wpm`;
    document.getElementById('currSpd').innerText = '0 wpm';
}

// set practice sentence randomly
function giveStc() {
    if (sampleStc.length === 0) return;

    const randomIndex = Math.floor(Math.random() * sampleStc.length);
    document.getElementById('sampleText').innerText = sampleStc[randomIndex];
}

