/*const register = (user, password, phone, email) => {
    var ws = new WebSocket('ws://192.168.1.155:8080/local-server/CreateAccount');
    
    ws.onopen = () => {
      // connection opened
      ws.send(JSON.stringify({UserName: user, PassWord: password, PhoneNumber: phone, Mail: email})); // send a message
    };
    
    ws.onmessage = (e) => {
      // a message was received
      console.log("messsage: " + e.data);
      if(e.data==200){
          alert('Success');
      }
    };
    
    ws.onerror = (e) => {
      // an error occurred
      console.log("error: " + e.message);
    };
    
    ws.onclose = (e) => {
      // connection closed
      console.log(e.code, e.reason);
    };
};*/

  module.exports = register;
  