const fs = require('fs');

function readFile(file) {
    return new Promise((resolve, reject) => {
        fs.readFile(file, (err, data) => {
            if (err) return reject(err);
            return resolve(data);
        });
    })
}

fs.readdir('./data', (err, files) => {
    const datas = files.map(f => './data/' + f).map(readFile)
        .map(p => p.then(JSON.parse));
    const arr = Promise.all(datas)
        .then(dataArray =>
             dataArray.reduce((acc, cur) =>
              acc.concat(cur)));
    arr.then(JSON.stringify).then(a => {
        fs.writeFile('remedios.json', a, (err) => {
            if (err) {
                console.log('Error while writing: ', err);
                return;
            }
            console.log('Wrote remedios.json');
        });
    });
})