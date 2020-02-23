use std::sync::{ Mutex, Arc };
use std::thread;

fn main() {
    let counter1_1 = Arc::new(Mutex::new(0));
    let d1 = counter1_1.lock();
    let d2 = counter1_1.lock();
    /*
    let counter2_1 = Arc::new(Mutex::new(1));


    let mut handle1 = counter1_1.lock().unwrap();
    *handle1 += 1;
    let counter1_2 = Arc::clone(&counter1_1);
    let counter2_2 = Arc::clone(&counter2_1);
    thread::spawn(move || {
        let mut handle2_1 = counter2_1.lock().unwrap();
        *handle2_1 += 1;
        let mut handle1_2 = counter1_2.lock().unwrap();
        *handle1_2 += 1;
    });
    let mut handle2_2 = counter2_2.lock().unwrap();


    println!("Result: {}", *handle1);
    println!("Result: {}", *handle2_2);
    */
}
