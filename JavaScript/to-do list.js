<!DOCTYPE html>
<html>
<head>
  <title>To-Do List</title>
</head>
<body>
  <h1>To-Do List</h1>
  <form onsubmit="addTask(); return false;">
    <label for="newTask">New task:</label>
    <input type="text" id="newTask" />
    <button>Add</button>
  </form>
  <ul id="taskList"></ul>
  <script>
    const tasks = [];

    function addTask() {
      const newTask = document.getElementById("newTask").value;

      if (newTask === "") {
        alert("Please enter a task.");
        return;
      }

      tasks.push({ text: newTask, completed: false });
      document.getElementById("newTask").value = "";
      renderTasks();
    }

    function renderTasks() {
      const taskList = document.getElementById("taskList");
      taskList.innerHTML = "";

      for (let i = 0; i < tasks.length; i++) {
        const task = tasks[i];

        const taskItem = document.createElement("li");
        taskItem.textContent = task.text;

        const checkbox = document.createElement("input");
        checkbox.type = "checkbox";
        checkbox.checked = task.completed;
        checkbox.addEventListener("change", function() {
          task.completed = this.checked;
          renderTasks();
        });

        taskItem.insertBefore(checkbox, taskItem.firstChild);
        taskList.appendChild(taskItem);
      }
    }
  </script>
</body>
</html>
