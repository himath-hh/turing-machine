# Turing Machine

## Overview

The project simulates a Turing Machine using a configurable set of states and transitions defined in an *.atm* file. The Turing Machine reads an input string, processes it according to its transitions, and outputs the resulting string after halting. The machine can move left or right on its tape, read characters, write characters, and transition between states based on the rules defined in the *.atm* file.

## Project Structure

```
/src
    TuringMachine.java
    State.java
    Compiler.java
    /resources
        AddTwo.atm
        TwosComplement.atm
        IsEven.atm
```

- **/src /resources**: contains the *.atm* files defining the states and transitions for different Turing Machines.
- **TuringMachine.java**: Contains the implementation of the Turing Machine logic, including state transitions, tape manipulation, and halting.
- **State.java**: Defines the state structure, which includes a set of transitions for reading, writing, and moving along the tape.
- **Compiler.java**: Handles the reading of the *.atm* file and initializes the Turing Machine with the defined states and transitions.

## Classes

### TuringMachine

Simulates the behavior of a Turing Machine

- **Attributes**:

  - `states`: A HashMap that stores the states of the Turing Machine.
  - `initialState`: The name of the starting state.
  - `currentState`: The current state of the Turing Machine.
  - `haltingState`: The name of the halting state.
  - `blank`: The blank character used to pad the tape.
  - `tapeIndex`: The current position of the tape head.
  - `tape`: The StringBuilder representing the tape.

- **Methods**:

  - `TuringMachine(String blank, String initialState, String haltingState)`: Constructor that initializes the Turing Machine with the blank character, initial state, and halting state.
  - `addState(String name, State state)`: Adds a new state with its transitions.
  - `setInitialState(String initialState)`: Sets the initial state of the Turing Machine.
  - `Read(String input)`: Processes an input string and simulates the Turing Machine operations.

### State

Represents a single state in the Turing Machine, including its transitions.

- **Attributes**:

    - `table`: A HashMap of transitions where each key is a character to read, and the value is an array containing the write character, move direction, and next state.

- **Methods**:

    - `addTransition(char read, String write, String move, String state)`: Adds a transition for the state.
    - `getTransition(char read)`: Retrieves the transition for a specific read character.

### Compiler

Handles the reading of the *.atm* file and configures the Turing Machine with the defined states and transitions.

- **Methods**:
    - `runTM(String fileName)`: Reads the *.atm* file, extracts states, transitions, and initializes the Turing Machine.
    - `main(String[] args)`: Main method that runs the Turing Machine with the given *.atm* file and input string.

## Understanding the *.atm* file

An *.atm* file defines the rules for how the Turing Machine operates. It includes:

1. **Name**: The name of the Turing Machine.
2. **Description**: A brief description of the machine's purpose.
3. **Blank**: The character used for empty cells on the tape.
4. **Start**: The name of the initial state of the Turing Machine.
5. **States**: Each state is defined by a series of transitions that dictate the machine’s behavior. Transitions specify what the machine should
   do when reading a certain character on the tape (i.e., what to write, whether to move left or right, and which state to transition to).

#### Structure of a State

Each state in the _.atm_ file is preceded by a $ symbol and a state name. The transitions for a state are written below it. Each transition 
consists of:

- **Read**: The character the machine reads from the tape.
- **Write**: The character the machine writes to the tape.
- **Move**: The direction to move the tape head (Right or Left).
- **State**: The next state the machine should transition to after executing the current transition.

#### Example of an _.atm_ file: *AddTwo.atm*

This is an example of a simple Turing Machine that appends `11` to a unary number. For example, if the input is `1`, it will transform it into 
`111`.

```
Name: AddTwo.atm
Description: Append 11 to a unary number. For instance, 1 becomes 111. The smallest number is 1.
Blank:0
Start:AddOne

$AddOne
Read:0 Write:1 Move:Right State:AddTwo
Read:1 Write:1 Move:Right State:AddOne

$AddTwo
Read:0 Write:1 Move:None State:Halt
Read:1 Write:1 Move:None State:Halt
```

- **Name**: _AddTwo_ is the name of the Turing Machine.
- **Description**: The Turing Machine appends _11_ to a unary number (represented by 1s).
- **Blank**: _0_ is used to represent blank spaces on the tape.
- **Start**: The machine starts in the _AddOne_ state.

##### States and Transitions:

- *$AddOne*:

    - **Read**: `0`: If the machine reads `0`, it writes `1`, moves right, and transitions to the AddTwo state.
    - **Read**: `1`: If the machine reads `1`, it writes `1`, moves right, and stays in the AddOne state.

- *$AddTwo*:

    - **Read**: `0`: If the machine reads `0`, it writes `1`, does not move the tape, and halts the machine.
    - **Read**: `1`: If the machine reads `1`, it writes `1`, does not move the tape, and halts the machine.

The Turing Machine will continue processing the input until it reaches the `Halt` state.

## Creating an *.atm* file

To create an *.atm* file for your Turing Machine, follow these steps:

1. **Name**: Give the name for your Turing Machine.
2. **Description**: Add a description (optional).
3. **Blank**: Define the blank character.
4. **Start**: Define the startng state
5. **States**: Each state must start with the `$` symbol followed by the state name and define the transitions using:
    - **Read**: Character the machine reads.
    - **Write**: Character the machine writes.
    - **Move**: The direction the tape head needs to move.
    - **State**: The next state to transition to.

## Usage

#### 1. Prepare the *.atm* file

Store the *.atm* file in the */src /resources*. Example *.atm* files are stored in this folder.

#### 2. Run the Turing Machine

To run the Turing Machine provide the path to the *.atm* file and the input string as arguments to *TuringMachine.jar*.

```
java -jar TuringMachine.jar src/resources/yourFile.atm yourValue
```

Example using *AddTwo.atm*:

```
java -jar TuringMachine.jar src/resources/AddTwo.atm 1
```

The output should be the following:

```
111
```

## License

This project is licensed under the [MIT License](LICENSE.md).