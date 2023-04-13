class HouseMap{
    constructor(config){
        this.gameObjects = config.gameObjects;

        this.walls = config.walls || {};

        // Drawn below character
        this.lowerImage = new Image();
        this.lowerImage.src = config.lowerSrc;
        // Drawn above character
        this.upperImage = new Image();
        this.upperImage.src = config.upperSrc;
       
    }

    drawLowerImage(ctx){
        ctx.drawImage(this.lowerImage, 0, 0);
    }

    drawUpperImage(ctx){
        ctx.drawImage(this.upperImage, 0, 0);

    }

    isSpaceTaken(currentX, currentY, direction){
        const {x, y} = utils.nextPosition(currentX, currentY, direction);
        return this.walls[`${x},${y}`] || false;
    }

    mountObjects(){
        Object.values(this.gameObjects).forEach(o => {
            o.mount(this);
        })
    }

    addWall(x, y){
        this.walls[`${x},${y}`] = true;
    }

    removeWall(x, y){
        delete this.walls[`${x},${y}`];
    }

    moveWall(preX, preY, direction){
        this.removeWall(preX, preY);
        const {x, y} = utils.nextPosition(preX, preY, direction);
        this.addWall(x, y);
    }
}

window.HouseMaps = {
    Demo: {
        lowerSrc: "/images/maps/DemoLower.png",
        upperSrc: "/images/maps/DemoUpper.png",
        gameObjects: {
            hero: new Person({
                x: utils.withGrid(5),
                y: utils.withGrid(6),
                src: "/images/test-characters/people/hero.png",
                useShadow: true,
                isPlayer: true
            }),
            npc: new Person({
                x: utils.withGrid(7),
                y: utils.withGrid(9),
                src: "/images/test-characters/people/npc1.png",
                useShadow: true
            }),
        },
        walls: {
            [utils.asGridCoord(7, 6)]: true,
            [utils.asGridCoord(8, 6)]: true,
            [utils.asGridCoord(7, 7)]: true,
            [utils.asGridCoord(8, 7)]: true,
        }
    },
    Bedroom: {
        lowerSrc: "/images/Backgrounds/Bedroom.png",
        upperSrc: "/images/maps/KitchenUpper.png",
        gameObjects: {
            hero: new Person({
                x: utils.withGrid(0),
                y: utils.withGrid(1),
                xOff: 4,
                yOff: 7,
                src: "/images/Characters/Linus SpriteSheet.png",
                useShadow: true,
                isPlayer: true
            }),
            // npc: new Person({
            //     x: utils.withGrid(),
            //     y: utils.withGrid(5),
            //     src: "/images/test-characters/people/npc1.png",
            //     useShadow: true
            // })
        }
    },
}