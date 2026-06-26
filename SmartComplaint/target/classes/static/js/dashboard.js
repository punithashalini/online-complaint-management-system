const COMPLAINT_API = "http://localhost:8081/api/complaints";

// Load dashboard
window.onload = function () {

    const name = localStorage.getItem("name");

    if (name) {
        document.getElementById("welcomeUser").innerHTML =
            "Welcome, " + name;
    }

    loadMyComplaints();
};

// ===================== LOAD USER COMPLAINTS =====================

async function loadMyComplaints() {

    try {

        const response = await fetch(COMPLAINT_API);

        const complaints = await response.json();

        const userId = parseInt(localStorage.getItem("userId"));

        const myComplaints = complaints.filter(c => c.user.id === userId);

        document.getElementById("totalComplaints").innerText =
            myComplaints.length;

        let pending = 0;
        let progress = 0;
        let resolved = 0;

        let rows = "";

        myComplaints.forEach(c => {

            if (c.status === "PENDING")
                pending++;

            if (c.status === "IN_PROGRESS")
                progress++;

            if (c.status === "RESOLVED")
                resolved++;

            rows += `
                <tr>

                    <td>${c.id}</td>

                    <td>${c.title}</td>

                    <td>${c.priority}</td>

                    <td>

                        <span class="${statusClass(c.status)}">

                            ${c.status}

                        </span>

                    </td>

                </tr>
            `;

        });

        document.getElementById("pendingCount").innerText = pending;

        document.getElementById("progressCount").innerText = progress;

        document.getElementById("resolvedCount").innerText = resolved;

        document.getElementById("complaintTable").innerHTML = rows;

    }

    catch (error) {

        console.error(error);

        alert("Unable to load complaints.");

    }

}

// ===================== STATUS COLOR =====================

function statusClass(status) {

    if (status === "PENDING")
        return "pending";

    if (status === "IN_PROGRESS")
        return "progress";

    if (status === "RESOLVED")
        return "resolved";

    return "";

}

// ===================== LOGOUT =====================

function logout() {

    localStorage.clear();

    window.location.href = "index.html";

}