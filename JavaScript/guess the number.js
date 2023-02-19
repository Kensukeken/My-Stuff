<!DOCTYPE html>
<html>
<head>
  <title>Guess the Number Game</title>
</head>
<body>
  <h1>Guess the Number Game</h1>
  <p>Guess a number between 1 and 100:</p>
  <input type="text" id="guess" />
  <button onclick="checkGuess()">Guess</button>
  <p id="result"></p>
  <script>
    // generate a random number between 1 and 100
    const randomNumber = Math.floor(Math.random() * 100) + 1;

    function checkGuess() {
      const guess = parseInt(document.getElementById("guess").value);

      if (isNaN(guess) || guess < 1 || guess > 100) {
        document.getElementById("result").textContent = "Invalid guess. Please enter a number between 1 and 100.";
        return;
      }

      if (guess === randomNumber) {
        document.getElementById("result").textContent = "Congratulations! You guessed the correct number.";
      } else if (guess < randomNumber) {
        document.getElementById("result").textContent = "Your guess is too low. Try again.";
      } else {
        document.getElementById("result").textContent = "Your guess is too high. Try again.";
      }
    }
  </script>
</body>
</html>
