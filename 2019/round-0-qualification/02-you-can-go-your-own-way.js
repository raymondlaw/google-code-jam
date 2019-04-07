/*
Algorithm: My path will always alternate S and E unless that is impossible
My path can only possibly overlap with Lydia on x=y, 
In these instances see what Lydia choose and go opposite, on the next step go back to the diagonal
*/

const readline = require('readline').createInterface({input: process.stdin});
let state = "test_cases";
let num_test_cases;
let test_case = 1;
let dimension;

const find_path = function(lydia_path_str){
	const lydia_path = [...lydia_path_str];
	let lydia_coordiantes = {x:0,y:0};
	let my_path_str = "";
	let my_next_step;
	lydia_path.map(
		function(step){
			if(lydia_coordiantes.x === lydia_coordiantes.y){
				if(step === "E"){
					my_path_str += "S";
					my_next_step = "E";
				}
				else{
					my_path_str += "E";
					my_next_step = "S";
				}
			}
			else{
				my_path_str += my_next_step;
				my_next_step = (my_next_step === "E") ? "S":"E";
			}
			if(step === "E"){
				lydia_coordiantes.x++;
			}
			else{
				lydia_coordiantes.y++;
			}
		}
	)
	return my_path_str;
}
readline.on('line', function(line) {
	line = line.trim();
	if(state === "test_cases"){
		num_test_cases = parseInt(line);
		state = "dimension";
	}
	else if(test_case > num_test_cases){
		process.exit(0);
	}
	else if(state === "dimension"){
		dimension = parseInt(line);
		state = "path"
	}
	else{
		console.log(`Case #${test_case++}: ${find_path(line)}`);
		state = "dimension";
	}
}).on('close',function(){
    process.exit(0);
});
