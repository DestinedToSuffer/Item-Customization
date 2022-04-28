## About The Project

This is a recreate of my first every minecraft plugin. I have not created plugins in a long time and wanted to start with something simple. If you think something needs changing or additions please let me know! Plan to create more plugins and get back into plugin creation!

https://www.spigotmc.org/resources/item-customization.101675/

### Installation
1. Spigot 1.18.x
2. Download the ItemCustomization-1.1
3. Move into plugins folder
4. Edit the config as you see fit!


### Commands
/setname [name]
  -  Description: Change the name of your item at the cost of xp
  -  Permission: setname.use

/setlore [lore]
  - Description: Change the lore of your item at the cost of xp
  - Permission: setlore.use

/glow
  - Description: Create an enchanted effect without an enchant at the cost of xp
  - Permission: glow.use

/resetname
  - Description: Resets the name of the item and will refund some xp
  - Permission: resetname.use

/resetlore
  - Description: Resets the lore of the item and will refund some xp
  - Permission: resetlore.use

/resetglow
  - Description: Resets the glow effect on the item and will refund some xp
  - Permission: resetglow.use


### Default Config
```
SetName:
  - requiresExp: true
  - expRequired: 5
SetLore:
  - requiresExp: true
  - expRequired: 5
Glow:
  - requiresExp: true
  - expRequired: 1
ResetLore:
  - refund: true
  - expRefunded: 2
ResetName:
  - refund: true
  - expRefunded: 2
ResetGlow:
  - refund: false
  - expRefunded: 1
```
