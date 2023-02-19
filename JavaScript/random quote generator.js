<!DOCTYPE html>
<html>
<head>
  <title>Random Quote Generator</title>
</head>
<body>
  <h1>Random Quote Generator</h1>
  <button onclick="generateQuote()">Generate Quote</button>
  <p id="quote"></p>
  <script>
    const quotes = [
      "The best way to predict the future is to create it. - Peter Drucker",
      "It does not matter how slowly you go as long as you do not stop. - Confucius",
      "The only way to do great work is to love what you do. - Steve Jobs",
      "Success is not final, failure is not fatal: It is the courage to continue that counts. - Winston S. Churchill",
      "Believe you can and you're halfway there. - Theodore Roosevelt",
      "If you want to live a happy life, tie it to a goal, not to people or things. - Albert Einstein",
      "You miss 100% of the shots you don't take. - Wayne Gretzky",
      "A person who never made a mistake never tried anything new. - Albert Einstein",
      "If you can't explain it simply, you don't understand it well enough. - Albert Einstein"
    ];

    function generateQuote() {
      const randomIndex = Math.floor(Math.random() * quotes.length);
      const randomQuote = quotes[randomIndex];
      document.getElementById("quote").textContent = randomQuote;
    }
  </script>
</body>
</html>
