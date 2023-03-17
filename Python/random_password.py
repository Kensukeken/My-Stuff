import random

# define character set of password
charset = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*()_+-=[]"

# randomly generate a password of length 12
password = ""
for i in range(12):
    index = random.randint(0, len(charset)-1)
    password += charset[index]

# output the generated password
print("Randomly generated password: " + password)
