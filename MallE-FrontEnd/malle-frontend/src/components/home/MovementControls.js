
export function handleKeyPress(moveStatus, headServoStatus, keyPressed) {
    switch (keyPressed) {
        //------- HBridge movement -------
        case "ArrowUp":
            var newMoveStatus;
            switch (moveStatus) {
                case "S":
                    newMoveStatus = "F";
                    break;
            }
            return { newMoveStatus: newMoveStatus, newhHeadServoStatus: "" };
        case "ArrowDown":
            var newMoveStatus;
            switch (moveStatus) {
                case "S":
                    newMoveStatus = "B";
                    break;
            }
            return { newMoveStatus: newMoveStatus, newhHeadServoStatus: "" };
        case "ArrowLeft":
            var newMoveStatus;
            switch (moveStatus) {
                case "S":
                    newMoveStatus = "L";
                    break;
            }
            return { newMoveStatus: newMoveStatus, newhHeadServoStatus: "" };
        case "ArrowRight":
            var newMoveStatus;
            switch (moveStatus) {
                case "S":
                    newMoveStatus = "R";
                    break;
            }
            return { newMoveStatus: newMoveStatus, newhHeadServoStatus: "" };

        //------- Head Servo movement -------   
        case "w":
        case "W":
            var newhHeadServoStatus;
            switch (headServoStatus) {
                case "S":
                    newhHeadServoStatus = "U";
                    break;
                case "R":
                    newhHeadServoStatus = "UR";
                    break;
                case "L":
                    newhHeadServoStatus = "UL";
                    break;
            }
            return { newMoveStatus: "", newhHeadServoStatus: newhHeadServoStatus };
        case "s":
        case "S":
            var newhHeadServoStatus;
            switch (headServoStatus) {
                case "S":
                    newhHeadServoStatus = "D";
                    break;
                case "R":
                    newhHeadServoStatus = "DR";
                    break;
                case "L":
                    newhHeadServoStatus = "DL";
                    break;
            }
            return { newMoveStatus: "", newhHeadServoStatus: newhHeadServoStatus };
        case "a":
        case "A":
            var newhHeadServoStatus;
            switch (headServoStatus) {
                case "S":
                    newhHeadServoStatus = "L";
                    break;
                case "U":
                    newhHeadServoStatus = "UL";
                    break;
                case "D":
                    newhHeadServoStatus = "DL";
                    break;
            }
            return { newMoveStatus: "", newhHeadServoStatus: newhHeadServoStatus };
        case "d":
        case "D":
            var newhHeadServoStatus;
            switch (headServoStatus) {
                case "S":
                    newhHeadServoStatus = "R";
                    break;
                case "U":
                    newhHeadServoStatus = "UR";
                    break;
                case "D":
                    newhHeadServoStatus = "DR";
                    break;
            }
            return { newMoveStatus: "", newhHeadServoStatus: newhHeadServoStatus };
    }
}
export function handleKeyRelease(moveStatus, headServoStatus, keyReleased) {
    switch (keyReleased) {
        //------- HBridge movement -------
        case "ArrowUp":
            var newMoveStatus;
            switch (moveStatus) {
                case "F":
                    newMoveStatus = "S";
                    break;
                case "FR":
                    newMoveStatus = "R";
                    break;
                case "FL":
                    newMoveStatus = "L";
                    break;
            }
            return { newMoveStatus: newMoveStatus, newhHeadServoStatus: "" };
        case "ArrowDown":
            var newMoveStatus;
            switch (moveStatus) {
                case "B":
                    newMoveStatus = "S";
                    break;
                case "BR":
                    newMoveStatus = "R";
                    break;
                case "BL":
                    newMoveStatus = "L";
                    break;
            }
            return { newMoveStatus: newMoveStatus, newhHeadServoStatus: "" };
        case "ArrowLeft":
            var newMoveStatus;
            switch (moveStatus) {
                case "L":
                    newMoveStatus = "S";
                    break;
                case "FL":
                    newMoveStatus = "F";
                    break;
                case "BL":
                    newMoveStatus = "B";
                    break;
            }
            return { newMoveStatus: newMoveStatus, newhHeadServoStatus: "" };
        case "ArrowRight":
            var newMoveStatus;
            switch (moveStatus) {
                case "R":
                    newMoveStatus = "S";
                    break;
                case "FR":
                    newMoveStatus = "F";
                    break;
                case "BR":
                    newMoveStatus = "B";
                    break;
            }
            return { newMoveStatus: newMoveStatus, newhHeadServoStatus: "" };
        //------- Head Servo movement -------   
        case "w":
        case "W":
            var newhHeadServoStatus;
            switch (headServoStatus) {
                case "U":
                    newhHeadServoStatus = "S";
                    break;
                case "UR":
                    newhHeadServoStatus = "R";
                    break;
                case "UL":
                    newhHeadServoStatus = "L";
                    break;
            }
            return { newMoveStatus: "", newhHeadServoStatus: newhHeadServoStatus };
        case "s":
        case "S":
            var newhHeadServoStatus;
            switch (headServoStatus) {
                case "D":
                    newhHeadServoStatus = "S";
                    break;
                case "DR":
                    newhHeadServoStatus = "R";
                    break;
                case "DL":
                    newhHeadServoStatus = "L";
                    break;
            }
            return { newMoveStatus: "", newhHeadServoStatus: newhHeadServoStatus };
        case "a":
        case "A":
            var newhHeadServoStatus;
            switch (headServoStatus) {
                case "L":
                    newhHeadServoStatus = "S";
                    break;
                case "UL":
                    newhHeadServoStatus = "U";
                    break;
                case "DL":
                    newhHeadServoStatus = "D";
                    break;
            }
            return { newMoveStatus: "", newhHeadServoStatus: newhHeadServoStatus };
        case "d":
        case "D":
            var newhHeadServoStatus;
            switch (headServoStatus) {
                case "R":
                    newhHeadServoStatus = "S";
                    break;
                case "UR":
                    newhHeadServoStatus = "U";
                    break;
                case "DR":
                    newhHeadServoStatus = "D";
                    break;
            }
            return { newMoveStatus: "", newhHeadServoStatus: newhHeadServoStatus };
    }
}