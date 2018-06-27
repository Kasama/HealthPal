const cheerio = require('cheerio');
const req = require('request-promise-native');
const { Iconv } = require('iconv');
const fs = require('fs');

const requestOptions = {
  method: 'POST',
  uri: 'http://www.anvisa.gov.br/datavisa/fila_bula/frmResultado.asp',
  form: {
    txtMedicamento: '',
    txtEmpresa: '',
    txtNuExpediente: '',
    txtDataPublicacaoI: '',
    txtDataPublicacaoF: '',
    hddLetra: '',
    hddOrderBy: 'medicamento',
    hddSortBy: 'asc',
    hddPageSize: 100,
    hddPageAbsolute: 1,
  },
  encoding: null
}

const utf8Conversor = new Iconv('ISO-8859-1', 'UTF-8');

function fetchPage(number) {
  requestOptions.form.hddPageAbsolute = number;
  const before = new Date();
  return req(requestOptions).then(body => {
    const $ = cheerio.load(utf8Conversor.convert(body));
    const arr = [];
    $('#tblResultado > tbody > tr').each((i, tr) => {
      const data = tr.children
        .filter(td => td.type == 'tag')
        .map(td => {
          const text = td.children[0];
          if (text.data.trim() != "") {
            return text.data.trim();
          } else {
            const functionCall = text.next.attribs.onclick;
            if (!functionCall) return '';
            const match = /.*\('(\d+)', '(\d+)'\)/.exec(functionCall);
            return match;
          }
        });
      if (data.length <= 4) {
        return {};
      }
      let [trans, axes] = ['', ''];
      if (data[4]) {
        [trans, axes] = data[4];
      }
      arr.push({
        medicamento: data[0],
        empresa: data[1],
        expediente: data[2],
        dataPublicacao: data[3],
        bula: `http://www.anvisa.gov.br/datavisa/fila_bula/frmVisualizarBula.asp?pNuTransacao=${trans}&pIdAnexo=${axes}`,
      });
    });
    return arr;
  }).then(parsed => {
    const after = new Date();
    console.log(`Page ${number} done in: ${(after - before)/1000}s`);
    return parsed;
  });
}
// const pageNumbers = new Array(877);
const pageNumbers = new Array(10);

for (let i = 1; i <= pageNumbers.length; ++i) { pageNumbers[i-1] = i + 80; }

const pagePromises = pageNumbers.map(fetchPage);
// const pagePromises = [fetchPage(12)];
const before = new Date();
Promise.all(pagePromises).then(pages => {
  const after = new Date();
  console.log(`All successful after ${(after - before)/1000}s`);
  return pages.reduce((acc, current) => acc.concat(current))
}).then((allMedications) => {
  fs.writeFile('remedios.json', JSON.stringify(allMedications), (err) => {
    if (err) console.log("Error while writing file:", err);
    console.log("wrote to file 'remedios.json'");
  })
})
