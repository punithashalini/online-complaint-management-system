const API = "http://localhost:8081/api/complaints";

window.onload = function () {

    const name = localStorage.getItem("name");

    if (name) {
        document.getElementById("welcomeUser").innerHTML =
            "Welcome, " + name;
    }

    loadComplaints();

    document.getElementById("search")
        .addEventListener("keyup", loadComplaints);

    document.getElementById("statusFilter")
        .addEventListener("change", loadComplaints);
};

async function loadComplaints() {

    try {

        const response = await fetch(API);

        const complaints = await response.json();

        const userId = parseInt(localStorage.getItem("userId"));

        const search = document.getElementById("search").value.toLowerCase();

        const status = document.getElementById("statusFilter").value;

        let rows = "";

        complaints
            .filter(c => c.user && c.user.id === userId)
            .filter(c => c.title.toLowerCase().includes(search))
            .filter(c => status === "ALL" || c.status === status)
            .forEach(c => {

                rows += `
                    <tr>
                        <td>${c.id}</td>
                        <td>${c.title}</td>
                        <td>${c.description}</td>
                        <td>${c.priority}</td>
                        <td>
                            <span class="${getStatusClass(c.status)}">
                                ${c.status}
                            </span>
                        </td>
                    </tr>
                `;
            });

        document.getElementById("complaintTable").innerHTML = rows;

    } catch (e) {

        console.log(e);

        alert("Unable to load complaints");

    }

}

function getStatusClass(status){

    if(status==="PENDING")
        return "pending";

    if(status==="IN_PROGRESS")
        return "progress";

    if(status==="RESOLVED")
        return "resolved";

    return "";

}

function logout(){

    localStorage.clear();

    window.location.href="index.html";

}