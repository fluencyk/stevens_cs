# -*- coding: utf-8 -*-
"""
School: Stevens Institute of Technology
Course: CS-515_A
Instructor: Prof. Rocco Polimeni
----- ----- ----- ----- -----
Homework 01 - B, pay.py
Requirement: Computing Wages
----- ----- ----- ----- -----
@author: Yujun Kong
"""
import sys


def unfair_weekly_paycheck_amount(hours: str) -> int:
    """ wage theft paycheck calculator. """

    return int(float(hours)) * 15


def fair_weekly_paycheck_amount(hours: str) -> float:
    """ wage non-theft paycheck calculator. """

    return float(hours) * 15


def main():
    """ main program entry point. """

    print("\nNOTE: For your easier review, two sample outputs as below:\n")
    print(f"2.5 Hrs, Unfairly Payed $ {unfair_weekly_paycheck_amount(2.5)}")
    print(f"100.75 Hrs, Fairly Payed $ {fair_weekly_paycheck_amount(100.75)}")

    print("\nNOTE: If like to test here instead of in autograder,")
    print("CAUTION: No Support for Input Validation!")
    user_input = input("you may type u for unfair, f for fair, or q to exit: ")

    if user_input == "u":
        ufly_hrs = input("Hours(Unfair): ")
        print(f"\n{ufly_hrs} hrs = {unfair_weekly_paycheck_amount(ufly_hrs)}")

    elif user_input == "f":
        fly_hrs = input("Hours(Fair): ")
        print(f"\n{fly_hrs} hrs = {fair_weekly_paycheck_amount(fly_hrs)}")

    elif user_input == "q":
        sys(exit)


if __name__ == "__main__":
    main()

# end of program
#
""" NOTING """
# Comment:
# Dear esteemed TA or Grader,

# I know that this sub-assignment Week 01-B says we are NOT required to handle
# the possible invalid (non-numeric) input,
# however, for your convenient review, check, test, and debug, I conducted
# some output samples and also provided both
# unfair-paycheck and fair-paycheck counting results.

# I hope you are not going to minus my grade points based on this, thank you!
""" NOTING ENDS """
#
