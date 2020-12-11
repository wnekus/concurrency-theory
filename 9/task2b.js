import walkdir from "walkdir/walkdir.js"
import * as fs from 'fs';
import  parallel  from 'async/parallel.js';
import apply from 'async/apply.js'

var total = 0;

function getFiles(path) {
    let files = [];
    walkdir.sync(path, function(path) {
        files.push(path);
    });
    return files;
}

function getFileLinesNumber(file) {
    return new Promise((resolve, reject) => {
        let count = 0;
        fs.createReadStream(file).on('data', function(chunk) {
            count += chunk.toString('utf8')
            .split(/\r\n|[\n\r\u0085\u2028\u2029]/g)
            .length-1;
        }).on('end', function() {
            console.log("File " + file + " number of lines: " + count)
            resolve(count);
        }).on('error', function(err) {
            console.error(err);
        });
    });
}

function main() {
    let files = getFiles("pam08").filter(file => !fs.lstatSync(file).isDirectory());
    let tasks = [];

    const reducer = (accumulator, currentValue) => accumulator + currentValue;

    let start = new Date();

    files.forEach(file => tasks.push(getFileLinesNumber(file)));

    Promise.all(tasks).then((values) => {
        console.log("Total number of lines: " + values.reduce(reducer));
        let end = new Date();
        let elapsed = end.getTime() - start.getTime();
        console.log(elapsed);
    })
}

main();