/*
Algorithm: 
Take each test case and go through it one digit at a time
Each instance of an odd digit x can be split into 1 and x-1
The special case here is if x is 5 which creates a 4, in this case split it into 3 and 2

Each instance of an even digit y can be split into two instances of y/2
The special case here is if y is 8 which creates more 4s, in this case split it into 5 and 3
Finally check for leading 0s in the second check and prune if necessary
*/

const readline = require('readline').createInterface({input: process.stdin});
			 
let state = "test_cases";
let num_test_cases;
let test_case = 1;
const split_number = function(number){
	let split_checks = {check_1:"",check_2:""};
	charArr = [...number];
	charArr.map(
		function(digit){
			digit = parseInt(digit);
			if(digit === 5){
				split_checks.check_1 += 3;
				split_checks.check_2 += 2;
			}
			else if(digit % 2 === 1){
				split_checks.check_1 += 1;
				split_checks.check_2 += digit - 1;
			}
			else if(digit === 8){
				split_checks.check_1 += 5;
				split_checks.check_2 += 3;
			}
			else{
				split_checks.check_1 += (digit/2);
				split_checks.check_2 += (digit/2);
			}
		}
	);
	while(split_checks.check_2.startsWith('0')){
		split_checks.check_2 = split_checks.check_2.substring(1);
	}
	return split_checks;
}
readline.on('line', function(line) {
	if(state === "test_cases"){
		num_test_cases = parseInt(line);
		state = "check_amounts";
	}
	else{
		if(test_case > num_test_cases){
			process.exit(0);
		}
		else{
			let result = split_number(line.trim());
			console.log(`Case #${test_case++}: ${result.check_1} ${result.check_2}`);
		}
	}
}).on('close',function(){
    process.exit(0);
});
