class GameObject{
    constructor(config){
        this.isMounted = false;
        this.x = config.x || 0;
        this.y = config.y || 0;
        this.xOff = config.xOff || 0;
        this.yOff = config.yOff || 0;

        this.direction = config.direction || "down";
        this.sprite = new Sprite({
            gameObject: this,
            src: config.src || "/images/test-characters/people/hero.png",
            useShadow: config.useShadow || false

        });
    }

    mount(map){
        this.isMounted = true;
        map.addWall(this.x, this.y);
        console.log("Mounting");
    }

    update(){

    }
}