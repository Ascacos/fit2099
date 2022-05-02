Design Rationale 
=

REQ1
-

In requirement 1, we decided to have the Tree class be abstract, and extend its functionality in 3 subclasses (Mature, Sapling, Sprout). This allows us to put any shared functionality into the Tree class, and implement any differences into the respective classes. We believe having this as an abstract class will help us easily extend the project via inheritance in future.
The Sprout and Mature directly creates new Goomba and Koopa trees (respectively), which resulted in that association. 
The Sapling generates coins, which differ from Mature and Sprout who spawn Actors. Coins inherit from the abstract Item class given by the engine. Instead of going into the Actor’s inventory, it will go into a wallet which will hold a simple integer value, representing the Actor’s money (see REQ 6)
Sprouts grow into Sapling, which grow into Mature, which create new Sprouts in adjacent dirt. Due to this functionality, there is a ‘cyclic’ association between these 3 classes. 
The Mature tree also have a chance to reduce to dirt each turn. This results in an association to the Dirt class, as it needs to create new Dirt objects.

REQ2
-

In order to implement jumping to higher grounds, we added a JumpAction class, which inherits from the engine’s abstract Action class. Any grounds that we determined should be ‘tall’ (Trees, Wall) is given the Status.TALL capability. By using capabilities, we can prevent code smells such as instanceof. We implemented the success rate and fall damage into the constructor for the JumpAction object, so that we can easily manipulate it to suit the different types of tall grounds.
When the player is under the effect of the Super Mushroom effect, we could simply check if the Actor executing the JumpAction had the Status.SUPER_MUSHROOM capability, and were able to negate any success rates and inflicted damage. 

REQ3
-



The game had two enemies - a simple Goomba and a slightly more complex Koopa.
To handle these enemies' attacks (kick and punch) we simply implemented an override method of the IntrinsicWeapon for these two actors. We found that this made very convenient use of the engine code. By default, these two Actors are initialised with a WanderBehaviour, until another actor (the player), that is Status.HOSTILE_TO_ENEMIES, lands in one of the exits of the enemy’s location. This will add a FollowBehaviour with the player as the target. In the Goomba’s playTurn() function, we simply set a 10% chance for it to execute a SuicideAction.
In order to implement the Koopa’s dormant behaviour, we added a Shell interface that we can implement in Koopa. This Shell interface only had one function, but allowed us to pass a reference of the Koopa as a Shell object, to a DestroyShellAction (when attacked by a Wrench). This follows the Liskov Substitution Principle (LSP). With this Shell interface, we can enforce that the Koopa must implement a destroyShell() method, which is how we can also drop the Super Mushroom in the game world. We implemented the Wrench as a subclass of WeaponItem (from the engine). 

REQ4
-

Capabilities was a huge asset in this requirement, as when the player consumes the item we can simply add a capability to the player and check it as required. This made very good use of the Capable interface.
The challenge in this requirement was how to allow players to consume items directly off the ground. The engine had it’s own builtin PickupItemAction. In order to add the functionality to actually consume the item, we added an abstract ConsumtableItem class, which would hold all the required functionality for consuming the item. Each ConsumableItem object was initialised with it’s own instance of a ConsumeItemAction, which was passed down the line to it’s sub-classes (PowerStar.java and SuperMushroom.java), which could remove the ConsumeAction from these items once called. This was particularly important for the PowerStar, which had to last multiple turns. Once consumed, we couldn’t remove it from the player without removing the buff itself. So, we kept the item in the player’s inventory, but remove any hope of interacting with it, until it expired. We did this by removing the ConsumeAction (specified in ConsumeItem’s constructor. This also meant we could pass subtypes of ConsumableItems to the ConsumeItemAction - which took ConsumableItems as a parameter. Sending sub-types this way is an example of the open-close principles (OCP) and will help us to add more consumable items in future, in the extension tasks.

REQ5
-

In order to implement trading, we had to first consider how to store balances at all. Due to the way the engine works, we couldn’t simply add an attribute to player, because we only ever check Actor objects (and casting to a player would have been poor object oriented practice). We decided to implement Wallets as a separate class, with a singleton WalletManager instance that holds (and manages) all actor’s and their wallets. This way, we could just add the Player with a new wallet to the WalletManager, inside the Player’s constructor. We also added an Economy interface, to ensure we had a strong contract between Actors and the wallet system, which will help with future implementations. With this, it was quite easy to initialise the Toad actor with various different actions to allow the purchase of items. We chose to solve this with a BuyItemAction, which would work for any different item with its flexible constructor.

REQ6
-


REQ7
-

