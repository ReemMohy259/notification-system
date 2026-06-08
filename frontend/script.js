const PRODUCER_URL = "http://localhost:8080/api/register";
const CONSUMER_URL = "http://localhost:8081/notifications/latest";
const CONSUMER_ALL_URL = "http://localhost:8081/notifications";

async function registerUser() {

    const user = {
        firstName:   document.getElementById("firstName").value,
        lastName:    document.getElementById("lastName").value,
        email:       document.getElementById("email").value,
        phoneNumber: document.getElementById("phoneNumber").value
    };

    const msgEl = document.getElementById("registerMessage");
    msgEl.className = "feedback";
    msgEl.textContent = "";

    try {

        const response = await fetch(PRODUCER_URL, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(user)
        });

        const result = await response.json();

        if (response.ok) {
            msgEl.className = "feedback success";
            msgEl.textContent = "✓ User registered — event published to queue.";
        } else {
            msgEl.className = "feedback error";
            msgEl.textContent = result.message || "Registration failed.";
        }

    } catch (error) {
        msgEl.className = "feedback error";
        msgEl.textContent = "Unable to connect to Producer Service.";
    }
}

async function receiveNotification() {

    try {

        const response     = await fetch(CONSUMER_URL);
        const notification = await response.json();

        document.getElementById("name").textContent =
            notification.firstName + " " + notification.lastName;

        document.getElementById("notificationEmail").textContent =
            notification.email;

        document.getElementById("phone").textContent =
            notification.phoneNumber;

        document.getElementById("registeredAt").textContent =
            notification.registeredAt;

        const card = document.getElementById("notification");
        card.hidden = false;

    } catch (error) {
        alert("No notifications available.");
    }
}

async function receiveAllNotifications() {

    try {

        const response      = await fetch(CONSUMER_ALL_URL);
        const notifications = await response.json();

        const wrap    = document.getElementById("allNotificationsWrap");
        const list    = document.getElementById("allNotifications");
        const countEl = document.getElementById("notifCount");

        list.innerHTML = "";
        countEl.textContent = notifications.length;
        wrap.hidden = false;

        if (notifications.length === 0) {
            list.innerHTML = '<li class="notif-list-empty">No notifications yet.</li>';
            return;
        }

        notifications.forEach((n, i) => {
            const li = document.createElement("li");
            li.className = "notif-list-item";
            li.style.animationDelay = (i * 0.04) + "s";
            li.innerHTML = `
                <span class="notif-item-name">${n.firstName} ${n.lastName}</span>
                <span class="notif-item-email">${n.email}</span>
                <span class="notif-item-time">${n.registeredAt || ""}</span>
            `;
            list.appendChild(li);
        });

    } catch (error) {
        alert("Unable to fetch notifications.");
    }
}