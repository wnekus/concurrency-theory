import  waterfall  from 'async/waterfall.js';

function printAsync(s, cb) {
    var delay = Math.floor((Math.random() * 1000) + 500);
    setTimeout(function () {
      console.log(s);
      if (cb) cb();
    }, delay);
  }
  
  function task(n) {
    return new Promise((resolve, reject) => {
      printAsync(n, function () {
        resolve(n);
      });
    });
  }

  function sectionTask(callback) {
        task(1).then((n) => {
          console.log('task', n, 'done');
          return task(2);
      }).then((n) => {
          console.log('task', n, 'done');
          return task(3);
      }).then((n) => {
          console.log('task', n, 'done');
          console.log('done');
          callback();
      })    
  }     

  function loop(m) {  
   let tasks = [];
   for(let i = 0; i < m; i++) {
     tasks.push(sectionTask);
   }
   waterfall(tasks);
}
  
loop(4);