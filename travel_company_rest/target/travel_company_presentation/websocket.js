var wsUri = "ws://" + document.location.host + "/travel_company/chat";
var websocket = new WebSocket(wsUri);

var username;
websocket.onopen = function(evt) { onOpen(evt) };
websocket.onmessage = function(evt) { onMessage(evt) };
websocket.onerror = function(evt) { onError(evt) };
var output = document.getElementById("output");

function join() {
    username = nick.value;
    websocket.send(username + " joined");
}

function send_message() {
    websocket.send(username + ": " + message.value);
}

function onOpen() {
    writeToScreen("Connected to " + wsUri);
}

function onMessage(evt) {
    chatlogField.innerHTML += evt.data + "\n";
}

function onError(evt) {
    writeToScreen('<span style="color: red;">ERROR:</span> ' + evt.data);
}

function writeToScreen(message) {
    output.innerHTML += message + "<br>";
}