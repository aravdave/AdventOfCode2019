# At first, I could not understand the directions in part 2 of day 2 of the 2019 Advent of Code.
# After rereading the instructions for an hour and a half. I decided to look on the Advent of Code subreddit for help.
# I am learning Python as I partcipate in this year's advent of code so most of the programs I saw were very complicated.
# However, I was able to complete part 1 of day 2 (and all previous parts for this year's Advent of Code) without any external help (besides looking up basic built-in Python methods).
# I finally stumbled upon https://github.com/puf17640/AdventOfCode2019 in the subreddit. After reading his/her code, I understood what the objective is.
# I noticed that his/her run() function looked extremely similar to mine besides the run() part (function part).
# I definetly borrowed the idea of using a function (along with using return instead of print) and the idea of a nested for loop to run all the possible combinations.
# I am making these comments to give credit to those who deserve it and to avoid plagiarizing.

noun = 0
verb = 0

def trial(noun, verb):
    data = [1,0,0,3,1,1,2,3,1,3,4,3,1,5,0,3,2,1,9,19,1,13,19,23,2,23,9,27,1,6,27,31,2,10,31,35,1,6,35,39,2,9,39,43,1,5,43,47,2,47,13,51,2,51,10,55,1,55,5,59,1,59,9,63,1,63,9,67,2,6,67,71,1,5,71,75,1,75,6,79,1,6,79,83,1,83,9,87,2,87,10,91,2,91,10,95,1,95,5,99,1,99,13,103,2,103,9,107,1,6,107,111,1,111,5,115,1,115,2,119,1,5,119,0,99,2,0,14,0]
    data[1] = noun
    data[2] = verb
    i = 0
    while (i < (len(data))):
        if (data[i] == 99):
            break
        elif (data[i] == 1):
            data[data[i+3]] = (data[data[i+1]] + data[data[i+2]])
            i += 4
        elif (data[i] == 2):
            data[data[i+3]] = (data[data[i+1]] * data[data[i+2]])
            i += 4
        else:
            print("Something went wrong. You have to fix your code.")
            break
    return (data[0])

for noun in range(100):
    for verb in range(100):
        if (trial(noun, verb) == 19690720):
            print(100 * noun + verb)