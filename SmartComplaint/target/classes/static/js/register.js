const API = "http://localhost:8081/api/users";

document
    .getElementById("registerForm")
    .addEventListener("submit", registerUser);

async function registerUser(event) {

    event.preventDefault();

    const user = {

        name: document.getElementById("name").value,

        email: document.getElementById("email").value,

        password: document.getElementById("password").value,

        role: document.getElementById("role").value

    };

    try {

        const response = await fetch(API + "/register", {

            method: "POST",

            headers: {
                "Content-Type": "application/json"
            },

            body: JSON.stringify(user)

        });

        if (response.ok) {

            alert("Registration Successful");

            document.getElementById("registerForm").reset();

            window.location.href = "index.html";

        }

        else {

            const error = await response.json();

            if (error.message) {

                alert(error.message);

            } else {

                alert("Registration Failed");

            }

        }

    }

    catch (err) {

        console.error(err);

        alert("Server Error");

    }

}