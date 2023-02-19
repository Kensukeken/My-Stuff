<!DOCTYPE html>
<html>
<head>
  <title>Quiz Game</title>
</head>
<body>
  <h1>Quiz Game</h1>
  <div id="question"></div>
  <ul id="choices"></ul>
  <button id="submit">Submit Answer</button>
  <div id="result"></div>
  <script>
    const questions = [
      {
        question: "What is the capital of France?",
        choices: ["Paris", "Berlin", "Rome", "Madrid"],
        answer: "Paris"
      },
      {
        question: "What is the largest country in the world?",
        choices: ["China", "United States", "Russia", "Brazil"],
        answer: "Russia"
      },
      {
        question: "What is the currency of Japan?",
        choices: ["Yuan", "Won", "Yen", "Dollar"],
        answer: "Yen"
      }
    ];

    const questionDiv = document.getElementById("question");
    const choicesList = document.getElementById("choices");
    const submitButton = document.getElementById("submit");
    const resultDiv = document.getElementById("result");

    let currentQuestion = 0;
    let score = 0;

    function displayQuestion() {
      const question = questions[currentQuestion];
      questionDiv.textContent = question.question;

      choicesList.innerHTML = "";
      question.choices.forEach(choice => {
        const li = document.createElement("li");
        const radio = document.createElement("input");
        radio.type = "radio";
        radio.name = "choice";
        radio.value = choice;
        li.appendChild(radio);
        li.appendChild(document.createTextNode(choice));
        choicesList.appendChild(li);
      });
    }

    function checkAnswer() {
      const selectedChoice = document.querySelector('input[name="choice"]:checked');
      if (!selectedChoice) return;

      const answer = selectedChoice.value;
      if (answer === questions[currentQuestion].answer) {
        resultDiv.textContent = "Correct!";
        score++;
      } else {
        resultDiv.textContent = "Incorrect.";
      }

      currentQuestion++;
      if (currentQuestion >= questions.length) {
        endGame();
      } else {
        displayQuestion();
      }
    }

    function endGame() {
      questionDiv.textContent = `You scored ${score} out of ${questions.length}.`;
      choicesList.innerHTML = "";
      submitButton.style.display = "none";
    }

    displayQuestion();
    submitButton.addEventListener("click", checkAnswer);
  </script>
</body>
</html>
