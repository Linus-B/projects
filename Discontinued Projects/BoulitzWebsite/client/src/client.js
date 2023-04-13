
const sock = io();
var logged_in = false;

const writeEvent = (text) => {

    const parent = document.querySelector('#events');

    const el = document.createElement('li');
    el.innerHTML = text;

    parent.appendChild(el);
};

const onFormSubmitted = (e) => {
    e.preventDefault();

    const username_input = document.querySelector('#username');
    const password_input = document.querySelector('#password');
    const info = [username_input.value, password_input.value];

    sock.emit('info', ['verification', info]);
    console.log("information emitted");
    sock.emit('info', ['message', "This is working"]);

    username_input.value = '';
    password_input.value = '';
};

sock.on('info', (info) => {
    switch (info[0]){
        case 'message':
            writeEvent(info[1]);
            break;
        
        case 'verification_from_server':
            logged_in = info[1];
            if (logged_in){
                writeEvent("successfully logged in");
            } else {
                writeEvent("log in not successful")
            }
            break;
        default:
            console.log("client cannot find that info type: ", info[0]);
    }
});

writeEvent('Welcome to the Sheepshead website!');

document.querySelector('#submit-Area').addEventListener('submit', onFormSubmitted);