// setting up firebase with our website
const firebaseApp = firebase.initializeApp({
	
  apiKey: "AIzaSyBynnfggQFZC6XeT3o0mQaIHuN3bs8SqCw",
  authDomain: "thato-3f06b.firebaseapp.com",
  projectId: "thato-3f06b",
  storageBucket: "thato-3f06b.appspot.com",
  messagingSenderId: "624472717592",
  appId: "1:624472717592:web:f08b75af0bed45ca11c66d",
  
});
const db = firebaseApp.firestore();
const auth = firebaseApp.auth();
// Sign up function
const signUp = () => {
    // Get email and password from the form
    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;

    // Firebase code to create a new user
    firebase.auth().createUserWithEmailAndPassword(email, password)
        .then((result) => {
            // User signed up successfully
            // Update the DOM or redirect the user to another page
            displaySuccessMessage();
            // You can also redirect the user to another page like this:
            window.location.href = "signIn.html";
        })
        .catch((error) => {
            // Handle errors
            console.log(error.code);
            console.log(error.message);
            // Additional error handling if needed
        });
};

const displaySuccessMessage = () => {
    // Update the DOM with a success message
    const successMessage = document.getElementById("successMessage");
    successMessage.innerHTML = "You are Signed Up!";
};


// Sign In function
const signIn = () => {
    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;

    firebase.auth().signInWithEmailAndPassword(email, password)
        .then((result) => {
            // Successful sign-in
            const user = result.user;
            console.log('User signed in:', user);

           
            window.location.href = 'Home.html';
        })
        .catch((error) => {
            // Handle errors
            const errorCode = error.code;
            const errorMessage = error.message;
            console.error('Sign-in error:', errorCode, errorMessage);

            // Log error details to the console
            console.log('Error Code:', errorCode);
            console.log('Error Message:', errorMessage);

            // Optionally, display an error message to the user on the current page
            // For example, update a <div> with an error message
            document.getElementById('error-message').innerText = errorMessage;
        });
}
