#[derive(Debug)]

struct Rectangle {
    width: u32,
    height: u32
}

impl Rectangle {
    fn square(size: u32) -> Rectangle {
        Rectangle { width: size, height: size }
    }

    fn area(&self) -> u32 {
        self.width * self.height
    }
    fn can_hold(&self, other: &Rectangle) -> bool {
        self.width > other.width && self.height > other.height
    }
}


fn main() {
    //let width1 = 30;
    //let height1 = 50;
    let rect1 = Rectangle {width: 40, height: 50};
    let rect2 = Rectangle {width: 10, height: 20};
    let rect3 = Rectangle {width: 60, height: 20};


    println!("The area of rectangle is {}", rect1.area());
    println!("Can rect1 hold rect2? {}", rect1.can_hold(&rect2));
    println!("Can rect1 hold rect3? {}", rect1.can_hold(&rect3));

}

