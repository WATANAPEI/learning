extern crate getopts;
extern crate rustc_serialize;
extern crate csv;

use std::env;
use getopts::Options;
use std::path::Path;

#[derive(Debug, RustcDecodable)]
struct Row {
    country: String,
    city: String,
    accent_city: String,
    region: String,

    populatin: Option<u64>,
    latitude: Option<u64>,
    longtitude: Option<u64>,

}

struct PopulationCount {
    city: String,
    country: String,
    count: u64,
}

fn print_usage(program: &str, opts: Options) {
    println!("{}", opts.usage(&format!("Usage: {} [options] <data-path> <city>", program)));
}

fn search<P: AsRef<Path>>(file_path: P, city: &str) -> Vec<PopulationCount> {
    let mut found = vec![];
    let file = std::fs::File::open(file_path).unwrap();
    let mut rdr = csv::Reader::from_reader(file);

    for row in rdr.decode::<Row>() {
        let row = row.unwrap();
        match row.populatin {
            None => { },
            Some(count) => if row.city == city {
                found.push(PopulationCount {
                    city: row.city,
                    country: row.country,
                    count: count,
                });
            },
        }
    }
    found
}

fn main() {
    let args: Vec<String> = env::args().collect();
    let program = args[0].clone();

    let mut opts = Options::new();
    opts.optflag("h", "help", "Show this usage message.");

    let matches = match opts.parse(&args[1..]) {
        Ok(m) => { m },
        Err(e) => { panic!(e.to_string()) }
    };

    if matches.opt_present("h") {
        print_usage(&program, opts);
        return;
    }
    let data_file = args[1].clone();
    let data_path = std::path::Path::new(&data_file);
    let city = args[2].clone();
    for pop in search(&data_path, &city) {
        println!("{}, {}: {:?}", pop.city, pop.country, pop.count);
    }

}
