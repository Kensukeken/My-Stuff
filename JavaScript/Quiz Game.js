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
        choices:
