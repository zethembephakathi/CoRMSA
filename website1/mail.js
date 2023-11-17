
  const firebaseConfig = {
  apiKey: "AIzaSyBynnfggQFZC6XeT3o0mQaIHuN3bs8SqCw",
  authDomain: "thato-3f06b.firebaseapp.com",
  databaseURL: "https://thato-3f06b-default-rtdb.firebaseio.com",
  projectId: "thato-3f06b",
  storageBucket: "thato-3f06b.appspot.com",
  messagingSenderId: "624472717592",
  appId: "1:624472717592:web:f08b75af0bed45ca11c66d",
  measurementId: "G-S837HTKEH7"
};


// initialize firebase
firebase.initializeApp(firebaseConfig);

// reference your database
var contactFormDB = firebase.database().ref("commentForm");

document.getElementById("commentForm").addEventListener("submit", submitForm);

function submitForm(e) {
  e.preventDefault();

  var name = getElementVal("name");
  var emailid = getElementVal("emailid");
  var msgContent = getElementVal("msgContent");

  if (validateInputs(name, emailid, msgContent)) {
    // If inputs are valid, save the form data
    saveMessages(name, emailid, msgContent);

    // Enable alert
    document.querySelector(".alert").style.display = "block";

    // Remove the alert after 3 seconds
    setTimeout(() => {
      document.querySelector(".alert").style.display = "none";
    }, 3000);

    // Reset the form
    document.getElementById("commentForm").reset();
  }
}

const validateInputs = (name, emailid, msgContent) => {
  // Simple validation, you can enhance this as needed
  if (!name || !emailid || !msgContent) {
    alert("Please fill in all fields.");
    return false;
  }

  // Email validation using a basic pattern
  const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  if (!emailPattern.test(emailid)) {
    alert("Please enter a valid email address.");
    return false;
  }

  return true; // All validations passed
};

const saveMessages = (name, emailid, msgContent) => {
  var newContactForm = contactFormDB.push();

  newContactForm.set({
    name: name,
    emailid: emailid,
    msgContent: msgContent,
  });
};

const getElementVal = (id) => {
  return document.getElementById(id).value;
};