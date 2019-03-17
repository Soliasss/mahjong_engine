# Mahjong Engine Design Document

This document provide information about the design decisions made about the engine module.

## Normalised naming of mahjong tiles

### Commons

#### Dots

```regex
d[1-9]
```

#### Bamboo

```regex
b[1-9]
```

#### Characters

```regex
c[1-9]
```

### Honors

#### Wind

```regex
W(e|s|w|n)
```

#### Dragons

```regex
D(r|g|w)
```

### Bonus

#### Flowers

```regex
F[1-4]
```

##### Flower Detail

F1 : Plum blossom

F2 : Orchid

F3 : Chrysanthemum

F4 : Bamboo

#### Seasons

```regex
S[1-4]
```

##### Seasons Detail

S1 : Spring

S2 : Summer

S3 : Autumn

S4 : Winter

## Move priority representation

### What is move priority

Only one move can be played at a time, but two players (possibly more) can propose moves, priority is now key to determine what move has to be played.

Possible moves appear as list, a move contains its order, as a field (int).

The value 0 is the highest priority possible, the higher the order the lower the priority.

### Usage of move priority

Engine uses move priority to determine what changes it has to apply to the board.

If this property is carried through to the GUI, it can be used to suggest a move to its user.
