<!DOCTYPE html>
<html>
<head>
  <title>Password Generator</title>
</head>
<body>
  <h1>Password Generator</h1>
  <form onsubmit="generatePassword(); return false;">
    <label for="length">Length:</label>
    <input type="number" id="length" value="8" min="4" max="32" />
    <br>
    <label for="uppercase">Uppercase letters:</label>
    <input type="checkbox" id="uppercase" checked />
    <br>
    <label for="lowercase">Lowercase letters:</label>
    <input type="checkbox" id="lowercase" checked />
    <br>
    <label for="numbers">Numbers:</label>
    <input type="checkbox" id="numbers" checked />
    <br>
    <label for="symbols">Symbols:</label>
    <input type="checkbox" id="symbols" checked />
    <br>
    <button>Generate Password</button>
  </form>
  <div id="password"></div>
  <script>
    function generatePassword() {
      const length = document.getElementById("length").value;
      const useUppercase = document.getElementById("uppercase").checked;
      const useLowercase = document.getElementById("lowercase").checked;
      const useNumbers = document.getElementById("numbers").checked;
      const useSymbols = document.getElementById("symbols").checked;
      
      const uppercaseChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
      const lowercaseChars = "abcdefghijklmnopqrstuvwxyz";
      const numberChars = "0123456789";
      const symbolChars = "!@#$%^&*()_-+=[]{}\\|;:'\"<>,.?/~`";
      
      let availableChars = "";
      if (useUppercase) availableChars += uppercaseChars;
      if (useLowercase) availableChars += lowercaseChars;
      if (useNumbers) availableChars += numberChars;
      if (useSymbols) availableChars += symbolChars;

      let password = "";
      for (let i = 0; i < length; i++) {
        password += availableChars[Math.floor(Math.random() * availableChars.length)];
      }

      const passwordDiv = document.getElementById("password");
      passwordDiv.innerHTML = `
        <h2>Generated Password:</h2>
        <p>${password}</p>
      `;
    }
  </script>
</body>
</html>
