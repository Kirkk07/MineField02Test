//public class Player {
//    private int x;
//    private int y;
//    private Player player;
//
//    public Player() {
//        this.x = 0;
//        this.y = 0;
//    }
//
////    public void moveUp() {
////        if (y > 0) y--;
////    }
////     public void moveUp() {
////        if (y > 0) y--;
////    }
////
////    public void moveDown() {
////        if (y < 9) y++;
////    }
////
////    public void moveLeft() {
////        if (x > 0) x--;
////    }
////
////    public void moveRight() {
////        if (x < 9) x++;
////    }
////
////    public int getX() {
////        return x;
////    }
////
////    public int getY() {
////        return y;
////    }
//
////
//public void moveUp() {
//    if (x > 0) {
//        x--;
//    } else {
//        System.out.println("You can not go outside of the grid, try other directions.");
//    }
//}
//
//    public void moveDown() {
//        if (x < 9) {
//            x++;
//        } else {
//            System.out.println("You can not go outside of the grid, try other directions.");
//        }
//    }
//
//    public void moveLeft() {
//        if (y > 0) {
//            y--;
//        } else {
//            System.out.println("You can not go outside of the grid, try other directions.");
//        }
//    }
//
//    public void moveRight() {
//        if (y < 9) {
//            y++;
//        } else {
//            System.out.println("You can not go outside of the grid, try other directions.");
//        }
//    }
//
//    public int getX() {
//        return x;
//    }
//
//    public int getY() {
//        return y;
//    }
//
//
//
//}
//
public class Player {
    private int x;
    private int y;
    private boolean hasProtection = false;


    public Player() {
        this.x = 0;
        this.y = 0;
    }

    public boolean hasProtection() {
        return hasProtection;
    }

    public void giveProtection() {
        hasProtection = true;
    }

    public void useProtection() {
        hasProtection = false;
    }


    public boolean moveLeft() {
        if (x > 0) {
            x--;
            return true;
        } else {
            System.out.println("You can not go outside of the grid, try other directions.");
            return false;
        }
    }

    public boolean  moveRight(){
        if (x < 9) {
            x++;
            return true;
        } else {
            System.out.println("You can not go outside of the grid, try other directions.");
            return false;
        }
    }

    public boolean moveUp() {
        if (y > 0) {
            y--;
            return true;
        } else {
            System.out.println("You can not go outside of the grid, try other directions.");
            return false;
        }
    }

    public boolean  moveDown() {
        if (y < 9) {
            y++;
            return true;
        } else {
            System.out.println("You can not go outside of the grid, try other directions.");
            return false;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
