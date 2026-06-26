const API = "http://localhost:8081/api/users";

document
.getElementById("loginForm")
.addEventListener("submit", login);

async function login(e){

    e.preventDefault();

    const email =
    document.getElementById("email").value;

    const password =
    document.getElementById("password").value;

    const response = await fetch(API+"/login",{

        method:"POST",

        headers:{
            "Content-Type":"application/json"
        },

        body:JSON.stringify({

            email,
            password
        })

    });

    if(response.ok){

        const user = await response.json();

        localStorage.setItem("userId",user.id);
        localStorage.setItem("name",user.name);
        localStorage.setItem("email",user.email);
        localStorage.setItem("role",user.role);

        alert("Login Successful");

        if(user.role==="ADMIN"){

            window.location.href="admin.html";
        }

        else{

            window.location.href="dashboard.html";
        }

    }

    else{

        alert("Invalid Email or Password");
    }

}