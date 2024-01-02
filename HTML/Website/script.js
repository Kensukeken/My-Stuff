// Add this code to a script.js file

function addSpacesBetweenLogos() {
    const experienceLogos = document.getElementById('experienceLogos');

    if (experienceLogos) {
        const logoItems = experienceLogos.getElementsByClassName('logo-item');

        // Loop through logo items and add margin to the right
        for (let i = 0; i < logoItems.length; i++) {
            logoItems[i].style.marginRight = '200px'; // Adjust the margin as needed

            // Add space between logo images
            const imgElement = logoItems[i].getElementsByTagName('img')[0];
            imgElement.style.marginBottom = '20px'; // Adjust the space as needed
        }
    }
}

// Call the function when the page loads
document.addEventListener('DOMContentLoaded', addSpacesBetweenLogos);
