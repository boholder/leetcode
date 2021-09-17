fn main() {
    // statistics::run();
    // pig_latin::run();
    hr::run();
}

mod hr {
    use std::collections::HashMap;
    use regex::Regex;

    pub(crate) fn run() {
        let mut company = Company::new();
        let dept1 = "Engineering";
        let steve = "Steve";
        company.add(&concat_decision(dept1, steve));
        let employees = company.get_department_employees(dept1).unwrap();
        println!("{:?}", employees);
    }

    fn concat_decision(department: &str, employee: &str) -> String {
        format!("Add {} to {}", employee, department)
    }

    struct Company {
        employees: HashMap<String, Vec<String>>,
    }

    impl Company {
        fn new() -> Company {
            Company { employees: HashMap::new() }
        }

        fn add(&mut self, decision: &str) {
            let re = Regex::new(r"Add\s([[:alpha:]]+)\sto\s([[:alpha:]]+)").unwrap();
            let matches = re.captures(decision).unwrap();
            let employee = matches.get(1).unwrap().as_str().to_string();
            let department = matches.get(2).unwrap().as_str().to_string();
            let department = self.employees.entry(department).or_insert(Vec::new());
            department.push(employee);
        }

        fn get_department_employees(&self, department_name: &str) -> Option<&Vec<String>> {
            self.employees.get(department_name)
        }
    }
}

// mod pig_latin {
//     pub(crate) fn run() {
//         println!("pig latin:");
//         assert_eq!(convert(&"apple"), "apple-hey");
//         println!("apple-hey!");
//         assert_eq!(convert(&"fre"), "re-fey");
//         println!("re-fey!");
//     }
//
//     fn convert(origin: &str) -> String {
//         let vowels = vec!['a', 'e', 'i', 'u', 'o'];
//         let origin_copy = origin.to_string();
//         let first_char = origin.chars().next().unwrap_or('b');
//
//         if vowels.contains(&first_char) {
//             origin_copy + &"-hey"
//         } else {
//             let head_removed = String::from(&origin_copy[1..origin_copy.len()]);
//             head_removed + &"-" + &origin_copy[0..1] + &"ey"
//         }
//     }
// }
//
// mod statistics {
//     use std::collections::HashMap;
//
//     pub(crate) fn run() {
//         println!("statistics:");
//         let nums: Vec<u32> = vec![1, 2, 3, 3, 4, 5];
//         let mean = calc_mean(&nums);
//         assert_eq!(mean, 3);
//         println!("mean: {}", mean);
//         if let Some(number) = calc_middle(&nums) {
//             assert_eq!(number, 3);
//             println!("middle: {}", number);
//         }
//         let mode = calc_mode(&nums);
//         assert_eq!(*mode, 3);
//         println!("mode: {}", mode);
//     }
//
//     fn calc_mean(nums: &Vec<u32>) -> u32 {
//         let mut mean = 0;
//         for num in nums {
//             mean += num;
//         }
//         mean / nums.len() as u32
//     }
//
//     fn calc_middle(nums: &Vec<u32>) -> Option<u32> {
//         let mut sorted = nums.clone();
//         sorted.sort();
//         let middle = sorted.get(sorted.len() / 2usize);
//         middle.cloned()
//     }
//
//     fn calc_mode(nums: &Vec<u32>) -> &u32 {
//         let mut count_map = HashMap::new();
//         for num in nums {
//             let count = count_map.entry(num).or_insert(0);
//             *count += 1;
//         }
//         let most_frequent = count_map.into_iter()
//             .reduce(|a, b| {
//                 if a.1 > b.1 { a } else { b }
//             });
//         most_frequent.unwrap().0
//     }
// }