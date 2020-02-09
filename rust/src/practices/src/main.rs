use std::collections::HashMap;

fn main() {
    let mut v: Vec<isize> = vec![2, 5, 20, -3, 4, 5];
    let mut sum = 0;
    for i in &v {
        sum += *i;
    }
    let len = v.len();
    let avg = sum / (len as isize);
    println!("average of v is: {}", avg);
    v.sort();
    println!("median of v is: {}", v[len/2]);
    let mut map = HashMap::new();
    for i in &v {
        let count = map.entry(i).or_insert(0);
        *count += 1;
    }
    println!("{:?}", map);

    let text = "first";
    let mut chars = text.chars();
    match chars.next() {
        Some('a') | Some('i') | Some('u') | Some('e') | Some('o') => {
            println!("{}-hay", text);
        },
        Some(_) => {
            let mut result = String::new();
            for i in chars {
                result.push(i);
            };
            println!("{}-fay", result);
        },
        None => println!("None"),

    }


}
