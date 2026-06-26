const API = "http://localhost:8081/api/complaints";

// Load user name
window.onload = function () {

    const name = localStorage.getItem("name");

    if (name) {

        document.getElementById("welcomeUser").innerHTML =
            "Welcome, " + name;

    }

};

// Submit Complaint

document
    .getElementById("complaintForm")
    .addEventListener("submit", createComplaint);

async function createComplaint(e) {

    e.preventDefault();

    const complaint = {

        title: document.getElementById("title").value,

        description: document.getElementById("description").value,

        priority: document.getElementById("priority").value,

        userId: parseInt(localStorage.getItem("userId"))

    };

    try {

        const response = await fetch(API, {

            method: "POST",

            headers: {

                "Content-Type": "application/json"

            },

            body: JSON.stringify(complaint)

        });

        if (response.ok) {

            alert("Complaint Submitted Successfully");

            document.getElementById("complaintForm").reset();

            window.location.href = "dashboard.html";

        }

        else {

            alert("Failed to Submit Complaint");

        }

    }

    catch (error) {

        console.error(error);

        alert("Server Error");

    }

}

// Logout

function logout() {

    localStorage.clear();

    window.location.href = "index.html";

}