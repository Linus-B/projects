import turtle

def triangle(length):
    for i in range(3):
        turtle.fd(length)
        turtle.lt(120)

def Recursive_Serpinski(x, y, length, layer):
    if (layer > 0):
        turtle.up()
        turtle.setx(x)
        turtle.sety(y)
        turtle.down()
        triangle(length)
        Recursive_Serpinski(x, y, length / 2, layer - 1)
        Recursive_Serpinski(x + length / 4, y + (length / 4 * (3 ** 0.5)), length / 2, layer - 1)
        Recursive_Serpinski(x + length / 2, y, length / 2, layer - 1)

def Serpinski_Triangle():
    turtle.setworldcoordinates(-5, -5, 595, 595)
    turtle.hideturtle()
    turtle.speed(0)
    Recursive_Serpinski(0, 0, 521, 8)

Serpinski_Triangle()
