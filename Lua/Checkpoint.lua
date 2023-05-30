local checkpoints = workspace:WaitForChild("Checkpoints")

local Players = game:GetService("Players")
local RunService = game:GetService("RunService")

local DatastoreService = game:GetService("DataStoreService")
local Data = DatastoreService:GetDataStore("1")
local sessionData = {}

function PlayerAdded(player)
 local leaderstats = Instance.new("Folder")
 leaderstats.Name = "leaderstats"

 local stage = Instance.new("NumberValue")
 stage.Name = "Stage"
 stage.Parent = leaderstats

 local success = nil
 local playerData = nil
 local attempt = 1

 repeat 
  success, playerData = pcall(function() -- here pcall or protected call is just repeat waiting until the data loads for the player
   return Data:GetAsync(player.UserId)
  end)

  attempt += 1
  if not success then 
   warn(playerData)
   task.wait(2)
  end

 until success or attempt == 5 -- it repeats it until it loads

 if success then --if it loads then make the table with their data inside
  print("Data loaded: "..player.Name)
  if not playerData then -- if they have no table then their a new player so we create the table 
   print("new player, giving default data")

   playerData = {
    ["Stage"] = 1, --add all your values and stuff inside of the data

   }
  end

  sessionData[player.UserId] = playerData --set the data to a table with the players id and make to make a variable
 else
  warn("couldnt load data: "..player.Name)
  player:Kick("couldnt load your data, rejoin") --if the data couldnt load we kick them so their not just sitting there forever waiting
 end

 stage.Value = sessionData[player.UserId].Stage --here we get the numbervalue created above and get the value of it and set it to the value inside of the table

 stage:GetPropertyChangedSignal("Value"):Connect(function()
  sessionData[player.UserId].Stage = stage.Value --update the table value whenever the leaderstat value changes
 end)


 leaderstats.Parent = player

end

Players.PlayerAdded:Connect(function(player)
 PlayerAdded(player)

 player.CharacterAdded:Connect(function(char)
  local leaderstats = player:WaitForChild("leaderstats")
  local stage = leaderstats.Stage

  local hum = char:WaitForChild("Humanoid")
  task.wait()
  char:MoveTo(checkpoints[stage.Value].Position)

  hum.Touched:Connect(function(hit)
   if hit.Parent == checkpoints then
    if tonumber(hit.Name) == stage.Value + 1 then
     stage.Value += 1
    end
   end
  end)
 end)
end)



function PlayerLeaving(player)

 if sessionData[player.UserId] then
  local success = nil
  local errorMsg = nil
  local attempt = 1


  repeat
   success, errorMsg = pcall(function()
    Data:SetAsync(player.UserId, sessionData[player.UserId]) --here is the same as loading data just repeat waits until the data saves
   end)

   attempt += 1
   if not success then 
    warn(errorMsg)
    task.wait(2)
   end

  until success or attempt == 5

  if success then 
   print("Data saved: "..player.Name)
  else
   warn("Cant save: "..player.Name)
  end

 end

end

Players.PlayerRemoving:Connect(PlayerLeaving)

function ServerShutdown()
 if RunService:IsStudio() then
  return
 end

 for i, player in ipairs(Players:GetPlayers()) do
  task.spawn(function()
   PlayerLeaving(player)
  end)
 end
end
game:BindToClose(ServerShutdown)
