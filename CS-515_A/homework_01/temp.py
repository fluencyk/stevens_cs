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
import re
import sys
from typing import Optional


def get_user_input() -> Optional[float]:
    """ validates user input and returns a float or None. """

    while True:
        user_input: str = input("Enter temperature in Fahrenheit: ")

        if not user_input:  # check for empty input
            return None

        if user_input == "-0":  # allow -0.
            user_input = "0"

        if user_input == "0":  # handle 0
            return 0.0

        if user_input == "0." or user_input == "-0.":  # handle 0. and -0.
            return None

        if len(user_input) >= 2:  # handle 0 leading circumstances
            if user_input[0] == "0" and user_input[1] == "0":
                return None
            elif user_input[0:3] == "-00":
                return None
            elif user_input[0] == "0" and user_input[1] != ".":
                return None
            elif user_input[0] == "-" and user_input[1] == "0":
                if user_input[2] != ".":
                    return None

        # use a regular expression for validation
        match = re.match(r"^-?\d+(\.\d+)?$", user_input)  # allows optional
        if not match:
            return None

        try:
            return float(user_input)
        except ValueError:
            return None


def cnvt_n_pnt_to_celsius() -> None:
    """ converts Fahrenheit to Celsius and prints the result. """

    while True:
        temp_faht = get_user_input()

        if temp_faht is None:
            print("Invalid input. Please try again.")
            sys.stdout.flush()
            continue  # go to the next loop

        temp_cels: float = (temp_faht - 32) * (5 / 9)
        print(f"Temperature in Celsius: {temp_cels:.1f}")
        sys.stdout.flush()
        break  # exit the loop after right validation and calculation


def main():
    """ main program entry point. """
    cnvt_n_pnt_to_celsius()


if __name__ == "__main__":
    main()
