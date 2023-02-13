from turtle import *
import turtle as tur

turt=tur.Turtle()
tur.title("PythonGuides")
turt.width(8)
turt.color("cyan")
new=tur.getscreen()
turt.speed(10)


new.bgcolor("black")

turt.left(180)
turt.penup()
turt.forward(300)
turt.right(90)
turt.forward(100)
turt.pendown()

# H

turt.forward(50)
turt.right(90)
turt.forward(50)
turt.left(90)
turt.forward(50)
turt.left(90)

turt.penup()
turt.forward(50)
turt.left(90)
turt.pendown()
turt.forward(50)
turt.left(90)
turt.forward(50)
turt.right(90)
turt.forward(50)


# A

turt.penup()
turt.left(90)
turt.forward(15)
turt.pendown()
turt.left(70)
turt.forward(110)
turt.right(70)
turt.right(70)
turt.forward(110)
turt.left(180)
turt.forward(55)
turt.left(70)
turt.forward(38)
turt.left(70)
turt.penup()
turt.forward(55)
turt.left(110)

turt.forward(100)

# P

turt.left(90)
turt.pendown()
turt.forward(100)
turt.right(90)
turt.forward(50)
turt.right(20)
turt.forward(20)
turt.right(70)
turt.forward(40)
turt.right(70)
turt.forward(20)
turt.right(20)
turt.forward(50)
turt.left(90)
turt.forward(50)
turt.left(90)
turt.penup()
turt.forward(100)


# P

turt.left(90)
turt.pendown()
turt.forward(100)
turt.right(90)
turt.forward(50)
turt.right(20)
turt.forward(20)
turt.right(70)
turt.forward(40)
turt.right(70)
turt.forward(20)
turt.right(20)
turt.forward(50)
turt.left(90)
turt.forward(50)
turt.left(90)
turt.penup()
turt.forward(100)

# Y

turt.forward(20)
turt.pendown()
turt.left(90)
turt.forward(50)
turt.left(30)
turt.forward(60)
turt.backward(60)
turt.right(60)
turt.forward(60)
turt.backward(60)
turt.left(30)

# go to Home

turt.penup()
turt.home()

turt.color("white")
new.bgcolor("black")
# setting second row

turt.backward(300)
turt.right(90)
turt.forward(60)
turt.left(180)


# P


turt.pendown()
turt.forward(100)
turt.right(90)
turt.forward(50)
turt.right(20)
turt.forward(20)
turt.right(70)
turt.forward(40)
turt.right(70)
turt.forward(20)
turt.right(20)
turt.forward(50)
turt.backward(50)
turt.left(180)
turt.right(20)
turt.forward(20)
turt.right(70)
turt.forward(40)
turt.right(70)
turt.forward(20)
turt.right(20)
turt.forward(50)
turt.right(90)
turt.forward(10)


# go to Home

turt.penup()
turt.home()

# setting up

turt.backward(200)
turt.right(90)
turt.forward(10)
turt.left(90)
turt.pendown()
turt.forward(20)
turt.penup()
turt.home()

# D

turt.backward(150)
turt.right(90)
turt.forward(60)
turt.pendown()
turt.backward(100)
turt.right(90)
turt.forward(10)
turt.backward(70)
turt.left(180)
turt.right(20)
turt.forward(20)
turt.right(70)
turt.forward(88)
turt.right(70)
turt.forward(20)
turt.right(20)
turt.forward(70)

turt.penup()
turt.home()


turt.backward(50)
turt.right(90)
turt.forward(65)
turt.left(90)





turt.pendown()
turt.left(70)
turt.forward(110)
turt.right(70)
turt.right(70)
turt.forward(110)
turt.left(180)
turt.forward(55)
turt.left(70)
turt.forward(38)
turt.left(70)
turt.penup()
turt.forward(55)
turt.left(110)

turt.forward(100)



turt.pendown()
turt.left(90)
turt.forward(50)
turt.left(30)
turt.forward(60)
turt.backward(60)
turt.right(60)
turt.forward(60)
turt.backward(60)
turt.left(30)


turt.penup()
turt.home()



turt.right(90)
turt.forward(215)
turt.right(90)
turt.forward(200)
turt.right(90)

#color

turt.color("cyan")
new.bgcolor("black")





turt.penup()
turt.left(90)
turt.forward(80)
turt.left(90)
turt.forward(7)


turt.forward(100)
turt.home()
turt.forward(200)
turt.pendown()
turt.color("CYAN")
turt.width(3)
turt.speed(0)



def squre(length, angle):

    turt.forward(length)
    turt.right(angle)
    turt.forward(length)
    turt.right(angle)

    turt.forward(length)
    turt.right(angle)
    turt.forward(length)
    turt.right(angle)

squre(80, 90)

for i in range(36):
      turt.right(10)
      squre(80, 90)


tur.mainloop()