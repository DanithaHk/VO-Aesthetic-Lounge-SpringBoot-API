import jwtDecode from "jwt-decode";
const token = localStorage.getItem("token");
const decoded = jwtDecode(token);
const email = decoded.email || decoded.sub;
var swiper = new Swiper(".mySwiper", {
    slidesPerView: 1,
    spaceBetween: 20,
    loop: true,
    pagination: {
        el: ".swiper-pagination",
        clickable: true,
    },
    navigation: {
        nextEl: ".swiper-button-next",
        prevEl: ".swiper-button-prev",
    },
    breakpoints: {
        768: {
            slidesPerView: 2
        },
        1024: {
            slidesPerView: 3
        }
    }
});
function toggleSidebar() {
    const sidebar = document.getElementById("profileSidebar");
    if (sidebar) {
        sidebar.classList.toggle("show"); // Toggle visibility class
    } else {
        console.error("Sidebar element not found!");
    }
}

// Optional: Close sidebar when clicking outside
document.addEventListener("click", function (event) {
    const sidebar = document.getElementById("profileSidebar");
    const profileBtn = document.getElementById("profileBtn");

    // If sidebar is open and the click is outside the sidebar and not on the profile button
    if (
        sidebar && sidebar.classList.contains("show") &&
        !sidebar.contains(event.target) &&
        event.target !== profileBtn
    ) {
        sidebar.classList.remove("show");
    }
});


function getBookings(email) {

    $.ajax({
        url: "http://localhost:8080/api/v1/booking/get/"+email, // Your API endpoint for appointments
        method: "GET",
        headers: {
            "Authorization": `Bearer ${localStorage.getItem("token")}` // Pass token in header
        },
        success: (res) => {
            console.log(res);  // Log the response for debugging
            let appointments = res.data;  // Assuming the data comes in `data` field
            let table = $('#appointmentTableBody');  // Assuming you have a table with id 'appointmentsTable'
            table.empty();  // Clear the table before appending new data

            appointments.forEach((appointment) => {
                let appointmentTime = appointment.appointmentTime || "N/A";  // Default to "N/A" if time is missing
                let appointmentDate = appointment.appointmentDate || "N/A";  // Default to "N/A" if date is missing

                table.append(`
                <tr>
                    <td>${appointment.serviceName}</td>
                     <td>${appointment.appointmentDate}</td>
                     <td>${appointment.appointmentTime}</td>
                </tr>
            `);
            });
        },
        error: (error) => {
            console.error("Error fetching appointments:", error);
        }
    });

}
function getUsers(email) {
    $.ajax({
        url: "http://localhost:8080/api/v1/user/get/"+email,
        type: "GET",
        contentType: "application/json",
        headers: {
            "Authorization": "Bearer " + localStorage.getItem("token")
        },
        success: function (response) {
            // Set the dynamic data
            $("#profileName").text(response.name);
            $("#profileEmail").text(response.email);
        },
        error: function () {
            console.error("Failed to load profile data.");
        }
    });
}