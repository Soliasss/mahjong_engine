# Mahjong Engine Design Document

This document provide information about the package of the engine module.
If we use the following packages we will not have to search too many time in the directory to find a specific file.
We will separate Objects from Exceptions and Tiles from Zones

## Normalised naming of mahjong package

```
engine  
│
└───Object
|   └───Tiles
|   │   |   AbstractTiles.java
|   │   │   CommonTile.java
|   |   |   Complex.Tile.java
|   |   |   Flower.java
|   |   |   GameTile.java
|   |   |   HonorTile.java
|   |   |   Season.java
|   |   |   SimpleHonor.java
|   |   |   SuperiorHonor.java
|   |   |   WindHonor.java
|   │   
|   └───Zones
|   |   │   MoneyZone.java
|   |   │   MoneyZone.java
|   |   │   TileZone.java
|   |   |   Zone.java
|   |
|   └───Board.java
|   └───Game.java
└───Exceptions
|   └───TileZoneException.java
```