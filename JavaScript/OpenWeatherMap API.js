<!DOCTYPE html>
<html>
<head>
  <title>Current Weather</title>
</head>
<body>
  <h1>Current Weather</h1>
  <form onsubmit="getWeather(); return false;">
    <label for="location">Location:</label>
    <input type="text" id="location" />
    <button>Get Weather</button>
  </form>
  <div id="weather"></div>
  <script>
    async function getWeather() {
      const location = document.getElementById("location").value;
      const apiKey = "your_api_key_here"; // replace with your own API key from OpenWeatherMap
      const apiUrl = `https://api.openweathermap.org/data/2.5/weather?q=${location}&units=metric&appid=${apiKey}`;

      const response = await fetch(apiUrl);
      const data = await response.json();

      const weatherDiv = document.getElementById("weather");
      weatherDiv.innerHTML = `
        <h2>${data.name}, ${data.sys.country}</h2>
        <p>${data.weather[0].description}</p>
        <p>Temperature: ${data.main.temp} &#8451;</p>
        <p>Feels like: ${data.main.feels_like} &#8451;</p>
        <p>Humidity: ${data.main.humidity}%</p>
      `;
    }
  </script>
</body>
</html>
