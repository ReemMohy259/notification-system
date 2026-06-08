const PRODUCER_URL = "http://localhost:8080/api/register";
const CONSUMER_URL = "http://localhost:8081/notifications/latest";

async function registerUser() {

    const user = {

        firstName: document.getElementById("firstName").value,

        lastName: document.getElementById("lastName").value,

        email: document.getElementById("email").value,

        phoneNumber: document.getElementById("phoneNumber").value
    };

    try {

        const response = await fetch(PRODUCER_URL, {

            method: "POST",

            headers: {

                "Content-Type": "application/json"
            },

            body: JSON.stringify(user)

        });

        const result = await response.json();

        if (response.ok) {

            document.getElementById("registerMessage").innerHTML =
                "✅ User registered successfully.";

        } else {

            document.getElementById("registerMessage").innerHTML =
                result.message;
        }

    } catch (error) {

        document.getElementById("registerMessage").innerHTML =
            "Unable to connect to Producer Service.";
    }

}

async function receiveNotification() {

    try {

        const response = await fetch(CONSUMER_URL);

        const notification = await response.json();

        document.getElementById("name").innerHTML =
            notification.firstName + " " + notification.lastName;

        document.getElementById("notificationEmail").innerHTML =
            notification.email;

        document.getElementById("phone").innerHTML =
            notification.phoneNumber;

        document.getElementById("registeredAt").innerHTML =
            notification.registeredAt;

    } catch (error) {

        alert("No notifications available.");
    }

}