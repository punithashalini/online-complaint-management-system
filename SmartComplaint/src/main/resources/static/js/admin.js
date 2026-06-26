const USER_API = "http://localhost:8081/api/users";
const COMPLAINT_API = "http://localhost:8081/api/complaints";

window.onload = function () {

    const name = localStorage.getItem("name");

    if (name) {
        document.getElementById("adminName").innerText =
            "Welcome, " + name;
    }

    loadUsers();
    loadComplaints();
};

// ================= USERS =================

async function loadUsers() {

    try {

        const response = await fetch(USER_API);

        const users = await response.json();

        let rows = "";

        let totalUsers = 0;

        users.forEach(user => {

            // Skip ADMIN user
            if (user.role === "ADMIN") {
                return;
            }

            totalUsers++;

            rows += `
                <tr>
                    <td>${user.id}</td>
                    <td>${user.name}</td>
                    <td>${user.email}</td>
                    <td>${user.role}</td>
                </tr>
            `;

        });

        document.getElementById("totalUsers").innerText = totalUsers;

        document.getElementById("userTable").innerHTML = rows;

    }

    catch (error) {

        console.log(error);

    }

}

// ================= COMPLAINTS =================

async function loadComplaints() {

    try {

        const response = await fetch(COMPLAINT_API);

        const complaints = await response.json();

        document.getElementById("totalComplaints").innerText =
            complaints.length;

        let pending = 0;
        let progress = 0;
        let resolved = 0;

        let rows = "";

        complaints.forEach(c => {

            if (c.status === "PENDING")
                pending++;

            if (c.status === "IN_PROGRESS")
                progress++;

            if (c.status === "RESOLVED")
                resolved++;

            rows += `
                <tr>

                    <td>${c.id}</td>

                    <td>${c.user.name}</td>

                    <td>${c.title}</td>

                    <td>${c.priority}</td>

                    <td>
                        <span class="${getClass(c.status)}">
                            ${c.status}
                        </span>
                    </td>

                    <td>

                        <select onchange="updateStatus(${c.id}, this.value)">

                            <option value="PENDING" ${c.status === "PENDING" ? "selected" : ""}>
                                PENDING
                            </option>

                            <option value="IN_PROGRESS" ${c.status === "IN_PROGRESS" ? "selected" : ""}>
                                IN PROGRESS
                            </option>

                            <option value="RESOLVED" ${c.status === "RESOLVED" ? "selected" : ""}>
                                RESOLVED
                            </option>

                        </select>

                    </td>

                </tr>
            `;

        });

        document.getElementById("pendingCount").innerText = pending;

        document.getElementById("resolvedCount").innerText = resolved;

        document.getElementById("complaintTable").innerHTML = rows;

    }

    catch (error) {

        console.log(error);

    }

}

// ================= UPDATE STATUS =================

async function updateStatus(id, status) {

    try {

        const response = await fetch(

            COMPLAINT_API + "/" + id + "/status?status=" + status,

            {
                method: "PATCH"
            }

        );

        if (response.ok) {

            alert("Complaint Status Updated Successfully");

            loadComplaints();

        } else {

            alert("Failed to Update Status");

        }

    }

    catch (error) {

        console.log(error);

    }

}

// ================= STATUS COLOR =================

function getClass(status) {

    if (status === "PENDING")
        return "pending";

    if (status === "IN_PROGRESS")
        return "progress";

    if (status === "RESOLVED")
        return "resolved";

    return "";

}

// ================= LOGOUT =================

function logout() {

    localStorage.clear();

    window.location.href = "index.html";

}