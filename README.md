# OpenComputers Driver for GregTech Battery Buffers

This mod allows the Adapter block from OpenComputers to read information about the Battery Buffers from GregTech: Community Edition.

**This mod was only programmed and tested for MC 1.12.2.**

To compile this mod, download the repo and execute `./gradlew build`. The jar file will then be compiled into `build/libs/`.

It provides the following functions:

```lua
local component = require("component")
local b = component.battery_buffer

-- Returns the charge of the buffer or of a single battery (indexed starting from 0)
b.getCharge()
b.getSingleCharge(0)
b.getCapacity()
b.getSingleCapacity(0)

-- Returns the Input/Output Voltage/Amperage of the buffer
b.getInputVoltage()
b.getInputAmperage()
b.getOutputVoltage()
b.getOutputAmperage()

-- Returns the number of batteries that are actually in the buffer right now
b.getBatteryCount()

-- Returns the number of slots the buffer has
b.getBatterySlots()
```
