class Person extends GameObject {
    constructor(config) {
        super(config);
        this.movingProgressRemaining = 0;
        this.isPlayer =  config.isPlayer || false;
        this.directionUpdate = {
            "down": ["y", 1],
            "up": ["y", -1],
            "left": ["x", -1],
            "right": ["x", 1],

        }
    }
    update(state){
        if (this.movingProgressRemaining > 0){
            this.updatePosition();
        } else {
            if (this.isPlayer && state.arrow){
                this.startBehavior(state, {
                    type: "walk",
                    direction: state.arrow,
                })
            }
            this.updateSprite(state);
        }
    }

    startBehavior(state, behavior) {
        // Setting direction
        this.direction = behavior.direction; 
        // May be doing different actions
        if (behavior.type == "walk"){
            // Stop if space isn't free
            if (state.map.isSpaceTaken(this.x, this.y, this.direction)){
                return;
            };
            state.map.moveWall(this.x, this.y, this.direction);
            this.movingProgressRemaining = 16;
        }
    }

    updatePosition(){
        const [property, change] = this.directionUpdate[this.direction];
        this[property] += change;
        this.movingProgressRemaining -= 1;
    }

    updateSprite(){
        if(this.movingProgressRemaining > 0){
            this.sprite.setAnimation("walk-" + this.direction);
            return;
        }
        this.sprite.setAnimation("idle-" + this.direction);

    }
}