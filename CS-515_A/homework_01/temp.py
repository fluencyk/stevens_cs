# -*- coding: utf-8 -*-
"""
School: Stevens Institute of Technology
Course: CS-515_A
Instructor: Prof. Rocco Polimeni
----- ----- ----- ----- -----
Homework 01 - A, temp.py
Requirement: Temperature Conversion
----- ----- ----- ----- -----
@author: Yujun Kong
"""
from typing import Optional
import sys


def get_user_input() -> Optional[float]:
    """ validate the user input to return a float for converting to celsius """

    user_input: str = input("Enter temperature in Fahrenheit: ")

    if " " in user_input or user_input == "-0":
        return None

    if len(user_input) >= 2 and user_input[0] == "0" and user_input[1] != ".":
        return None

    if user_input[len(user_input) - 1] == ".":
        return None

    if user_input.replace(".", "").replace("-", "").isdecimal() is False:
        return None

    count: int = 0
    for i in user_input[1:len((user_input))]:
        if i == ".":
            count += 1
    if count > 1 or "-" in user_input[1:len((user_input))]:
        return None

    if user_input == "0":
        # print("It's totally valid!") # archived manual testing
        return float(user_input)
    elif user_input.replace(".", "").replace("-", "").isdecimal() is True:
        # print("It's totally valid!") # archived manual testing
        return float(user_input)


def cnvt_n_pnt_to_celsius() -> None:
    """ convert fahrenheit to celsius and print """

    invalid_msg: str = "Your entered is invalid, please try again."

    temp_faht = get_user_input()

    if temp_faht is None:
        print(invalid_msg)
        cnvt_n_pnt_to_celsius()

    else:
        temp_cels: float = (temp_faht - 32) * (5 / 9)
        print(f"\nTemperature in Celsius: {temp_cels: .4f}\n")


def main():
    """ main program entrance """

    cnvt_n_pnt_to_celsius()
    sys.exit()


if __name__ == "__main__":
    main()

# end of program
