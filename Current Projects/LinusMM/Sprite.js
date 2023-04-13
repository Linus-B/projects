// Gonna work with animations for the sprite
class Sprite{
    constructor(config){
        // Creating Images
        this.image = new Image();
        this.image.src = config.src;
        this.image.onload = () => {
            this.isLoaded = true;
        }
        // Shadow
        this.shadow = new Image();
        this.useShadow = config.useShadow || false;
        this.shadow.src = this.useShadow ? "/images/test-characters/shadow.png" : "";
        this.shadow.onload = () => {
            this.isShadowLoaded = true;
        }
        this.useShadow = true; // will change

        // Configure animations
        this.animations = config.animations || {
            "idle-down": [ [0, 0] ],
            "walk-down": [ [1, 0], [0, 0], [3, 0], [0, 0] ],
            "idle-up": [ [0, 2] ],
            "walk-up": [ [1, 2], [0, 2], [3, 2], [0, 2] ],
            "idle-left": [ [0, 3] ],
            "walk-left": [ [1, 3], [0, 3], [3, 3], [0, 3] ],
            "idle-right": [ [0, 1] ],
            "walk-right": [ [1, 1], [0, 1], [3, 1], [0, 1] ],

        }


        this.currentAnimation = config.currentAnimation || "idle-down";
        this.currentAnimationFrame = 0;
        this.animationFrameLimit = config.animationFrameLimit || 8;
        this.animationFramePorgress = this.animationFrameLimit;

        // Reference the gameObject
        this.gameObject = config.gameObject;
    }

    get frame() {
        return this.animations[this.currentAnimation][this.currentAnimationFrame];
    }

    setAnimation(key){
        if (this.currentAnimation != key){
            this.currentAnimation = key;
            this.currentAnimationFrame = 0;
            this.animationFramePorgress = this.animationFrameLimit;

        }
    }

    updateAnimationProgress(){
        if (this.animationFramePorgress > 0){
            this.animationFramePorgress--;
            return;
        } else {
            this.animationFramePorgress = this.animationFrameLimit;
            this.currentAnimationFrame += 1;
            // CHANGE TO MODULUS
            if (this.frame === undefined){
                this.currentAnimationFrame = 0;
            }
        }
    }

    draw(ctx){
        const x = this.gameObject.x - this.gameObject.xOff;
        const y = this.gameObject.y - this.gameObject.yOff;
        this.isShadowLoaded && ctx.drawImage(this.shadow, x, y);
        // draw person

        const [xFrame, yFrame] = this.frame;
        this.isLoaded && ctx.drawImage(this.image,
            // left top crop
            xFrame * 32, yFrame * 32, 
            // width then height of crop
            32, 32,
            // position of the cut
            x, y,
            // size it is drawn
            32, 32);
        
        this.updateAnimationProgress();
        
    }

    init() {

    }

}