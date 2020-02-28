fn main() {
    let x = Some(5);
    let y = 10;

    match x {
        Some(50) => println!("Got 50"),
        Some(y) => println!("Matched. Got {:?}", y),
        _ => println!("Got {:?}", x),
    }
    println!("x and y is {:?}, {:?}", x, y);
}
